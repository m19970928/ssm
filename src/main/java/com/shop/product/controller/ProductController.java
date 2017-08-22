package com.shop.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.category.service.CategoryService;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;
import com.shop.util.PageBean;

/** 
 * 优化：
 * 	1、商品后可添加评论功能
 **/

@Controller
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * 跳转到商品列表页面
	 * 同时根据一级分类的id查询所属商品
	 */
	@RequestMapping("productList")
	public String productListPage(Integer cid, int page, HttpServletRequest request) {
		/*
		 * 根据cid查询所有商品并分页显示
		 */
		PageBean<Product> pageBean = productService.selectProductByCid(cid, page);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		
		return "productList";
	}
	
	/*
	 * 根据二级分类的csid查询所属商品
	 */
	@RequestMapping("selectProductByCsid")
	public String selectProductByCsid(Integer csid, HttpServletRequest request){
		// 根据csid查询商品
		List<Product> productList = productService.selectProductByCsid(csid);
		request.setAttribute("productList", productList);
		return "productList";
	}
	
	/*
	 * 查询商品详细信息
	 */
	@RequestMapping("selectProductByPid")
	public String selectProductByPid(Integer pid, HttpServletRequest request){
		// 根据csid查询商品
		Product product = productService.selectProductByPid(pid);
		request.setAttribute("product", product);
		System.out.println(product);
		return "product";
	}	
	
	
	
}
