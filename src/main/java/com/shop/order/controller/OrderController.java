package com.shop.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.cart.entity.Cart;
import com.shop.cart.entity.CartItem;
import com.shop.order.entity.Order;
import com.shop.order.entity.OrderItem;
import com.shop.order.service.OrderService;
import com.shop.user.entity.User;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	// 添加订单同时清空购物车
	@RequestMapping("/addOrder")
	public String addOrder(HttpSession session){
		// 获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		
		// 创建订单
		Order order = new Order();
		// 设置总金额
		order.setTotal(cart.getTotal()); 
		
		// 设置订单条目
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (CartItem cartItem : cart.getCartItems()) { // 遍历购物车条目
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount()); // 设置订单条目的数量
			orderItem.setProduct(cartItem.getProduct()); // 设置订单条目上的商品
			orderItem.setSubtotal(cartItem.getSubtotal()); // 设置订单条目的小计
			orderItem.setOrder(order); // 设置订单条目所属的订单		
			
			orderItemList.add(orderItem);
			
		}
		order.setOrderItems(orderItemList); // 将订单条目添加到订单中
		// 获取用户
		User user = (User) session.getAttribute("user");
		order.setUser(user);
		
		// 保存订单同时保存订单条目
		orderService.insertOrder(order, orderItemList);
		// 将订单保存到session中
		session.setAttribute("order", order);
		// 清空购物车
		cart.clear();
		return "order";
	}

	// 根据uid查询所属订单
	@RequestMapping("/myOrder")
	public String myOrder(HttpSession session, HttpServletRequest request){
		// 获取登陆的用户
		User user = (User) session.getAttribute("user");
		// 查询用户的所有订单
		List<Order> orderList = orderService.selectOrderByUid(user.getUid());
		request.setAttribute("orderList", orderList);
		
		return "orderList";
	}
	
	// 根据oid查询所属订单
	@RequestMapping("/orderByOid")
	public String orderByOid(Integer oid, HttpSession session){

		// 查询用户的所有订单
		Order order = orderService.selectOrderByOid(oid);
		session.setAttribute("order", order);
		
		return "order";
	}
	
	// 确认订单
	@RequestMapping("/confirmOrder")
	public String confirmOrder(Integer oid){

		// 将用户订单状态修改为已付款
		orderService.updateOrderState(oid, 2);
		
		return "forward:/order/myOrder";
	}
	
	// 完成订单
	@RequestMapping("/endOrder")
	public String endOrder(Integer oid){
		
		// 将用户订单状态修改为交易完成
		orderService.updateOrderState(oid, 4);
		
		return "forward:/order/myOrder";
	}
	


}
