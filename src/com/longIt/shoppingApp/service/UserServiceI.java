/**
 * 
 */
package com.longIt.shoppingApp.service;

import com.longIt.shoppingApp.bean.User;

/**
 * @author Administrator
 *
 */
public interface UserServiceI {

	// 根据用户输入的账号密码获取用户信息
	User findUserByNameAndPass(User user);

	// 异步校验账号是否存在
	String validLoginName(String loginName);

	// 注册
	void saveUser(User user);

	// 用户信息激活
	String active(String activeCode);

}
