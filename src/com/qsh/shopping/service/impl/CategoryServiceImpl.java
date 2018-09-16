package com.qsh.shopping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.qsh.shopping.dao.CategoryDao;
import com.qsh.shopping.model.Category;
import com.qsh.shopping.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;
	
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public boolean addChildCategory(int parent, Category category) {
		this.categoryDao.saveChildCategory(parent, category);
		return true;
	}

	@Override
	public boolean addTopCategory(String text, String description) {
		Category category = new Category();
		category.setText(text);
		category.setDescription(description);
		category.setParent(0);
		category.setLeaf(true);
		this.categoryDao.save(category);
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		List<Category> list = this.categoryDao.findByparent(id);
		if(list.size() > 0){
			for(Category c : list){
				deleteById(c.getId());
			}
		}
		return this.categoryDao.delete(id);
	}

	@Override
	public boolean update(Category category) {
		return this.categoryDao.update(category);
	}

	@Override
	public List<Category> findAll() {
		return this.categoryDao.findAll();
	}

	@Override
	public List<Category> findTopAll() {
		return this.categoryDao.findTopCategory();
	}

}
