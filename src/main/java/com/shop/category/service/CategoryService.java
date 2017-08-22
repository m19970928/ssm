package com.shop.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.category.dao.CategoryDao;
import com.shop.category.entity.Category;
import com.shop.category.entity.CategorySecond;
import com.shop.product.entity.Product;
import com.shop.util.PageBean;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	// 不分页查询全部一级分类，包括二级分类
	public List<Category> selectCategoryAll(){
		return categoryDao.selectCategoryAll();
	}
	
	// 分页查询全部一级分类
	public PageBean<Category> selectCategoryAll(int page){
		PageBean<Category> pageBean = new PageBean<Category>();
		pageBean.setPage(page);// 设置当前页数
		// 设置每页显示数量
		int limit = 4 ; 
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = categoryDao.findCategoryCount();
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
		List<Category> productList = categoryDao.selectCategory(begin, limit);
		pageBean.setList(productList);
		return pageBean;
		
		
	}
	
	// 根据cid查询一级分类
	public Category selectCategoryByCid(Integer cid){
		return categoryDao.selectCategoryByCid(cid);
	}
	
	// 添加一级分类
	public void insertCategory(Category category){
		categoryDao.insertCategory(category);
	}
	
	// 删除一级分类
	public void deleteCategory(Integer cid){
		// 首先删除所有的二级分类
		categoryDao.deleteCategorySecondByCid(cid);
		// 再删除一级分类
		categoryDao.deleteCategory(cid);
	}
	
	// 修改一级分类
	public void updateCategory(Category category){
		categoryDao.updateCategory(category);
	}

	// 分页查询全部二级分类
	public PageBean<CategorySecond> selectCategorySecondsAll(int page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);// 设置当前页数
		// 设置每页显示数量
		int limit = 8 ; 
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = categoryDao.findCategorySecondCount();
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
		List<CategorySecond> productList = categoryDao.selectCategorySeconds(begin, limit);
		pageBean.setList(productList);
		return pageBean;
		
	}
	
	// 不分页查询所有二级分类
	public List<CategorySecond> selectCategorySecondsAll(){
		return categoryDao.selectCategorySecondsAll();
	}
	
	// 根据csid删除二级分类
	public void deleteCategorySecond(Integer csid){
		categoryDao.deleteCategorySecondByCsid(csid);
	}
	
	// 根据csid查询二级分类
	public CategorySecond selectCategorySecondByCsid(Integer csid){
		return categoryDao.selectCateogrySecondByCsid(csid);
	}
	
	// 修改二级分类
	public void updateCategorySecond(CategorySecond categorySecond){
		categoryDao.updateCategorySecond(categorySecond);
	}
	
	// 添加二级分类
	public void insertCategorySecond(CategorySecond categorySecond){
		categoryDao.insertCategorySecond(categorySecond);
	}
}
