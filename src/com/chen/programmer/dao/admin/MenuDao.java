package com.chen.programmer.dao.admin;

import org.springframework.stereotype.Repository;

import com.chen.programmer.entity.admin.Menu;

/**
 * 菜单管理dao
 * @author Administrator
 *
 */
@Repository   //标识为dao类
public interface MenuDao {
	public int add(Menu menu);
}
