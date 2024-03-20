package com.itheima.bigevent.interceptors;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.itheima.bigevent.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Logininterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		
		//token驗證( 因文件要求將token放在header中的Autorization中, 故要先去取得後, 下一步去做驗證)
		String token = request.getHeader("Authorization");
		
		try {
			//通過驗證, 回傳ture
			Map<String, Object> claims = JwtUtil.parseToken(token);
			return true;
			
		} catch (Exception e) {
			//不通過驗證, 出現401錯誤
			response.setStatus(401);
			return false;
		}
		
	}
	
}
