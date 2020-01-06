package com.chen.programmer.dao.admin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
	public LinkedList<Menu> findList(HashMap<String, Object> queryHashMap);
	
	public List<Menu> findTopList();
	public int getTotal(HashMap<String, Object> queryMap);
}
