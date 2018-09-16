package com.qsh.shopping.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qsh.shopping.model.Category;
import com.qsh.shopping.service.CategoryService;

public class TestCategoryServiceImpl {

	private ApplicationContext ctx;
	private CategoryService categoryService;
	
	@Before
	public void beforeTest(){
		ctx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		categoryService = (CategoryService)ctx.getBean("categoryService");
	}
	
	@Test
	public void testAddTopCategory(){
		
		String text = "所有分类";
		String description = "所有分类的描述";
		this.categoryService.addTopCategory(text, description);
	}
	
	@Test
	public void testAddChildCategory(){
		Category category = new Category();
		category.setDescription("赵克敏");
		category.setText("zkm");
		category.setLeaf(true);
		this.categoryService.addChildCategory(6, category);
	}
}
