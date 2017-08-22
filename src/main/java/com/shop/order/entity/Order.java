package com.shop.order.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.shop.user.entity.User;

/**
 * 订单实体类
 */
public class Order {
	private Integer oid; // 订单id
	private Double total; // 订单总计
	private Date ordertime; // 订单创建时间
	private Integer state;// 1:未付款 2:订单已经付款 3:已经发货 4:订单结束

	// 用户的外键:对象
	private User user;
	// 配置订单项的集合
	private List<OrderItem> orderItems;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", total=" + total + ", ordertime="
				+ ordertime + ", state=" + state + ", user=" + user
				+ ", orderItems=" + orderItems + "]";
	}

}
