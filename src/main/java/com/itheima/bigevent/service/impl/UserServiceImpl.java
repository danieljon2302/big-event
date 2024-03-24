package com.itheima.bigevent.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.bigevent.mapper.UserMapper;
import com.itheima.bigevent.pojo.User;
import com.itheima.bigevent.service.UserService;
import com.itheima.bigevent.util.ThreadLocalUtil;

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

	@Override
	public void update(User user) {
		user.setUpdateTime(LocalDateTime.now());
		userMapper.update(user);
	}

	@Override
	public void updateAvatar(String avatarUrl) {
		
//		在文檔中, 並未強制要求說必須傳入id, 但若要更新用戶頭像必定要先取的該用戶的id才能進行更新
//		故需將token中的id取出
		Map<String, Object> map = ThreadLocalUtil.get();
		Integer id = (Integer) map.get("id");
		
		userMapper.updateAvatar(avatarUrl,id);
		
	}

	@Override
	public void updatePwd(String newPwd) {
		
		Map<String, Object> map = ThreadLocalUtil.get();
		Integer id = (Integer) map.get("id");
		//之後用md5util來加／解密
		userMapper.updatePwd(newPwd, id);
		
	}

}
