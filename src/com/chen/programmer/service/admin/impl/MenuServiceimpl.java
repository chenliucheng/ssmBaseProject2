package com.chen.programmer.service.admin.impl;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.LinkedList;

=======
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037
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
<<<<<<< HEAD
	@Override
	public LinkedList<Menu> findList(HashMap<String, Object> queryHashMap) {
		// TODO Auto-generated method stub
		return menuDao.findList(queryHashMap);
	}
=======
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037

}
