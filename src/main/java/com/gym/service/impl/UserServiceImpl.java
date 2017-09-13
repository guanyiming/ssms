package com.gym.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.UserMapper;
import com.gym.pojo.User;
import com.gym.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User slectUserByName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.slectUserByName(userName);
	}

	@Override
	public Set<String> slectRoleByName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.slectRoleByName(userName);
	}
	

}
