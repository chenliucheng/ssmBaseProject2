package com.chen.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chen.programmer.entity.admin.User;
import com.chen.programmer.util.CpachaUtil;
import com.github.pagehelper.util.StringUtil;
import com.mysql.jdbc.StringUtils;
import com.sun.javafx.collections.MappingChange.Map;

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
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)//GET方法进入登录页面
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}
	
	/**
	 * 登录表单POST方法提交控制器
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody //标注返回的是页面内容，不进行修改
	public HashMap<String, String>loginAct(User user, String cpacha, HttpServletRequest request){
		HashMap<String, String> ret =  new HashMap<String, String>();
		// 判断user是否为空
		if(user == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名信息！");
			
			return ret;
		}
		//判断验证码是否为空
		if(StringUtil.isEmpty(cpacha)) {
			ret.put("type", "error");
			ret.put("msg", "请填写验证码！");
			
			return ret;
		}
		//判断用户名是否为空
		if(StringUtil.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名!");
			
			return ret;
		}
		//判断密码是否为空
		if(StringUtil.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			
			return ret;
		}
		//获取验证码的session
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null) {// 等于null，说明会话超时
			ret.putIfAbsent("type", "error");
			ret.put("msg", "会话超时，请刷新页面");
			return ret;
		}else {//不等于空，说明会话未超时，此时需要判断输入的验证码与图形验证码是否一致
			if(! cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
				// 输入的验证码与图形验证码不匹配，提示验证码错误
				ret.put("type", "error");
				ret.put("msg", "验证码错误");
				return ret;
			}
			
		}
		//没有空值，提示登录成功
		ret.put("type", "success");
		ret.put("msg", "登录成功");
		return  ret;
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
