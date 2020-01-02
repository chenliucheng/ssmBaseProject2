package com.chen.programmer.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 菜单管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/menu")  // 设置路径为 /menu
@Controller   //告诉spring，这是一个controller类，扫描
public class MenuController {
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("menu/list");
		
		return model;
	}
}
