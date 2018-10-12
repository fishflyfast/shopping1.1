package com.qsh.shopping.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qsh.shopping.dao.CategoryDao;
import com.qsh.shopping.model.Category;
import com.qsh.shopping.util.HibernateUtil;
import com.qsh.shopping.util.QshException;

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
	public List<Category> findByParent(int pId) {
		String hql = "from Category where parent=" + pId;
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Category> findTopCategory() {
		String hql = "from Category where parent=0";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public void getCategories(List<Category> list, int id) {
		// TODO Auto-generated method stub
		
		Session session = hibernateUtil.getSession();
		session.beginTransaction();
		
            Query query = session.createQuery("from Category where parent='"+id+"'");
            
            List<Category> categories = query.list();
            for(Category category:categories){
            	list.add(category);
            	if(category.isLeaf()){
            		getCategories(list,category.getId());
            		//这样list就是一棵树,相当于深度查询
            	}
            }

		session.getTransaction().commit();
		hibernateUtil.closeSession(session);
	}

	@Override
	public boolean updateCategoryToLeaf(int id) throws QshException{
		// TODO Auto-generated method stub
		boolean flag = false;
		Transaction transaction = null;
		Session session  = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			Category category = (Category)session.load(Category.class, id);
			category.setLeaf(true);
			session.saveOrUpdate(category);
			
			transaction.commit();
			flag = true;
		}catch(ObjectNotFoundException o){
			throw new QshException("所有记录全部删除完毕");
		}catch(HibernateException he){
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
			flag = false;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}finally{
			hibernateUtil.closeSession(session);
		}
		return flag;
	}

}
