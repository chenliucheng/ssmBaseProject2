package com.chen.programmer.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chen.programmer.util.CpachaUtil;

/**
 * 系统操作控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/system") //访问路径
public class SystemController {
	
	
//	第一种方法：使用String
//	@RequestMapping(value = "/index",method = RequestMethod.GET)
//	public String index() {
//		//通过springmvc的视图解析器,springmvc.xml文件中的30行
//		return "system/index"; // 等于： /WEB-INF/views/ + system/index + .jsp
//	} 
	
//	第二种方法：使用ModelAndView，更加常用
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	//登录页面
	public ModelAndView index(ModelAndView model) {
		model.setViewName("system/index");
		model.addObject("name", "chenliucheng ");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}
	


	/**
	 * 功能： 所用需要用验证码的页面都可以使用这个方法来生成验证码
	 * @param vcodeLen
	 * @param width
	 * @param height
	 * @param cpachaType：用于判断请求验证码的页面
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/get_Cpacha", method = RequestMethod.GET)
	public void generateCpacha(//设置需要的参数
			@RequestParam(name = "vl", required = false, defaultValue = "4")Integer vcodeLen,
			@RequestParam(name = "w", required = false, defaultValue = "100")Integer width,
			@RequestParam(name = "h", required = false, defaultValue = "30")Integer height,
			@RequestParam(name = "type", required = true, defaultValue = "loginCpacha")String cpachaType,
			HttpServletRequest request, 
			HttpServletResponse response) {
		//实例化一个cpacha工具类（路径：com.chen.project.util.CpachaUtil.）
		CpachaUtil CpachaUtil = new CpachaUtil(vcodeLen, width, height);
		//初始化一个画布
		String generatorVCode = CpachaUtil.generatorVCode();
		//将画布赋值到session中
		request.getSession().setAttribute(cpachaType, generatorVCode);
		//设置验证码的旋转方式为立体效果
		BufferedImage generatorRotateVCodeImage = CpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}