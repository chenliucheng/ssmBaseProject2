package com.chen.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * @author Administrator
 *
 */
@Component  //spring框架通过component标价识别，将这个实体类自动加入容器，自动new 
public class User {
	private long id; // 用户id，设置为自增
	private String username; // 用户名
	private String password; // 登录密码
	private int sex; // 用户性别，0 :未知， 1:男， 2:女
	private Integer age; // 用户年龄
	private String address; // 用户住址 
	private String photo;//用户头像照片地址
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
