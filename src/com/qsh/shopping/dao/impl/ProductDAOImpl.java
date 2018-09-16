package com.qsh.shopping.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qsh.shopping.dao.ProductDao;
import com.qsh.shopping.model.Product;
import com.qsh.shopping.util.HibernateUtil;

public class ProductDAOImpl implements ProductDao {
	
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}
	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		String hql = "delete from Product where id="+id;
		return hibernateUtil.exeDelete(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Product";
		return hibernateUtil.exeQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		String hql ="";
		if(null != keyword){
			hql = "from Product where name like '%"+keyword+"%' or descr like '%"+keyword+"%'";
		}
		return hibernateUtil.exeQuery(hql);
	}

	public Product save(Product product) {
		// TODO Auto-generated method stub
		Product p = null;
		Transaction transaction = null;
		Session session  = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			p=(Product)session.load(Product.class, session.save(product));
			
			transaction.commit();
		}catch(HibernateException he){
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			hibernateUtil.closeSession(session);
		}
		return p;
	}

	public boolean update(Product product) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Transaction transaction = null;
		Session session  = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			Product p=(Product)session.load(Product.class, product.getId());
			if(null != product.getName()){
				p.setName(product.getName());
			}
			if(null != product.getDescr()){
				p.setDescr(product.getDescr());
			}
			if(product.getNormaPrice() != 0){
				p.setNormaPrice(product.getNormaPrice());
			}
			if(product.getMemberPrice() != 0){
				p.setMemberPrice(product.getMemberPrice());
			}
			if(product.getCategory() != null){
				p.setCategory(product.getCategory());
			}
			if(product.getPhoto() != null){
				p.setPhoto(product.getPhoto());
			}
			
			transaction.commit();
			flag = true;
		}catch(HibernateException he){
			flag = false;
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}finally{
			hibernateUtil.closeSession(session);
		}
		return flag;
	}

}
