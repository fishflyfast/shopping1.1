package com.qsh.shopping.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qsh.shopping.dao.CategoryDao;
import com.qsh.shopping.model.Category;
import com.qsh.shopping.model.User;
import com.qsh.shopping.util.HibernateUtil;

public class CategoryDaoImpl implements CategoryDao {

	private HibernateUtil hibernateUtil;
	
	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
	@Override
	public Category save(Category category) {
		Transaction transaction = null;
		Session session = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			/*教程中可以这样干,实际操作不行*/
//			u = (User)session.save(user);
			Integer id = (Integer)session.save(category);
			category.setId(id);
			
			transaction.commit();
		}catch(HibernateException he){
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			hibernateUtil.closeSession(session);
		}
		return category;
	}
	
	@Override
	public Category saveChildCategory(int parent, Category category) {
		Transaction transaction = null;
		Session session = null;
		boolean flag = false;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			Category pC = (Category)session.get(Category.class, parent);
			pC.setLeaf(false);
			category.setParent(parent);
			this.save(category);
			
			transaction.commit();
			flag = true;
		}catch(HibernateException he){
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			hibernateUtil.closeSession(session);
		}
		return category;
	}

	@Override
	public boolean delete(int id) {
		String hql = "delete from Category where id=" + id;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean update(Category category) {
		boolean flag = false;
		Category c = null;
		Transaction transaction = null;
		Session session = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();

			c = (Category)session.load(Category.class, category.getId());
			c.setDescription(category.getDescription());
			c.setText(category.getText());
			c.setParent(category.getParent());
			
			transaction.commit();
			flag = true;
		}catch(HibernateException he){
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			hibernateUtil.closeSession(session);
		}
		return flag;
	}

	@Override
	public List<Category> findAll() {
		String hql = "from Category";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Category> findByparent(int pId) {
		String hql = "from Category where parent=" + pId;
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Category> findTopCategory() {
		String hql = "from Category where parent=0";
		return hibernateUtil.exeQuery(hql);
	}

}
