package com.chen.programmer.controller.admin;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chen.programmer.entity.admin.Menu;
import com.chen.programmer.service.admin.MenuService;
import com.github.pagehelper.util.StringUtil;

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
		System.out.println("----"+i);
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}
	
	
}
