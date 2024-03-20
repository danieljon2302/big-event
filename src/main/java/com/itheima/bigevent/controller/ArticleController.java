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
	
//測試: 在沒驗證的情況下, 所寫的Interceptor是否能將其攔截, 並出現401error(同時測config是否成功配置)
	@PostMapping("/list")
	public Result<String> list(){
		
		return Result.success("成功訪問文章列表");
		
	}
	
}
