package com.qsh.shopping.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 定单实体类
 * @author 邱盛华
 * 2012－11－23 15:08
 */
@Entity
@Table(name="t_salesorder")
public class SalesOrder {
	private int id;
	/**
	 * 定单编号
	 * 格式为201211230001,201211230002
	 */
	private String orderCode;
	/**
	 * 用户名（那位用户下的单子）
	 */
	private String user;//userid int
	/**用户的电话号码*/
	private String phone;//
	/**用户的QQ号码*/
	private String QQ;
	/**
	 * 送货地址
	 */
	private String addr;
	/**
	 * 定单日期
	 */
	private java.sql.Timestamp odate;
	/**
	 * 定单状态：有效\无效
	 */
	private boolean status;
	/**是否为作废单子 true表示为有效，false表示为作废*/
	private boolean nullify = true;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 一个订单包含那些项
	 * 一对多的关系
	 */
	private Set<SaleItem> saleItems = new HashSet<SaleItem>();
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qq) {
		QQ = qq;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public java.sql.Timestamp getOdate() {
		return odate;
	}
	public void setOdate(java.sql.Timestamp odate) {
		this.odate = odate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isNullify() {
		return nullify;
	}
	public void setNullify(boolean nullify) {
		this.nullify = nullify;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="salesOrderId")
	public Set<SaleItem> getSaleItems() {
		return saleItems;
	}
	public void setSaleItems(Set<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}
	
	
}
