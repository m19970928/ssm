package com.shop.category.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.category.entity.Category;
import com.shop.category.entity.CategorySecond;

public interface CategoryDao {

	// 不分页查询全部一级分类包括二级分类
	public List<Category> selectCategoryAll();

	// 分页查询全部一级分类
	public List<Category> selectCategory(@Param("begin") int begin, @Param("limit") int limit);
	
	// 根据cid查询一级分类
	public Category selectCategoryByCid(Integer cid);
	
	// 添加一级分类
	public void insertCategory(Category category);
	
	// 修改一级分类
	public void updateCategory(Category category);
	
	// 删除一级分类
	public void deleteCategory(Integer cid);
	
	// 根据cid删除二级分类
	public void deleteCategorySecondByCid(Integer cid);
	
	// 根据csid删除二级分类
	public void deleteCategorySecondByCsid(Integer csid);

	// 分页查询全部二级分类
	public List<CategorySecond> selectCategorySeconds(@Param("begin") int begin, @Param("limit") int limit);
	
	// 不分页查询所有二级分类
	public List<CategorySecond> selectCategorySecondsAll();
		
	// 添加二级分类
	public void insertCategorySecond(CategorySecond categorySecond);
	
	// 修改二级分类
	public void updateCategorySecond(CategorySecond categorySecond);
	
	// 根据csid查询二级分类
	public CategorySecond selectCateogrySecondByCsid(Integer csid);

	// 查询一级分类总数
	public int findCategoryCount();
	
	// 查询二级分类总数
	public int findCategorySecondCount();

	
}


