package com.itheima.bigevent.util;

import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtil {

	private static final String KEY = "itheima";
	
	//接收業務數據,生成token
	public static String genToken(Map<String, Object> claims) {
		return JWT.create()
				.withClaim("claims",claims)//添加token的key與value
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加過期時間(毫秒)
				.sign(Algorithm.HMAC256(KEY));//記載加密算法名, 加密密鑰token的名字(未來要透過這個來解密)
	}
	
	//接收token, 驗證, 並返回token內容
	public static Map<String, Object> parseToken(String token){
		return JWT.require(Algorithm.HMAC256(KEY))
				.build()
				.verify(token)
				.getClaim("claims")
				.asMap();
	}
}
