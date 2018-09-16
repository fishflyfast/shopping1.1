package com.qsh.shopping.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 产品分类
 * @author 邱盛华
 *
 */
@Entity
@Table(name="t_category")
public class Category {
	/**
	 * 分类的id
	 */
	private int id;
	/**
	 * 分类名称
	 */
	private String text;
	/**
	 * 分类描述
	 */
	private String description;
	/**
	 * 父节点的id
	 */
	private int parent;//
	/**
	 * true为叶子,false为非叶子
	 */
	private boolean leaf = true;
	/**
	 * 一个类中包含的产品(一对多的关系)
	 */
	private Set<Product> products = new HashSet<Product>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	@OneToMany(mappedBy="category")
	@Cascade(CascadeType.ALL)//级连删除
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
