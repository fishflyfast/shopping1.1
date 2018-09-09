package com.qsh.shopping.util;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static Configuration configuration;
	private static SessionFactory sessionFactory;
	
	static{
		try{
			configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		}catch(Throwable ex){
			ex.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static Configuration getConfiguration(){
		return configuration;
	}
	public static void rebuildSessionFactory(){
		synchronized(sessionFactory){
			try{
				sessionFactory = getConfiguration().buildSessionFactory();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/** 获取session
	 * @return
	 */
	public static Session getSession(){
		Session session = null;
		try{
			session = sessionFactory.openSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return session;
	}
	
	public static void close(){
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
	public static List exeQuery(String hql){
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			list = session.createQuery(hql).list();
			transaction.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			HibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession(session);
		}
		return list;
	}
	
	/** 分页查询方法
	 * @param hql
	 * @param start 开始位置
	 * @param end 结束位置
	 * @return
	 */
	public static List<Object> exeQueryPage(String hql, int start, int end){
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			query.setFirstResult(start);
			query.setMaxResults(end);
			list = query.list();
			
			transaction.commit();
			HibernateUtil.closeSession(session);
		}catch(HibernateException e){
			e.printStackTrace();
			HibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession(session);
		}
		return list;
	}
	
	public static boolean exeDelete(String hql){
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			session.createQuery(hql).executeUpdate();
			transaction.commit();
			flag = true;
		}catch(HibernateException e){
			e.printStackTrace();
			HibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession(session);
		}
		return flag;
	}
}
