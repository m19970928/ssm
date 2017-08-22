package com.shop.index.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.category.entity.Category;
import com.shop.category.service.CategoryService;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;


@Controller	
public class IndexController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping("/index")
	public String index(HttpSession session){
		/*
		 * 1、查询最热商品按时间取前10
		 * 2、查询最新商品按时间取前10
		 * 3、查询所有分类
		 * 4、向Session中保存信息
		 */
		List<Product> productHot = productService.selectProductByHot();
		List<Product> productPdate = productService.selectProductByPdate();
		List<Category> categoryList = categoryService.selectCategoryAll();
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("productHot", productHot);
		session.setAttribute("productPdate", productPdate);
		return "index";
	}
	
	
}
