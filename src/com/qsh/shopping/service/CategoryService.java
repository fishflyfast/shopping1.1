package com.qsh.shopping.service;

import java.util.List;

import com.qsh.shopping.model.Category;
import com.qsh.shopping.util.QshException;

public interface CategoryService {
	/*添加子分类*/
	public boolean addChildCategory(int parent, Category category);
	/*添加根分类*/
	public boolean addTopCategory(String text, String description);
	public boolean deleteById(int id);
	public boolean update(Category category);
	public List<Category> findAll();
	public List<Category> findTopAll();
	/**根据id来查询*/
	public List<Category> getCategories(int id);
	/*根据ID和父ID删除*/
	public boolean deleteById(int id, int pid) throws QshException;
}
