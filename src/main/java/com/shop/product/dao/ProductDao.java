package com.shop.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.product.entity.Product;

public interface ProductDao {

	// 根据cid查询cid下的所有商品
	public List<Product> selectProductByCid(@Param("cid") Integer cid, @Param("begin") Integer begin, @Param("limit") Integer limit);

	// 根据csid查询所属商品
	public List<Product> selectProductByCsid(Integer csid);

	// 根据pid查询商品详细信息
	public Product selectProductByPid(Integer pid);

	// 查询热门商品
	public List<Product> selectProductByHot();
	// 查询最新商品
	public List<Product> selectProductByPdate();
	// 查询全部商品
	public List<Product> selectProductAll(@Param("begin") int begin, @Param("limit") int limit);
	
	
	
	// 添加商品
	public void insertProduct(Product product);
	
	// 删除商品
	public void deleteProductByPid(Integer pid);
	
	// 修改商品
	public void updateProduct(Product product);

	// 根据分类cid查询该分类下的商品总记录数
	public int findProductCountByCid(Integer cid);

	// 查询商品总记录数
	public int findProductCountAll();
}
