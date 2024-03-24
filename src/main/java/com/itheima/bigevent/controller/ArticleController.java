package com.itheima.bigevent.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/article")
public class ArticleController {
	
//將“驗證”token寫在“個別的”api中, 較麻煩, 若有“多個”登入後才能存取的功能依此寫法就必須在每個controller中寫一模一樣的驗證方式
//故須創建一"驗證的class"LoginInteceptors.class與"配置"寫好的驗證class的class"WebConfig.class
//	@PostMapping("/list")
//	public Result<String> list(@RequestHeader(name= "Authorization") String token,
//			HttpServletResponse response){
//		
//		try {
//			//驗證token
//			Map<String, Object> claimsMap = JwtUtil.parseToken(token);
//			return Result.success("所有文章數據");
//			
//		} catch (Exception e) {
//			//res狀態為401
//			response.setStatus(401);
//			return Result.error("未登入");
//		}
//		
//	}
	
//測試: 在沒驗證的情況下(postman的header中的Autorization移除)(注意token時間, 過期了就必須重新login取得新token), 
//所寫的Interceptor是否能將其攔截, 並出現401error(同時測config是否成功配置)
	@PostMapping("/list")
	public Result<String> list(){
		
		return Result.success("成功訪問文章列表");
		
	}
	
}
