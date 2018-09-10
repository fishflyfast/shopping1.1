package com.qsh.shopping.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qsh.shopping.dao.UserDao;
import com.qsh.shopping.model.User;
import com.qsh.shopping.service.UserService;

public class TestUserServiceImpl {

	private ApplicationContext ctx;
	private UserDao userDao;
	
	@Before
	public void beforeTest(){
		ctx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
	}
	@Test
	public void testRegister(){
		UserService userService = (UserService)ctx.getBean("userService");
		
		User user = new User();
		user.setName("博博fsdfs");
		user.setPassword("meiyou");
		userService.register(user);
		
		System.out.println(user);
	}
}
