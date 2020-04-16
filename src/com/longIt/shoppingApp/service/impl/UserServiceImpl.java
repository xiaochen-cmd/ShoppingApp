/**
* 
*/
package com.longIt.shoppingApp.service.impl;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longIt.shoppingApp.bean.User;
import com.longIt.shoppingApp.mapper.UserMapper;
import com.longIt.shoppingApp.service.UserServiceI;
import com.sun.mail.smtp.SMTPMessage;

/**
 * @author Administrator
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserByNameAndPass(User user) {
		// 根据用户输入的账号以及密码获取用户的信息
		User u = userMapper.findUserByNameAndPass(user);
		return u;
	}

	// 异步校验账号是否存在s
	@Override
	public String validLoginName(String loginName) {
		User user = userMapper.validLoginName(loginName);
		if (user != null) {
			return "您输入的账号已经存在!";
		}
		return "";
	}

	// 注册
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		try {
			// 生成激活码
			String active = UUID.randomUUID().toString();
			user.setCreateDate(new Date());
			user.setActive(active);
			userMapper.saveUser(user);

			// 开始发送邮件给用户

			Properties props = new Properties();
			// 服务器地址
			props.setProperty("mail.smtp.host", "smtp.126.com");
			// 服务器需要鉴权
			props.setProperty("mail.smtp.auth", "true");

			// 2).创建Authenticator的实例，实现账户、密码的鉴权。
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("luochunlong666@126.com", "luochunlong123");
				}
			};
			// 3).获得Session实例
			Session mailSession = Session.getInstance(props, auth);
			// 4).创建SMTPMessage，要提供session
			SMTPMessage msg = new SMTPMessage(mailSession);
			// 5).设置Message的内容，包括发送者、接收者、消息体
			// 设置邮件标题，没有标题的邮件几乎都会被认为是垃圾邮件被系统退回。
			msg.setSubject("用户注册激活邮件，请勿回复，按照指引激活");
			// 设置消息的HTML格式的内容
			msg.setContent("<a href='http://127.0.0.1:8080/ShoppingApp/user/active?activeCode=" + user.getActive()
					+ "' target='_blank'>恭喜您注册成功，点击该链接进行激活，此邮件无需回复!</a>", "text/html;charset=UTF-8");
			// 设置发送人
			msg.setFrom(new InternetAddress("luochunlong666@126.com"));
			// 设置接收者
			// 接收者类型由：TO(收件人)、CC(抄送)、BCC(密送)
			msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));

			// 发送邮件
			Transport.send(msg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// 用户信息激活
	@Override
	public String active(String activeCode) {
		// 根据激活码获取 用户信息，如果炸不到说明激活码不正确或者已经被激活了
		User user = userMapper.getUserByActive(activeCode);
		if (user != null) {
			userMapper.active(activeCode);
			return "";
		} else {
			return "激活码失效!";
		}

	}

}
