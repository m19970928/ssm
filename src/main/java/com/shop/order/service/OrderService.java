package com.shop.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.order.dao.OrderDao;
import com.shop.order.entity.Order;
import com.shop.order.entity.OrderItem;
import com.shop.product.entity.Product;
import com.shop.util.PageBean;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	// 保存订单
	public void insertOrder(Order order, List<OrderItem> orderItemList) {
		orderDao.insertOrder(order);
		for (OrderItem orderItem : orderItemList) {
			orderDao.insertOrderItem(orderItem);
		}
		
	}

	// 根据用户id查询用户所有的订单
	public List<Order> selectOrderByUid(Integer uid) {
		return orderDao.selectOrderByUid(uid);
	}

	// 根据订单id查询用户所有的订单
	public Order selectOrderByOid(Integer oid) {
		return orderDao.selectOrderByOid(oid);
	}

	// 修改订单状态
	public void updateOrderState(Integer oid, int state) {
		Order order = new Order();
		order.setOid(oid);
		order.setState(state);
		orderDao.updateOrder(order);
	}

	// 查询所有订单，并分页显示
	public PageBean<Order> selectOrderAll(int page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);// 设置当前页数
		// 设置每页显示数量
		int limit = 8 ; 
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = orderDao.selectOrderCountAll();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount/limit ;
		} else{
			totalPage = totalCount/limit+1 ;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		int begin = (page -1) * limit ; 
		List<Order> productList = orderDao.selectOrderAll(begin, limit);
		pageBean.setList(productList);
		return pageBean;
	}

	// 查询所有订单条目
	public List<OrderItem> selectOrderItem(Integer oid) {
		return orderDao.selectOrderItem(oid);
	}

	
}
