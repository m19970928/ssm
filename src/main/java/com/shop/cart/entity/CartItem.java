package com.shop.cart.entity;

import com.shop.product.entity.Product;

/**
 * 购物条目实体类
 * 拥有商品类型商品数量商品小计
 */
public class CartItem {

	private Product product; // 商品类型
	private int count; // 商品数量
	private double subtotal; // 商品小计

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/*
	 * 获得小计
	 */
	public double getSubtotal() {
		return count * product.getShopPrice();
	}

}
