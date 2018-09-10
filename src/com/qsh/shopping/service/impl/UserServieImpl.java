package com.qsh.shopping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.qsh.shopping.dao.UserDao;
import com.qsh.shopping.model.User;
import com.qsh.shopping.service.UserService;

public class UserServieImpl implements UserService {

	UserDao userDao;
	
	@Override
	public User register(User user) {
		if(this.checkUserName((user.getName()))){
			return null;
		}
		return userDao.save(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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
	public List<User> getUsers() {
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
