package com.shop.cart.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车实体类
 * 拥有购物总计和购物项的集合 
 */
public class Cart {

	// 购物项集合
	private Map<Integer, CartItem> cartItems = new LinkedHashMap<Integer, CartItem>();
	// 购物总计
	private double total;

	public Collection<CartItem> getCartItems() {
		 return cartItems.values();
	}

	public double getTotal() {
		return total;
	}

	/*
	 * 购物车的功能
	 */
	// 1、将购物项添加入购物车
	public void add(CartItem cartItem) {
		// 先判断要添加的购物项是否存在
		Integer pid = cartItem.getProduct().getPid();
		if (cartItems.containsKey(pid)) {
			// 存在，则添加数量
			CartItem _cartItem = cartItems.get(pid); // 返回原来的购物项
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount()); // 设置数量为新添加的购物项数量+原来的购物项的数量
		} else {
			// 不存在，直接添加
			cartItems.put(pid, cartItem);
		}
		// 设置总计=总计+新添加的购物小计
		total += cartItem.getSubtotal();

	}

	// 2、从购物车中移除购物项
	public void remove(Integer pid) {
		CartItem cartItem = cartItems.remove(pid); // 移除购物项，并返回移除的购物项
		total -= cartItem.getSubtotal(); // 设置总计=总计-移除的小计

	}

	// 3、清空购物车
	public void clear() {
		cartItems.clear(); // 清空
		total = 0; // 设置总计
	}
}
