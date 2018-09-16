package com.qsh.shopping.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 产品类
 * @author 邱盛华
 *
 */
@Entity
@Table(name="t_product")
public class Product {
	/**
	 * 产品编号
	 */
	private int id;
	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 产品描述
	 */
	private String descr;
	/**
	 * 市场价
	 */
	private double normaPrice;
	/**
	 * 会员价
	 */
	private double memberPrice;
	/**
	 * 上货日期
	 */
	private Timestamp pdate;
	/**
	 * 所属类别(多对一的关系)
	 */
	private Category category;
	/**
	 * 照片
	 */
	private String photo;
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public double getNormaPrice() {
		return normaPrice;
	}
	public void setNormaPrice(double normaPrice) {
		this.normaPrice = normaPrice;
	}
	public double getMemberPrice() {
		return memberPrice;
	}
	public void setMemberPrice(double memberPrice) {
		this.memberPrice = memberPrice;
	}
	public Timestamp getPdate() {
		return pdate;
	}
	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}
	@ManyToOne
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
