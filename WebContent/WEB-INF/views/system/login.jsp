<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<!-- saved from url=(0051)http://demo1.mycodes.net/denglu/HTML5_yonghudenglu/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <title>SSM-framework-admin-login-manager-system</title>
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  <meta name="author" content="Vincent Garreau">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <link rel="stylesheet" media="screen" href="../resources/login/css/style.css">
  <link rel="stylesheet" type="text/css" href="../resources/login/css/reset.css">
<body>

<div id="particles-js">
		<div class="login" style="display: block;">
			<div class="login-top">
				登录
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="../resources/login/images/name.png"></div>
				<div class="login-center-input">
					<input type="text" name="" value="" placeholder="请输入您的用户名" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入您的用户名&#39;">
					<div class="login-center-input-text">用户名</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="../resources/login/images/password.png"></div>
				<div class="login-center-input">
					<input type="password" name="" value="" placeholder="请输入您的密码" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入您的密码&#39;">
					<div class="login-center-input-text">密码</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="../resources/login/images/password.png"></div>
				<div class="login-center-input">
					<input style="width:50%" type="text" name="" value="" placeholder="请输入验证码" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入验证码&#39;">
					<div class="login-center-input-text">验证码</div>
					<img src="get_Cpacha?vl=4&w=110&h=30" width="110px" height="30px"/>
				</div>
			</div>
			<div class="login-button">
				登录
			</div>
		</div>
		<div class="sk-rotating-plane"></div>
<canvas class="particles-js-canvas-el" width="1147" height="952" style="width: 100%; height: 100%;"></canvas></div>

<!-- scripts -->
<script src="../resources/login/js/particles.min.js"></script>
<script src="../resources/login/js/app.js"></script>
<script type="text/javascript">
	function hasClass(elem, cls) {
	  cls = cls || '';
	  if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
	  return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
	}
	 
	function addClass(ele, cls) {
	  if (!hasClass(ele, cls)) {
	    ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
	  }
	}
	 
	function removeClass(ele, cls) {
	  if (hasClass(ele, cls)) {
	    var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
	    while (newClass.indexOf(' ' + cls + ' ') >= 0) {
	      newClass = newClass.replace(' ' + cls + ' ', ' ');
	    }
	    ele.className = newClass.replace(/^\s+|\s+$/g, '');
	  }
	}
		document.querySelector(".login-button").onclick = function(){
				addClass(document.querySelector(".login"), "active")
				setTimeout(function(){
					addClass(document.querySelector(".sk-rotating-plane"), "active")
					document.querySelector(".login").style.display = "none"
				},800)
				setTimeout(function(){
					removeClass(document.querySelector(".login"), "active")
					removeClass(document.querySelector(".sk-rotating-plane"), "active")
					document.querySelector(".login").style.display = "block"
					alert("登录成功")
					
				},5000)
		}
</script>
</body></html>