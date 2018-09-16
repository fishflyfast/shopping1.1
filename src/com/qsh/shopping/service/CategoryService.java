package com.qsh.shopping.service;

import java.util.List;

import com.qsh.shopping.model.Category;

public interface CategoryService {
	/*添加子分类*/
	public boolean addChildCategory(int parent, Category category);
	/*添加根分类*/
	public boolean addTopCategory(String text, String description);
	public boolean deleteById(int id);
	public boolean update(Category category);
	public List<Category> findAll();
	public List<Category> findTopAll();
}
