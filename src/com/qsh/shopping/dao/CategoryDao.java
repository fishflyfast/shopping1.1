package com.qsh.shopping.dao;

import java.util.List;

import com.qsh.shopping.model.Category;
import com.qsh.shopping.model.User;

/**
 * 产品分类数据访问对象接口
 * @author Administrator
 *
 */
public interface CategoryDao {
	/*用户注册*/
	public Category save(Category category);
	public boolean delete(int id);
	public boolean update(Category category);
	public List<Category> findAll();
	public Category saveChildCategory(int parent, Category category);
	/*根据父节点ID查询子节点*/
	public List<Category> findByparent(int pId);
	/*查询根分类信息*/
	public List<Category> findTopCategory();
}
