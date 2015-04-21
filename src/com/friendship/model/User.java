package com.friendship.model;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table(value = "user")
/**
 * 用户实体类
 * @author Administrator
 */
public class User {

	@Column
	@Id
	private int id;				//用户id
	@Column
	private String username;	//用户昵称
	@Column
	private String password;	//密码
	@Column
	private String phone;		//联系方式
	@Column
	private String email;		//邮箱
	@Many(target=Activity.class,field="userid")
	private List<Activity> actList;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
