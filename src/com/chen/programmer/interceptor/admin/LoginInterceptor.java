package com.chen.programmer.interceptor.admin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

/**
 * 后台登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 *方法内写入 登录拦截之后做什么...
	 *
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 方法内写入请求时做什么...
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
	

	/**方法内写入 登录拦截请求之前做什么...
	 *
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//方法: return false表示请求拒绝，拒绝进入；retrun true表示请求同意，允许进入
		
		//获取当前请求链接
		String requestURI = request.getRequestURI();
		//获取session
		Object admin = request.getSession().getAttribute("admin");
		if(admin == null) {//表示session失效或未登录，需要将页面重定向到login页面
			
			//控制台打印输出当前请求链接
			System.out.println("链接"+requestURI+"进入拦截器");
			//获取请求头中的X-Requested-With参数，用于判断是否为ajax请求
			String header = request.getHeader("X-Requested-With");
			if("XMLHttpRequest".equals(header)) {//header的值与XMLHttpRequest相等则说明是ajax请求
				
				//进入if语句，说明是ajax请求,创建一个ret返回对ajax请求的处理
				HashMap<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登录会话超时或还未登录，请重新登录！");
				//将ret写入响应头，并返回false
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//没有进入if语句说明是普通链接跳转，直接重定向到登录页面
			
			response.sendRedirect(request.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		//没有进入if语句，则说明session未失效或登陆成功，返回true，允许进入
		return true;
	}

}
