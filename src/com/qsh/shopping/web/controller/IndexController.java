package com.qsh.shopping.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qsh.shopping.model.Category;
import com.qsh.shopping.service.CategoryService;

@Controller
public class IndexController {

	private CategoryService categoryService;
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	@Resource
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception{
		List<Category> categoryList = categoryService.findAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", categoryList);
		return mav;
	} 
}
