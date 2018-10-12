package com.qsh.shopping.service;

import java.util.List;

import com.qsh.shopping.model.User;

public interface UserService {
	/*用户注册*/
	public User register(User user);
	public boolean remove(int[] ids);
	public boolean modify(User user);
	public List<User> getUsers();
	public List<User> getUsers(int start, int end);
	public User login(String name, String password);
	public boolean checkUserName(String checkUserName);
}
