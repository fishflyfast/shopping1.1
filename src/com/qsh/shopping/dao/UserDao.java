package com.qsh.shopping.dao;

import java.util.List;

import com.qsh.shopping.model.User;

public interface UserDao {
	/*用户注册*/
	public User save(User person);
	public boolean delete(int id);
	public boolean update(User person);
	public List<User> findAll();
	public boolean login(String name, String password);
	public boolean checkUserName(String username);
}
