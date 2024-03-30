package com.itheima.bigevent.interceptors;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.itheima.bigevent.util.JwtUtil;
import com.itheima.bigevent.util.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {		
		//token驗證( 因文件要求將token放在header中的Autorization中, 故要先去取得後, 下一步去做驗證)
		String token = request.getHeader("Authorization");
		
		try {
			
			ValueOperations<String, String>  operations= stringRedisTemplate.opsForValue();
			String redisToken = operations.get(token);
			
			if(redisToken==null) {
				throw new RuntimeException();
			}
			
			//通過驗證, 回傳ture
			Map<String, Object> claims = JwtUtil.parseToken(token);
			
			//將數據儲存在ThreadLocal中
			ThreadLocalUtil.set(claims);
			//放行
			return true;
			
		} catch (Exception e) {
			//不通過驗證, 出現401錯誤
			response.setStatus(401);
			return false;
		}		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//當執行完畢後, 清空儲存在其中的數據防止內存泄露
		ThreadLocalUtil.remove();
	}
	
}
