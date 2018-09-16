package com.qsh.shopping.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qsh.shopping.dao.SalesOrderDAO;
import com.qsh.shopping.model.SaleItem;
import com.qsh.shopping.model.SalesOrder;
import com.qsh.shopping.util.HibernateUtil;

public class SalesOrderDAOImpl implements SalesOrderDAO {
	
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}
	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
	
	public boolean delete(SalesOrder order) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Transaction transaction = null;
		Session session  = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			SalesOrder o=(SalesOrder)session.load(SalesOrder.class, order.getId());
			//false表示作废
			o.setNullify(false);
			
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

	public List<SalesOrder> findALL() {
		// TODO Auto-generated method stub
		String hql = "from SalesOrder";
		return hibernateUtil.exeQuery(hql);
	}
	
	public List<SalesOrder> findALL(int start, int end){
		String hql = "from SalesOrder";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}
	
	public List<SalesOrder> findByOderCode(String orderCode){
		String hql = "from SalesOrder where orderCode='"+orderCode+"'";
		return hibernateUtil.exeQuery(hql);
	}

	public boolean save(SalesOrder order) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Transaction transaction = null;
		Session session  = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			session.save(order);
			
			Set<SaleItem> sis = order.getSaleItems();
			for(SaleItem si:sis){
				session.save(si);
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

	public boolean verify(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Transaction transaction = null;
		Session session  = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			SalesOrder o=(SalesOrder)session.load(SalesOrder.class, id);
			//true表示已审核
			o.setStatus(true);
			
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
	public List<SalesOrder> findByOderUser(String username) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub
		String hql = "from SalesOrder where user='"+username+"'";
		return hibernateUtil.exeQuery(hql);
	}
	public List<SalesOrder> findALL(String id) {
		// TODO Auto-generated method stub
		String hql = "from SalesOrder where id="+id;
		return hibernateUtil.exeQuery(hql);
	}

}
