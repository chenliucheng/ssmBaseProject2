package com.chen.programmer.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chen.programmer.entity.admin.Menu;
import com.chen.programmer.page.admin.Page;
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
	 * 获取图标
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get_icons", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getIconList(HttpServletRequest request){
		HashMap<String, Object> ret = new HashMap<String, Object>();
		//获取项目的绝对目录
		String realPath = request.getServletContext().getRealPath("/");
		File file = new File(realPath + "\\resources\\admin\\easyui\\css\\icons");
		List<String>icons = new ArrayList<String>();
		if(!(file.exists())) { //如果指定目录不存在，返回错误信息
			ret.put("type","error");
			ret.put("msg", "图标文件目录不存在");
			return ret;
		}
		// 获取目录下的文件内容
		File[] listFiles = file.listFiles();
		//利用for循环遍历出文件内容
		for(File f:listFiles) {
			//System.out.println(f.getName()); 测试打印图标文件
			if(f != null && f.getName().contains(".png")) {
				icons.add("icon-" + f.getName().substring(0,f.getName().indexOf(".")).replace("_", "-"));
			}
		}
		ret.put("type", "success");
		ret.put("content", icons);
		return ret;
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
			System.out.println("----");
			System.out.println(menu.getIcon());
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
		//int i =  menuService.add(menu);
		
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}
	
	
}
