package com.qsh.shopping.dao.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qsh.shopping.dao.UserDao;
import com.qsh.shopping.model.User;

public class TestUserDaoImpl {

	UserDao userDao = null;
	User user = null;
	
	@Before
	public void beforeTest(){
		userDao = new UserDaoImpl();
		user = new User();
	}
	
	@Test
	public void testSave(){
		user.setName("王迪");
		user.setPassword("none///");
		
		System.out.println(userDao.save(user).getId());
		System.out.println("user created successfully.");
	}
	
	@Test
	public void testCheckUserName(){
		System.out.println(userDao.checkUserName("赵1克敏"));
	}
	
	@Test
	public void testDelete(){
		System.out.println(userDao.delete(5));
	}
	
	@Test
	public void testFindAll(){
		List list = userDao.findAll();
		for(int i = 0;i < list.size(); i++){
			User user = (User)list.get(i);
			System.out.println(user.getId() + user.getName());
		}
	}
}
