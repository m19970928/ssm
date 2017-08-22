package com.shop.user.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.user.entity.User;
import com.shop.user.service.UserService;

/**
 * 用户后台Controller
 * 主要实现对所有用户的CRUD
 */

@Controller
@RequestMapping("/userAdmin")
public class UserAdminController {

	@Autowired
	private UserService userService;
	
	
	// 查询所有用户
	@RequestMapping("/selectUserAll")
	public String selectUserAll(HttpServletRequest request){
		List<User> userList = userService.selectUserAll();
		request.setAttribute("userList", userList);
		return "admin/user/list";
	}
	
	// 删除用户
	@RequestMapping("/deleteUser")
	public String deleteUser(Integer uid, HttpServletRequest request){
		userService.deleteUserByUid(uid);
		
		return "forward:/userAdmin/selectUserAll";
	}
	
	// 添加用户
	@RequestMapping("/insertUser")
	public String insertUser(User user, HttpServletRequest request){
		userService.regist(user);
		
		return "forward:/userAdmin/selectUserAll";
	}
	
	// 修改用户
	@RequestMapping("/updateUser")
	public String updateUser(User user, HttpServletRequest request){
		userService.updateUser(user);
		
		return "forward:/userAdmin/selectUserAll";
	}	
	
	// 修改用户之前跳转到编辑页面
	@RequestMapping("/selectUserByUid")
	public String selectUserByUid(HttpServletRequest request, Integer uid){
		// 根据uid查询用户，用于编辑时能直接显示用户信息
		User user = userService.selectUserByUid(uid);
		request.setAttribute("user", user);
		return "admin/user/edit";
	}
}
