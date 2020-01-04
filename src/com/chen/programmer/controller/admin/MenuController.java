package com.chen.programmer.controller.admin;

import java.util.HashMap;
<<<<<<< HEAD
import java.util.LinkedList;
import java.util.concurrent.Executor;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;
=======

>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
=======
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chen.programmer.entity.admin.Menu;
<<<<<<< HEAD
import com.chen.programmer.page.admin.Page;
import com.chen.programmer.service.admin.MenuService;
import com.github.pagehelper.util.StringUtil;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
=======
import com.chen.programmer.service.admin.MenuService;
import com.github.pagehelper.util.StringUtil;
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037

/**
 * 菜单管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/menu")  // 设置路径为 /menu
@Controller   //告诉spring，这是一个controller类，扫描
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 菜单管理列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("menu/list");
		//System.out.println("list page");
		return model;
	}
	
	/**
<<<<<<< HEAD
	 * 获取菜单列表
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> MenuList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "")String name
			){
		//System.out.println(11111111); // 打印log
		HashMap<String, Object> ret = new HashMap<String, Object>();
		HashMap<String, Object> queryHashMap = new HashMap<String, Object>();
		queryHashMap.put("offset", page.getOffset());
		queryHashMap.put("pageSize", page.getRows());
		queryHashMap.put("name", name);
		LinkedList<Menu> findList = menuService.findList(queryHashMap);
		ret.put("rows", findList);
		ret.put("total", 10);
		return ret;
	}
	
	/**
=======
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037
	 * 菜单添加
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody  //返回json字符串
	public HashMap<String,String> add(Menu menu){
		System.out.println("进入add");
		HashMap<String, String> ret = new HashMap<String, String>();
		if(menu == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的菜单信息");
			return ret;
		}
		if(StringUtil.isEmpty(menu.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写菜单名称");
			return ret;
		}
		if(StringUtil.isEmpty(menu.getIcon())){
			ret.put("type", "error");
			ret.put("msg", "请填写菜单图标");
			return ret;
		}
		if(menuService.add(menu)<0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败请联系管理员");
			return ret;
		}
		if(menu.getParentId() == null ) {
			menu.setParentId(1);
		}
		//打印数据库操作条数
		int i =  menuService.add(menu);
<<<<<<< HEAD
		if( i == 1) {
			//当执行结果为1条时，需要手动commit
		}
=======
>>>>>>> 7655b1d1194d89e66e1f1407aa36791c40a95037
		System.out.println("----"+i);
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}
	
	
}
