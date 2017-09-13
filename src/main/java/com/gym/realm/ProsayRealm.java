package com.gym.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.gym.pojo.User;
import com.gym.service.UserService;

public class ProsayRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	/**
	 * 用于权限认证的
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pCollection) {
		System.out.println("进入授权认证************");
		String userName = pCollection.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(userService.slectRoleByName(userName));
		return info;
	}
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken tonken) throws AuthenticationException {
		System.out.println("进入登录认证**************88");
		String userName = tonken.getPrincipal().toString();
		User user = userService.slectUserByName(userName);
		if(user != null){
			//将查询到的账号和密码放到AuthenticationInfo对象中提供给shiro
			AuthenticationInfo authInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"gym");
			return authInfo;
		}
		return null;
	}

}
