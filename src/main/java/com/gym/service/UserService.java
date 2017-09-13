package com.gym.service;

import java.util.Set;

import com.gym.pojo.User;

public interface UserService {
	/**
	 * 根据用户名查用户信息
	 * @param userName
	 * @return
	 */
	public User slectUserByName(String userName);
	/**
	 * 根据用户查找角色
	 * @param userName
	 * @return
	 */
	public Set<String> slectRoleByName(String userName);
}
