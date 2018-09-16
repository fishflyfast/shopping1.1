package com.qsh.shopping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.qsh.shopping.dao.SalesOrderDAO;
import com.qsh.shopping.model.SalesOrder;
import com.qsh.shopping.service.SalesOrderService;

public class SalesOrderServiceImpl implements SalesOrderService {
	
	private SalesOrderDAO salesOrderDao;

	public SalesOrderDAO getSalesOrderDao() {
		return salesOrderDao;
	}
	@Resource
	public void setSalesOrderDao(SalesOrderDAO salesOrderDao) {
		this.salesOrderDao = salesOrderDao;
	}

	public boolean add(SalesOrder order) {
		// TODO Auto-generated method stub
		return salesOrderDao.save(order);
	}

	public List<SalesOrder> findALL() {
		// TODO Auto-generated method stub
		return salesOrderDao.findALL();
	}
	
	public List<SalesOrder> findALL(int start, int end) {
		// TODO Auto-generated method stub
		return salesOrderDao.findALL(start,end);
	}
	
	public List<SalesOrder> findByOderCode(String orderCode){
		return salesOrderDao.findByOderCode(orderCode);
	}

	/**作废*/
	public boolean remove(SalesOrder order) {
		// TODO Auto-generated method stub
		return salesOrderDao.delete(order);
	}

	public boolean verify(int id) {
		// TODO Auto-generated method stub
		return salesOrderDao.verify(id);
	}
	public List<SalesOrder> findByOderUser(String username)throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return salesOrderDao.findByOderUser(username);
	}
	public List<SalesOrder> findALL(String id) {
		// TODO Auto-generated method stub
		return salesOrderDao.findALL(id);
	}

}
