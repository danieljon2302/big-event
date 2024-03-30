package com.itheima.bigevent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

//在測試頭上加上此註解, 可在單元測試進行前, 進行初始化(new) sprint容器的動作
@SpringBootTest
public class RedisTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	//儲存token的method
	@Test
	public void testSet() {
		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
		operations.set("username", "zhang");
	}
	@Test
	public void testGet() {
		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
		System.out.println(operations.get("username"));
	}
}
