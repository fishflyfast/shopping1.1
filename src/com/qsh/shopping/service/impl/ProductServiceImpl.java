package com.qsh.shopping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.qsh.shopping.dao.ProductDao;
import com.qsh.shopping.model.Product;
import com.qsh.shopping.model.User;
import com.qsh.shopping.service.ProductService;

public class ProductServiceImpl implements ProductService {
	ProductDao ProductDao;
	
	public ProductDao getProductDao() {
		return ProductDao;
	}
	@Resource
	public void setProductDao(ProductDao ProductDao) {
		this.ProductDao = ProductDao;
	}

	public Product add(Product product) {
		// TODO Auto-generated method stub
		return ProductDao.save(product);
	}

	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return ProductDao.findAll();
	}

	public List<Product> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return ProductDao.findByKeyword(keyword);
	}

	public boolean modify(Product product) {
		// TODO Auto-generated method stub
		return ProductDao.update(product);
	}

	public boolean remove(String[] ids) {
		// TODO Auto-generated method stub
		boolean flag = false;
		for(int i=0;i<ids.length;i++){
			flag = ProductDao.delete(Integer.parseInt(ids[i]));
		}
		return flag;
	}
	public long count() {
		// TODO Auto-generated method stub
		return findAll().size();
	}

}
