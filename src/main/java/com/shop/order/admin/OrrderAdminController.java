package com.shop.order.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.order.entity.Order;
import com.shop.order.entity.OrderItem;
import com.shop.order.service.OrderService;
import com.shop.util.PageBean;


@Controller
@RequestMapping("/orderAdmin")
public class OrrderAdminController {

	@Autowired
	private OrderService orderService;
	
	// 查询全部订单
	@RequestMapping("/selectOrderAll")
	public String selectOrderAll(HttpServletRequest request, int page){
		
		// 查询全部订单
		PageBean<Order> pageBean = orderService.selectOrderAll(page);
		request.setAttribute("pageBean", pageBean);
		
		return "admin/order/list";
	}
	
	// 为订单商品发货
	@RequestMapping("/sendOrder")
	public String sendOrder(HttpServletRequest request, Integer oid){
		
		// 将订单状态由已付款改为确认收货
		orderService.updateOrderState(oid, 3);
		
		return "forward:/orderAdmin/selectOrderAll";
	}

	// Ajax显示订单详情
	@RequestMapping("/selectOrderItemByOid")
	public String selectOrderItemByOid(HttpServletRequest request, Integer oid){
		// 根据订单id查询订单项:
		List<OrderItem> orderItemList = orderService.selectOrderItem(oid);
		request.setAttribute("orderItemList", orderItemList);		
				
		return "admin/order/orderItem";
	}
	
}
