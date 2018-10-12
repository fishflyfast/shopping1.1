package com.qsh.shopping.dao;

import java.util.List;

import com.qsh.shopping.model.Product;

public interface ProductDao {

	public Product save(Product product);
	public boolean delete(int id);
	public boolean update(Product product);
	public List<Product> findAll();
	public List<Product> findByKeyword(String keyword);
	public List<Product> findAll(int start, int end);
}
