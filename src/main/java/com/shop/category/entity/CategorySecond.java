package com.shop.category.entity;

import java.util.HashSet;
import java.util.Set;

import com.shop.product.entity.Product;

/**
 * 二级分类的实体
 */
public class CategorySecond {
	private Integer csid;
	private String csname;
	// 所属一级分类.存的是一级分类的对象.
	private Category category;
	// 所属的商品
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CategorySecond [csid=" + csid + ", csname=" + csname
				+ ", category=" + category + ", product=" + product + "]";
	}

}
