package com.gym.mapper;

import java.util.Set;

import com.gym.pojo.Role;
import com.gym.pojo.User;

public interface UserMapper {
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
