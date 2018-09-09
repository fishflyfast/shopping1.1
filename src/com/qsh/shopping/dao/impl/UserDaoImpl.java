package com.qsh.shopping.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qsh.shopping.dao.UserDao;
import com.qsh.shopping.model.User;
import com.qsh.shopping.util.HibernateUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public User save(User user) {
		User u = null;
		Transaction transaction = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			/*教程中可以这样干,实际操作不行*/
//			u = (User)session.save(user);
			Integer id = (Integer)session.save(user);
			user.setId(id);
			
			transaction.commit();
		}catch(HibernateException he){
			he.printStackTrace();
			HibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession(session);
		}
		return user;
	}

	@Override
	public boolean delete(int id) {
		String hql = "delete from User where id=" + id;
		return HibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean update(User user) {
		boolean flag = false;
		User u = null;
		Transaction transaction = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
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
			HibernateUtil.closeSession(session);
			flag = true;
		}catch(HibernateException he){
			he.printStackTrace();
			HibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession(session);
		}
		return flag;
	}

	@Override
	public List<User> findAll() {
		String hql = "from User";
		return HibernateUtil.exeQuery(hql);
	}

	@Override
	public boolean login(String name, String password) {
		String hql = "from User name=" + name + " and password=" + password;
		if(HibernateUtil.exeQuery(hql).size() > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUserName(String username) {
		String hql = "from User where name='" + username + "'";
		List list = HibernateUtil.exeQuery(hql);
		return !list.isEmpty();
	}

}
