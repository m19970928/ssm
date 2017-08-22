package com.shop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.product.dao.ProductDao;
import com.shop.product.entity.Product;
import com.shop.util.PageBean;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	
	// 根据cid分页查询所属商品
	public PageBean<Product> selectProductByCid(Integer cid, int page){
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);// 设置当前页数
		// 设置每页显示数量
		int limit = 8 ; 
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.findProductCountByCid(cid);
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
		List<Product> productList = productDao.selectProductByCid(cid, begin, limit);
		pageBean.setList(productList);
		return pageBean;
	}
	
	// 根据csid查询所属商品
	public List<Product> selectProductByCsid(Integer csid){
		return productDao.selectProductByCsid(csid);
	}
	
	// 根据csid查询所属商品
	public Product selectProductByPid(Integer pid){
		return productDao.selectProductByPid(pid);
	}
	
	// 查询热门商品
	public List<Product> selectProductByHot(){
		return productDao.selectProductByHot();
	}
	
	// 查询最新商品
	public List<Product> selectProductByPdate(){
		return productDao.selectProductByPdate();
	}
	
	
	// 查询全部商品
	public PageBean<Product> selectProductAll(int page){
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);// 设置当前页数
		// 设置每页显示数量
		int limit = 5 ; 
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.findProductCountAll();
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
		List<Product> productList = productDao.selectProductAll(begin, limit);
		pageBean.setList(productList);
		return pageBean;
	}
	
	// 添加商品
	public void insertProduct(Product product){
		productDao.insertProduct(product);
	}
	
	// 删除商品
	public void deleteProductByPid(Integer pid){
		productDao.deleteProductByPid(pid);
	}
	
	// 修改商品
	public void updateProduct(Product product){
		productDao.updateProduct(product);
	}

	
	
}
