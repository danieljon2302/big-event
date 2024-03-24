package com.itheima.bigevent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.itheima.bigevent.interceptors.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//此class主要用於“配置”>將寫好的攔截器注入
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	//InterceptorRegistry=> 攔截器的註冊器class, 要註冊後才能使用Interceptor攔截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//驗證主要是用於登入“後"(本步驟用於登入後才能藉由其登入時創建的token訪問登入後的會員權限頁面), 
		//故要排除"註冊"及"登入"下的路徑操作
		registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");
	}
	

}
