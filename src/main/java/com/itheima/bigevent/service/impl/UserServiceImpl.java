package com.itheima.bigevent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.bigevent.mapper.UserMapper;
import com.itheima.bigevent.pojo.User;
import com.itheima.bigevent.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByUserName(String username) {
		
		User u = userMapper.findByUserName(username);
		return u;
	}

	@Override
	public void register(String username, String password) {
		// 先直接送明碼password進去, 之後再找加密( MD5/ SpringSecurity...) 
		// String md5String = Md5util.getMD5String(password);
		// 不用autowired, 用import即可
		
		userMapper.add(username, password);
	}

}
