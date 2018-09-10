package com.qsh.shopping.util;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateUtil {

	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	/** 获取session
	 * @return
	 */
	public Session getSession(){
		Session session = null;
		try{
			session = sessionFactory.openSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return session;
	}
	
	public void close(){
		try{
			sessionFactory.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void closeSession(Session session){
		try{
			if(null != session){
				session.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 事务回滚
	 * @param transaction
	 */
	public static void rollbackTransaction(Transaction transaction){
		try{
			if(null != transaction){
				transaction.rollback();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 返回一个List集合统一查询方法
	 * @param hql
	 * @return
	 */
	public List exeQuery(String hql){
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try{
			session = getSession();
			transaction = session.beginTransaction();
			
			list = session.createQuery(hql).list();
			transaction.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			HibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/** 分页查询方法
	 * @param hql
	 * @param start 开始位置
	 * @param end 结束位置
	 * @return
	 */
	public List<Object> exeQueryPage(String hql, int start, int end){
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try{
			session = getSession();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			query.setFirstResult(start);
			query.setMaxResults(end);
			list = query.list();
			
			transaction.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			HibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	public boolean exeDelete(String hql){
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try{
			session = getSession();
			transaction = session.beginTransaction();
			
			session.createQuery(hql).executeUpdate();
			transaction.commit();
			flag = true;
		}catch(HibernateException e){
			e.printStackTrace();
			rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return flag;
	}
}
