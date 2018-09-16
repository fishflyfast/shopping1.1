package com.qsh.shopping.dao;

import java.util.List;

import com.qsh.shopping.model.User;

public interface UserDao {
	/*用户注册*/
	public User save(User user);
	public boolean delete(int id);
	public boolean update(User user);
	public List<User> findAll();
	public boolean login(String name, String password);
	public boolean checkUserName(String username);
}
