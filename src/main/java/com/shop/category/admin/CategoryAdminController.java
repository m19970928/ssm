package com.shop.category.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.category.entity.Category;
import com.shop.category.entity.CategorySecond;
import com.shop.category.service.CategoryService;
import com.shop.util.PageBean;

/**
 * 后台管理分类Controller
 * 问题：
 * 1、表单提交过来的数据出现乱码，添加修改一级分类时
 */


@Controller
@RequestMapping("/categoryAdmin")
public class CategoryAdminController {

	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * 查询一级分类
	 */
	@RequestMapping("/selectCategory")
	public String selectCategory(HttpServletRequest request, int page){
		
		// 分页查询所有一级分类
		PageBean<Category> pageBean = categoryService.selectCategoryAll(page);
		request.setAttribute("pageBean", pageBean);
		return "admin/category/list";
	}
	
	
	// 添加一级分类
	@RequestMapping("/insertCategory")
	public String insertCategory(HttpServletRequest request, Category category){
		categoryService.insertCategory(category);
		
		return "forward:/categoryAdmin/selectCategory?page=1";
	}
	
	// 修改一级分类之前查询分类
	@RequestMapping("/selectCategoryByCid")
	public String selectCategoryByCid(HttpServletRequest request, Integer cid){
		Category category = categoryService.selectCategoryByCid(cid);
		request.setAttribute("category", category);
		return "admin/category/edit";
	}
	
	// 修改一级分类
	@RequestMapping("/updateCategory")
	public String updateCategory(HttpServletRequest request, Category category, Integer cid, String cname){
		categoryService.updateCategory(category);
		
		return "forward:/categoryAdmin/selectCategory?page=1";
	}
	
	// 删除一级分类同时删除二级分类
	@RequestMapping("/deleteCategory")
	public String deleteCategory(HttpServletRequest request, Integer cid){
		categoryService.deleteCategory(cid);
		
		return "forward:/categoryAdmin/selectCategory?page=1";
	}
	
	// 跳转到添加一级分类页面
	@RequestMapping("/addCategory")
	public String addCategory(){
		return "admin/category/add";
	}
	
	
	// 查询全部二级分类
	@RequestMapping("/selectCategoryAll")
	public String selectCategoryAll(HttpServletRequest request, int page){
		// 查询全部二级分类
		PageBean<CategorySecond> pageBean = categoryService.selectCategorySecondsAll(page);
		request.setAttribute("pageBean", pageBean);
		
		return "admin/categorysecond/list";
	}
	
	// 跳转到添加页面，同时查询一级分类的信息用于添加
	@RequestMapping("/addCategorySecond")
	public String addCategorySecond(HttpServletRequest request){
		List<Category> categoryList = categoryService.selectCategoryAll();
		request.setAttribute("categoryList", categoryList);
		return "admin/categorysecond/add";
	}
	
	// 添加二级分类
	@RequestMapping("/insertCategorySecond")
	public String insertCategorySecond(HttpServletRequest request, CategorySecond categorySecond){
		categoryService.insertCategorySecond(categorySecond);
		
		return "forward:/categoryAdmin/selectCategoryAll?page=1";
	}
	
	// 根据csid查询二级分类
	@RequestMapping("/selectCategorySecondByCsid")
	public String selectCategorySecondByCsid(HttpServletRequest request, Integer csid){
		CategorySecond categorySecond = categoryService.selectCategorySecondByCsid(csid);
		request.setAttribute("categorySecond", categorySecond);
		
		return "admin/categorysecond/edit";
	}
	
	
	// 修改二级分类
	@RequestMapping("/updateCategorySecond")
	public String updateCategorySecond(HttpServletRequest request, CategorySecond categorySecond){
		categoryService.updateCategorySecond(categorySecond);
		
		return "forward:/categoryAdmin/selectCategoryAll?page=1";
	}
	
	
	
	// 删除二级分类
	@RequestMapping("/deleteCategorySecond")
	public String deleteCategorySecond(HttpServletRequest request, Integer csid){
		categoryService.deleteCategorySecond(csid);
		
		return "forward:/categoryAdmin/selectCategoryAll?page=1";
	}
	
	
}
