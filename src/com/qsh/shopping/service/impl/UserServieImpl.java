package com.qsh.shopping.service.impl;

import java.util.List;

import com.qsh.shopping.dao.UserDao;
import com.qsh.shopping.dao.impl.UserDaoImpl;
import com.qsh.shopping.model.User;
import com.qsh.shopping.service.UserService;

public class UserServieImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	@Override
	public User register(User user) {
		return userDao.save(user);
	}

	@Override
	public boolean remove(int[] ids) {
		boolean flag = false;
		for(int i = 0;i < ids.length; i++){
			flag = userDao.delete(ids[i]);
		}
		return flag;
	}

	@Override
	public boolean modify(User user) {
		return userDao.update(user);
	}

	@Override
	public List<User> getPersons() {
		return userDao.findAll();
	}

	@Override
	public boolean login(String name, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkUserName(String userName) {
		return userDao.checkUserName(userName);
	}

}
