package com.itheima.bigevent.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.pojo.User;
import com.itheima.bigevent.service.*;
import com.itheima.bigevent.util.JwtUtil;
import com.itheima.bigevent.util.ThreadLocalUtil;

import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//註冊
	//之後將邏輯判斷放到Service層
	@PostMapping("/register")
	public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, 
							@Pattern(regexp = "^\\S{5,16}$") String password) {
		//轉譯字符: ^$, \s代表非為空值, \\s代表將\s化為轉譯字符
		User u = userService.findByUserName(username);
		
		if(u==null) {
			
			userService.register(username,password);
			return Result.success();
		}else {
			
			return Result.error("用戶名已被佔據");
		}
	}
	
	//登入
	@PostMapping("/login")
	public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, 
			@Pattern(regexp = "^\\S{5,16}$") String password){
		
		User loginUser = userService.findByUserName(username);
		
		if(loginUser==null) {
			return Result.error("用戶名錯誤");
		}
		
		//判斷密碼是否正確, 之後將輸入的password參數利用Md5util進行“解密”
		//if(MD5util.getMD5String(password).equals(loginUser.getPassword()))
		if(password.equals(loginUser.getPassword())) {
			
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("id", loginUser.getId());
			claims.put("username", loginUser.getUsername());
			
			String token = JwtUtil.genToken(claims);
			//將token存到redis中
			ValueOperations<String, String> Operations= stringRedisTemplate.opsForValue(); 
			Operations.set(token, token, 1, TimeUnit.HOURS);
			
			return Result.success(token);
		}
		
		return Result.error("密碼有誤");
		
	}
	
//	使用ThreadLocal線程儲存數據
	@GetMapping("/userInfo")
	public Result<User> userInfo(){	
//		先new 一個ThreadLocal, 再將其放入Interceptor攔截器中
//		此時再將方到攔截器中的ThreadLocalutil.get()的到儲存的數據
		Map<String, Object> map = ThreadLocalUtil.get();
		String username = (String)map.get("username");
		
		User user = userService.findByUserName(username);
		
		return Result.success(user);
	}
	
//	未使用ThreadLocal儲存數據
//	@GetMapping("/userInfo")
//	public Result<User> userInfo(@RequestHeader (name= "Authorization") String token){
//		
//		//Step1. 從header拿到登入後取得的token中, 驗證此token
//		Map<String, Object> map= JwtUtil.parseToken(token);
//		//Step2. 此token中會紀錄登入者的相關info, ex: User.class中的attribute
//		String username =(String)map.get("username");
//		//Step3. 藉由此username去查詢sql ,可得到User class
//		User user = userService.findByUserName(username);
//		
//		return Result.success(user);
//	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Validated User user) {
		userService.update(user);
		return Result.success();
	}
	
	@PatchMapping("/updateAvatar")
	public Result updateAvatar(@RequestParam @URL String avatarUrl) {
		userService.updateAvatar(avatarUrl);
		return Result.success();
	}
	
	@PatchMapping("/updatePwd")
	public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization")String token) {
		
//		接收前端傳進來的三個參數
		String oldPwd = params.get("old_pwd");
		String newPwd = params.get("new_pwd");
		String rePwd = params.get("re_pwd");
		
//		StringUtils.hasLength用於測試有沒有接收到參數haslength
		if(!StringUtils.hasLength("oldPwd")||!StringUtils.hasLength("newPwd")||!StringUtils.hasLength("rePwd")) {
			return Result.error("缺少必要參數");
		}
		
//		藉由在ThreadLocal中儲存的username取得該用戶密碼, 接著繼續比對與使用者輸入的值事否一致
		Map<String, Object> map = ThreadLocalUtil.get();
		String username =(String) map.get("username");
		
		User loginUser = userService.findByUserName(username);
		
		if(!loginUser.getPassword().equals(oldPwd)) {
			return Result.error("原密碼不正確");
		}
		
		if (!rePwd.equals(newPwd)) {
			return Result.error("再次輸入的密碼有誤");
		}
		
		userService.updatePwd(newPwd);
		//將存有舊密碼的token刪除-> 到參數中加入變數token( 此token由requestHeader中的屬性"Authorization"獲得)
		ValueOperations<String, String>  operations = stringRedisTemplate.opsForValue();
		operations.getOperations().delete(token);
		
		return Result.success();
	}
	
}
