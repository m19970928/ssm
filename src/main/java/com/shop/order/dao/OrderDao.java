package com.shop.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.order.entity.Order;
import com.shop.order.entity.OrderItem;

public interface OrderDao {
	
	// 添加订单
	public void insertOrder(Order order);
	
	// 添加订单条目
	public void insertOrderItem(OrderItem orderItem);
	
	// 根据uid查询所属订单
	public List<Order> selectOrderByUid(Integer uid);
	
	// 根据oid查询订单详情
	public Order selectOrderByOid(Integer oid);

	// 修改订单
	public void updateOrder(Order order);

	// 查询订单的总数量
	public int selectOrderCountAll();

	// 查询全部订单
	public List<Order> selectOrderAll(@Param("begin")int begin, @Param("limit")int limit);

	// 查询订单条目
	public List<OrderItem> selectOrderItem(Integer oid);
	
}
