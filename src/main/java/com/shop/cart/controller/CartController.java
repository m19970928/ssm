package com.shop.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.cart.entity.Cart;
import com.shop.cart.entity.CartItem;
import com.shop.product.service.ProductService;
import com.shop.user.entity.User;

/**
 * 购物车Controller
 */

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private ProductService productService;
		
	/**
	 * 添加购物车
	 * @param pid 需要添加的商品id
	 * @param count 需要添加的商品数量
	 * @param session 用于获取登陆中的用户分配的购物车
	 * @param request 用于返回错误信息
	 * @return
	 */
	@RequestMapping("/addCart")
	public String addCart(Integer pid, Integer count, HttpSession session, HttpServletRequest request){
		// 获取用户登录时分配的购物车
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null){
			// 用户还未登陆
			request.setAttribute("erro", "请先登陆");
			return "user/login";
		}
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.selectProductByPid(pid));
		cart.add(cartItem);
		return "cart";
	}
	
	/**
	 * 删除购物项
	 * @param pid 需要删除的购物项的商品id
	 * @param session 获取购物车
	 * @return
	 */
	@RequestMapping("/deleteCart")
	public String deleteCart(Integer pid, HttpSession session){
		// 获取用户的购物车
		Cart cart = (Cart)session.getAttribute("cart");
		cart.remove(pid);
		return "cart";
	}
	
	// 清空购物车
	@RequestMapping("/clearCart")
	public String clearCart(HttpSession session){
		// 获取用户的购物车
		Cart cart = (Cart)session.getAttribute("cart");
		cart.clear();
		return "cart";
	}
	
	// 跳转到购物车页面
	@RequestMapping("/myCart")
	public String myCart(HttpSession session, HttpServletRequest request){
		// 获取登陆中的用户信息
		User user = (User) session.getAttribute("user");
		if(user == null){
			request.setAttribute("erro", "尚未登陆，请登录后查询购物车");
			return "user/login";
		}
		return "cart";
	}
	
}
