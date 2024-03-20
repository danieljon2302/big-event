package com.itheima.bigevent.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.pojo.User;
import com.itheima.bigevent.service.*;
import com.itheima.bigevent.util.JwtUtil;

import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private UserService UserService;

	//註冊
	//之後將邏輯判斷放到Service層
	@PostMapping("/register")
	public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, 
							@Pattern(regexp = "^\\S{5,16}$") String password) {
		//轉譯字符: ^$, \s代表非為空值, \\s代表將\s化為轉譯字符
		User u = UserService.findByUserName(username);
		
		if(u==null) {
			
			UserService.register(username,password);
			return Result.success();
		}else {
			
			return Result.error("用戶名已被佔據");
		}
	}
	
	//登入
	@PostMapping("/login")
	public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, 
			@Pattern(regexp = "^\\S{5,16}$") String password){
		
		User loginUser = UserService.findByUserName(username);
		
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
			
			return Result.success(token);
		}
		
		return Result.error("密碼有誤");
		
	}
	

}
