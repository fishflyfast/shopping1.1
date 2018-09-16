package com.qsh.shopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 销售项实体类
 * @author 邱盛华
 * 2012－11－23 15:44
 */
@Entity
@Table(name="t_saleitem")
public class SaleItem {
	private int id;
	/**
	 * 产品编号
	 */
	private int productId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 购买数量
	 */
	private int number;
	/**
	 * 购买价格
	 */
	private double price;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
