package com.shop.order.entity;

import com.shop.product.entity.Product;

/**
 * 订单条目实体类
 */
public class OrderItem {
	private Integer itemid; // 订单条目id
	private Integer count; // 订单条目的数量
	private Double subtotal; // 订单条目小计
	// 商品外键:对象
	private Product product;
	// 订单外键:对象
	private Order order;

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
