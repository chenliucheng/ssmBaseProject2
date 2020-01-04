package com.chen.programmer.dao.admin;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.LinkedList;

=======
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037
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
<<<<<<< HEAD
	public LinkedList<Menu> findList(HashMap<String, Object> queryHashMap);
=======
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037
}
