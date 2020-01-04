package com.chen.programmer.service.admin;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

import com.chen.programmer.entity.admin.Menu;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
=======
import org.springframework.stereotype.Service;

import com.chen.programmer.entity.admin.Menu;
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037

/**
 * 菜单管理service
 * @author Administrator
 *
 */
@Service
public interface MenuService {
	public int add(Menu menu);
<<<<<<< HEAD
	public LinkedList<Menu> findList(HashMap<String, Object> queryHashMap);
=======
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037
}
