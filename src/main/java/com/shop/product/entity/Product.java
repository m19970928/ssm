package com.shop.product.entity;

import java.util.Date;

import com.shop.category.entity.CategorySecond;

/**
 * 商品的实体对象
 */
public class Product {
	private Integer pid; // 商品id
	private String pname; // 商品名称
	private Double marketPrice; // 商品市场价
	private Double shopPrice; // 商品商城价
	private String image; // 商品图片
	private String pdesc; // 商品信息
	private Integer hot; // 是否热门
	private Date pdate; // 商品创建时间
	// 二级分类的外键:使用二级分类的对象.
	private CategorySecond categorySecond;

	public CategorySecond getCategorySecond() {
		return categorySecond;
	}

	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", marketPrice="
				+ marketPrice + ", shopPrice=" + shopPrice + ", image=" + image
				+ ", pdesc=" + pdesc + ", hot=" + hot + ", pdate=" + pdate
				+ ", categorySecond=" + categorySecond + "]";
	}

}
