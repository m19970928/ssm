package com.shop.user.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.cart.entity.Cart;
import com.shop.user.entity.User;
import com.shop.user.service.UserService;
import com.shop.util.VerifyCode;

/**
 * 用户Controller
 * 主要实现用户注册登录激活退出
 */

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 实现注册功能
	 * @param user 用户输入的信息
	 * @param session 用于获取验证码文本
	 * @param request 用于返回错误信息
	 * @param captcha 获取用户输入的验证码
	 * @return
	 */
	@RequestMapping("/regist")
	public String regist(User user, HttpSession session, HttpServletRequest request,  String captcha){
		// 查询用户名是否被占用
		User newUser = userService.selectUserByUsername(user.getUsername());
		// 获取用户输入的验证码内容并大写
		captcha = captcha.toUpperCase();
		// 获取实际验证码内容并大写
		String text = (String) session.getAttribute("text");
		text = text.toUpperCase();
		
		// 判断用户是否为空，为空则代表用户名查询不到用户，即用户名未被占用可以注册
		if(newUser != null){
			// 用户名已被占用，需重新注册，返回错误信息
			request.setAttribute("erro", "用户名已被占用，请重新输入"); 
			return "user/regist";
		} else if(!text.equals(captcha)){ // 用户名可以使用，判断验证码是否正确
			// 验证码错误，返回错误信息
			request.setAttribute("erro", "验证码错误"); 
			return "user/regist";
		}
		// 账号可用，将用户保存到数据库中，同时发送激活邮件到Email中
		userService.regist(user);
		
		// 返回到登陆页面
		return "user/login";
	}
	
	/**
	 * 登陆功能
	 * @param user 用户输入的信息
	 * @param isRememberUsername 是否保存用户名密码Cookie
	 * @param captcha 用户输入的验证码信息
	 * @param session 用于获取验证码文本，用于将登陆的用户信息保存至session域中
	 * @param request 用于返回错误信息
	 * @param response 用于添加Cookie
	 * @return
	 */
	@RequestMapping("/login")
	public String login(User user, String isRememberUsername, String captcha, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		// 根据用户名获取用户信息
		User newUser = userService.selectUserByUsername(user.getUsername());
		// 用户如果为空，说明用户名不存在，即登陆的用户名错误
		if(newUser == null){
			// 用户名错误，返回错误信息，重新登陆
			request.setAttribute("erro", "用户名不存在，请重新输入");
			return "user/login";
		} else if(!newUser.getPassword().equals(user.getPassword())){ // 用户名正确，判断输入的密码与查询到的用户的密码是否一致
			// 若不一致，即用户名正确但密码错误，返回错误信息，重新登陆
			request.setAttribute("erro", "密码错误，请重新输入");
			return "user/login";
		} else if(newUser.getState() != 1){ // 用户名密码都正确，判断状态是否为1
			// 状态为0，尚未激活，返回错误信息，重新登陆
			request.setAttribute("erro", "用户尚未激活，请去激活");
			return "user/login";
		} 
		
		// 用户名密码状态皆正确，判断验证码是否正确
		String text = (String) session.getAttribute("text");
		text = text.toUpperCase();
		captcha = text.toUpperCase();
		if(captcha.equals(text)){
			// 验证码正确，登陆成功，将newUser保存到Session中
			session.setAttribute("user", newUser);
			
			// 判断是否添加用户信息到cookie中
			cookie(isRememberUsername, newUser.getUsername(), newUser.getPassword(), response);
			
			// 登陆成功后为没一个用户创建一个session域购物车
			Cart cart = new Cart();
			session.setAttribute("cart", cart);
			return "index";
		} else{
			// 验证码错误，返回错误信息，重新登陆
			request.setAttribute("erro", "验证码错误");
			return "user/login";
		}
		
		
	}
	
	
	// 退出功能
	@RequestMapping("/quit")
	public String quit(HttpSession session){
		// 删除session域中的用户信息
		session.removeAttribute("user"); 
		return "redirect:/index";
	}
	// 跳转到注册页面
	@RequestMapping("/registPage")
	public String registPage(){
		return "user/regist";
	}
	// 跳转到登陆页面
	@RequestMapping("/loginPage")
	public String loginPage(){
		return "user/login";
	}
	
	// Ajax判断用户名是否被占用
	@ResponseBody // 返回json数据
	@RequestMapping(value="/ajaxUsername", produces="application/json; charset=utf-8") // 解决返回Json数据乱码
	public String ajaxUsername(String username){
		// 查询用户名是否被占用
		User user = userService.selectUserByUsername(username);
		if(user != null){
			// 代表用户名已被占用
			return "用户名已被占用，请重新输入";
		} else{
			// 用户名可以使用
			return "用户名可以使用";
		}
	}
	
	// 激活用户功能
	@RequestMapping("/active")
	public String active(String code, HttpServletRequest request){
		// 根据激活码查询用户，若查询到用户则激活码正确
		User user = userService.selectUserByCode(code);
		if(user != null){
			// 代表激活码正确，将用户状态设置为1，清空激活码
			user.setState(1);
			user.setCode(null);
			// 修改用户
			userService.updateUser(user);

			// 显示激活成功信息
			request.setAttribute("msg", "激活成功，请去登陆");
			return "user/msg";
		} else{
			// 显示激活失败信息
			request.setAttribute("erro", "激活失败，激活码错误");
			return "user/msg";
		}
	}

	// cookie保存用户名和密码
	public void cookie(String isRememberUsername, String username, String password, HttpServletResponse response){
		if("true".equals(isRememberUsername)){
			// cookie保存用户名和密码
			
			// 创建Cookie
			Cookie username_cookie = new Cookie("username", username);
			Cookie password_cookie = new Cookie("password", password);
			// 设置Cookie保存时间，60s
			username_cookie.setMaxAge(60);
			password_cookie.setMaxAge(60);
			// 保存Cookie到客户端
			response.addCookie(username_cookie);
			response.addCookie(password_cookie);
		}
		
	}
	
	// 获取验证码
	@RequestMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 验证码获取工具类
		VerifyCode verifyCode = new VerifyCode();
		// 随机获取验证码
		BufferedImage bi = verifyCode.getImage();
		// 将验证码输出到网页中
		VerifyCode.output(bi, response.getOutputStream());
		request.getSession().setAttribute("text", verifyCode.getText());
	}
}	
