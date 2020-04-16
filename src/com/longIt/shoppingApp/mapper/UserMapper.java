package com.longIt.shoppingApp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.longIt.shoppingApp.bean.User;

/**
 * UserMapper 数据访问类
 * 
 * @date 2019-10-01 17:46:20
 * @version 1.0
 */
public interface UserMapper {

	// 根据用户输入的账号以及密码获取用户的信息
	@Select("select *from ec_user where login_name = #{loginName} and password = #{password}")
	User findUserByNameAndPass(User user);

	// 异步校验账号是否存在
	@Select("select *from ec_user where login_name = #{loginName}")
	User validLoginName(String loginName);

	// 注册
	@Insert("insert into ec_user(login_name,password,name,sex,email,phone,address,create_date,active) values (#{loginName},#{password},#{name},#{sex},#{email},#{phone},#{address},#{createDate},#{active})")
	void saveUser(User user);

	// 用户信息激活
	@Update("update ec_user set disabled = 1 , active = ''  where active = #{activeCode} ")
	void active(String activeCode);

	// 查找激活码
	@Select("select *from ec_user where active = #{activeCode}")
	User getUserByActive(String activeCode);

}