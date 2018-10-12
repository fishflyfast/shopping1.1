package com.qsh.shopping.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qsh.shopping.dao.UserDao;
import com.qsh.shopping.model.User;
import com.qsh.shopping.util.HibernateUtil;

public class UserDaoImpl implements UserDao{

	private HibernateUtil hibernateUtil;
	
	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	@Override
	public User save(User user) {
		Transaction transaction = null;
		Session session = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			/*教程中可以这样干,实际操作不行*/
//			u = (User)session.save(user);
			Integer id = (Integer)session.save(user);
			user.setId(id);
			
			transaction.commit();
		}catch(HibernateException he){
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			hibernateUtil.closeSession(session);
		}
		return user;
	}

	@Override
	public boolean delete(int id) {
		String hql = "delete from User where id=" + id;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean update(User user) {
		boolean flag = false;
		User u = null;
		Transaction transaction = null;
		Session session = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();

			u = (User)session.load(User.class, user.getId());
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			u.setAddr(user.getAddr());
			u.setEmail(user.getEmail());
			u.setPhone(user.getPhone());
			u.setQQ(user.getQQ());
			u.setRegDate(user.getRegDate());
			
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
	public List<User> findAll() {
		String hql = "from User";
		return hibernateUtil.exeQuery(hql);
	}
	
	@Override
	public List<User> findAll(int start, int end) {
		String hql = "from User";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public User login(String name, String password) {
		String hql = "from User where name='" + name + "' and password='" + password + "'";
		User user = null;
		List<User> users = hibernateUtil.exeQuery(hql);
		if(users.size() == 1){
			user = users.get(0);
		}
		return user;
	}

	@Override
	public boolean checkUserName(String username) {
		String hql = "from User where name='" + username + "'";
		List list = hibernateUtil.exeQuery(hql);
		return !list.isEmpty();
	}
}
