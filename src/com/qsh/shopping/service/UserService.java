package com.qsh.shopping.service;

import java.util.List;

import com.qsh.shopping.model.User;

public interface UserService {
	/*用户注册*/
	public User register(User person);
	public boolean remove(int[] ids);
	public boolean modify(User person);
	public List<User> getPersons();
	public boolean login(String name, String password);
	public boolean checkUserName(String checkUserName);
}
