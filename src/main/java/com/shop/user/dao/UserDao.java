package com.shop.user.dao;

import java.util.List;

import com.shop.user.entity.User;

public interface UserDao {

	// 根据用户名查询用户
	public User selectUserByUsername(String username);
	
	// 根据激活码查询用户
	public User selectUserByCode(String code);
	
	// 向数据库中添加用户
	public void insertUser(User user);
	
	// 查询全部用户
	public List<User> selectUserAll();
	
	// 删除用户
	public void deleteUserByUid(Integer uid);
	
	// 修改用户
	public void updateUser(User user);
	
	// 根据uid查询用户
	public User selectUserByUid(Integer uid);

}
