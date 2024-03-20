package com.itheima.bigevent.mapper.Impl;

import org.apache.ibatis.annotations.Mapper;

import com.itheima.bigevent.mapper.UserMapper;
import com.itheima.bigevent.pojo.User;

@Mapper
public class UsermapperImpl implements UserMapper {

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String username, String password) {
		// TODO Auto-generated method stub
		
	}

}
