package com.itheima.bigevent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtTest {

	@Test
	public void testGen() {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1);
		claims.put("username", "張三");
		
//		生成jwt token
		String token = JWT.create()
				.withClaim("user",claims)//添加token的key與value
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加過期時間(毫秒)
				.sign(Algorithm.HMAC256("itheima"));//記載加密算法名, 加密密鑰token的名字(未來要透過這個來解密)
					
		System.out.println(token);
		
	}
	
	@Test
	public void  testParse() {
		
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
				+ ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8teS4iSJ9LCJleHAiOjE3MTA4MDIxNDJ9"
				+ ".6Xta8hrXK5nKOT-AGyUTnBhTZkGPWnXfFwsZw7gpry4";
		
		JWTVerifier jwtverifier = JWT.require(Algorithm.HMAC256("itheima")).build();//生成一個驗證器
		
		DecodedJWT decodedJWT = jwtverifier.verify(token);//驗證token, 生成“解析後”的jwt物件
		Map<String, Claim> claims = decodedJWT.getClaims();
		System.out.println(claims.get("user"));
	}
	
	
	
}
