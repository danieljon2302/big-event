package com.itheima.bigevent.service;

import com.itheima.bigevent.pojo.User;

public interface UserService {
	
	User findByUserName(String username);
	
	void register(String username,String password);

}
