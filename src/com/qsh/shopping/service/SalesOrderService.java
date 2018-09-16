package com.qsh.shopping.service;

import java.util.List;

import com.qsh.shopping.model.SalesOrder;

public interface SalesOrderService {
	/**保存定单*/
	public boolean add(SalesOrder order);
	/**作废*/
	public boolean remove(SalesOrder order);
	/**查询所有*/
	public List<SalesOrder> findALL();
	/**根据id查询*/
	public List<SalesOrder> findALL(String id);
	/**分页查询所有*/
	public List<SalesOrder> findALL(int start,int end);
	/**根据定单编号来查询*/
	public List<SalesOrder> findByOderCode(String orderCode);
	/**根据用户来查询*/
	public List<SalesOrder> findByOderUser(String username)throws IndexOutOfBoundsException;
	/**审核定单*/
	public boolean verify(int id);
}
