package com.chen.programmer.service.admin;

import java.util.HashMap;
import java.util.LinkedList;

import java.util.List;
import org.springframework.stereotype.Service;

import com.chen.programmer.entity.admin.Menu;

/**
 * 菜单管理service
 * @author Administrator
 *
 */
@Service
public interface MenuService {
	public int add(Menu menu);
	public LinkedList<Menu> findList(HashMap<String, Object> queryHashMap);
	public List<Menu> findTopList();
	public int getTotal(HashMap<String, Object> queryMap);
}
