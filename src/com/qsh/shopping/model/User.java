package com.qsh.shopping.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 用户实体类
 */
@Entity
public class User {
	/*人员编号*/
	private int id;
	/*人员姓名*/
	private String name;
	/*密码*/
	private String password;
	private boolean sex;
	private long phone;
	private long QQ;
	private String email;
	/*家庭住址*/
	private String addr;
	
	private Date regDate;
	
	private String IP;
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String ip) {
		IP = ip;
	}
	//@GeneratedValue(generator = "paymentableGenerator")    
	//@GenericGenerator(name = "paymentableGenerator", strategy = "assigned") 
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
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public long getQQ() {
		return QQ;
	}
	public void setQQ(long qq) {
		QQ = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
