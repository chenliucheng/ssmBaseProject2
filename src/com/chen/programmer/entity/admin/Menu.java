package com.chen.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 菜单实体类
 * @author Administrator
 *
 */
@Component  //让spring扫描到，自动加入容器
public class Menu {
	private Integer id;
	private Integer parentId; //父类id
	private String name; //菜单名称
	private String url;  //点击后的url
	private String icon; // 菜单icon图标
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
