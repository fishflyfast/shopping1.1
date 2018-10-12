package com.qsh.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.qsh.shopping.dao.CategoryDao;
import com.qsh.shopping.model.Category;
import com.qsh.shopping.service.CategoryService;
import com.qsh.shopping.util.QshException;

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
		List<Category> list = this.categoryDao.findByParent(id);
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

	@Override
	public List<Category> getCategories(int id) {
		List<Category> list = new ArrayList<Category>();
		categoryDao.getCategories(list, id);
		return list;
	}

	@Override
	public boolean deleteById(int id, int pid){
		// TODO Auto-generated method stub
		List<Category> list = this.findByParent(id);
		 if(list.size()>0){
         	for(Category c:list){
         		deleteById(c.getId(),c.getParent());
         	}
         }else{//删除子节点完成，设置父节为叶子
        	 //如果还有兄弟就不改变
        	// System.out.println("num:"+this.findByParent(pid).size());
        	 if(this.findByParent(pid).size()<=1){
        		 try{
        			 this.setCategoryToLeaf(pid);
        		 }catch(QshException qe){
        			 System.out.println(qe);
        		 }
        	 }
         }
		return categoryDao.delete(id);
	}
	
	private List<Category> findByParent(int pid) {
		// TODO Auto-generated method stub
		return categoryDao.findByParent(pid);
	}
	
	/**修改为叶子，为deleteById作用的*/
	private boolean setCategoryToLeaf(int id) throws QshException{
		return categoryDao.updateCategoryToLeaf(id);
	}

}
