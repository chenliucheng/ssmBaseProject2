package com.chen.programmer.service.admin.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.programmer.dao.admin.MenuDao;
import com.chen.programmer.entity.admin.Menu;
import com.chen.programmer.service.admin.MenuService;

/**
 * 菜单管理实现类
 * @author	chenliucheng
 *
 */
@Service
public class MenuServiceimpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	@Override
	public int add(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.add(menu);
	}
	@Override
	public LinkedList<Menu> findList(HashMap<String, Object> queryHashMap) {
		// TODO Auto-generated method stub
		return menuDao.findList(queryHashMap);
	}
	@Override
	public List<Menu> findTopList() {
		// TODO Auto-generated method stub
		return menuDao.findTopList();
	}
	@Override
	public int getTotal(HashMap<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return menuDao.getTotal(queryMap);
	}

}
