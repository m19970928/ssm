package com.shop.user.service;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.user.dao.UserDao;
import com.shop.user.entity.User;
import com.shop.util.Email;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 根据用户名查询用户是否存在
	public User selectUserByUsername(String username) {
		return userDao.selectUserByUsername(username);
	}

	// 注册用户
	public void regist(User user) {
		// 添加激活码
		user.setCode(UUID.randomUUID().toString().replace("-", ""));
		// 添加状态
		user.setState(0);
		userDao.insertUser(user);

		// 发送激活邮件
		Email.sendEamil(user.getEmail(), 
				"<a href='http://localhost:8888/ssmshop/user/active?code=" + user.getCode() + "'>" + "点击此处，激活用户</a>");
	}

	// 查询全部用户
	public List<User> selectUserAll() {
		return userDao.selectUserAll();
	}

	// 删除用户
	public void deleteUserByUid(Integer uid) {
		userDao.deleteUserByUid(uid);
	}

	// 查询用户
	public User selectUserByUid(Integer uid) {
		return userDao.selectUserByUid(uid);
	}

	// 修改用户
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	// 根据激活码查询用户
	public User selectUserByCode(String code) {
		return userDao.selectUserByCode(code);
	}

}
