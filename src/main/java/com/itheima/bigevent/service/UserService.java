package com.itheima.bigevent.service;

import com.itheima.bigevent.pojo.User;

public interface UserService {
	
	User findByUserName(String username);
	
	void register(String username,String password);

	void update(User user);

	//更新頭貼
	void updateAvatar(String avatarUrl);

	//更改密碼
	void updatePwd(String newPwd);

}
