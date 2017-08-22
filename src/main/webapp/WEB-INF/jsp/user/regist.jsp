<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员注册</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css" />

<script>
	// 判断用户名是否被占用
	function ajaxUser(){
		// 获取用户名
		var username = document.getElementById("username").value;
		// 创建xhr对象
		var xhr = createxhr();
		// 发送请求
		xhr.open("GET","${pageContext.request.contextPath}/user/ajaxUsername?username="+username);
		// 发送数据
		xhr.send(null);
		// 监听响应
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var font = document.getElementById("ajaxUser");
				font.innerHTML = xhr.responseText;
			}
		}
	}
	
	// 判断确认密码是否与上一栏的密码是否相同
	function repassword(){
		// 获取确认密码的数据
		var repassword = document.getElementById("rePassword").value;
		// 获取上一栏的密码
		var password = document.getElementById("password").value;
		// 获取信息提示框
		var font = document.getElementById("fontPassword");
		// 判断是否相同
		if(password != repassword){
			// 如果不相同
			font.innerHTML = "请确认密码是否相同";
		} else{
			// 如果相同
			font.innerHTML= "密码相同，可以使用";
		}
	}
	
	// 创建xhr对象 
	function createxhr(){
		if(window.ActiveXObject){
			// 使用IE浏览器
			return new ActiveXObject("Microsoft.XMLHTTP");
		} else{
			// 除IE外
			return new XMLHttpRequest();
		}
	}
	
	// 点击事件更换验证码
	function reloadCode(){
	     var time=new Date().getTime();
	     document.getElementById("captchaImage").src="${pageContext.request.contextPath }/user/verifyCode?d="+time;
	}
</script>

</head>
<body>
	
	<!-- 引入菜单页面 -->
	<%@ include file="../menu.jsp" %>
	
	<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>会员注册</strong>USER REGISTER
					</div>
<!-- 注册表单 -->		
<font color="red">${requestScope.erro }</font>			
<form id="registerForm" action="${pageContext.request.contextPath}/user/regist" method="post" novalidate="novalidate">
	<table>
		<tbody>
			<tr>
				<th><span class="requiredField">*</span>用户名:</th>
				<td>
					<input type="text" id="username" name="username" class="text" maxlength="20" onblur="ajaxUser()">
					<font id="ajaxUser" color="red"></font>
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>密&nbsp;&nbsp;码:</th>
				<td>
					<input type="password" id="password" name="password" class="text" maxlength="20">
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>确认密码:</th>
				<td>
					<input type="password" id="rePassword" name="rePassword" class="text" maxlength="20" onblur="repassword()">
					<font id="fontPassword" color="red"></font>
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>E-mail:</th>
				<td>
					<input type="text" id="email" name="email" class="text" maxlength="200">
				</td>
			</tr>
			<tr>
				<th>姓名:</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200">
				</td>
			</tr>

			<tr>
				<th>地址:</th>
				<td>
					<input type="text" name="address" class="text" maxlength="200">
				</td>
			</tr>

			<tr>
				<th>电话:</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="200">
				</td>
			</tr>
			
			<tr>
				<th>验证码:</th>
				<td><span class="fieldSet"> 
					<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" >
					<img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath }/user/verifyCode" title="点击更换验证码" onclick="reloadCode()"> 
				</span></td>
			</tr>
			
			<tr>
				<th>&nbsp;</th>
				<td><input type="submit" class="submit" value="同意以下协议并注册">
				</td>
			</tr>
		
		<!-- 引入注册协议 -->
		<%@ include file="message.jsp" %>
		
		</tbody>
	</table>
	<div class="login">
		<div class="ad">
			<dl>
				<dt>注册即享受</dt>
				<dd>正品保障、正规发票</dd>
				<dd>货到付款、会员服务</dd>
				<dd>自由退换、售后上门</dd>
			</dl>
		</div>
		<dl>
			<dt>已经拥有账号了？</dt>
			<dd>
				立即登录即可体验在线购物！ <a href="${pageContext.request.contextPath}/会员登录.htm">立即登录</a>
			</dd>
		</dl>
	</div>
</form>
				</div>
			</div>
		</div>
	</div>
	
	
		<!-- 引入尾部页面 -->
	<%@ include file="../tail.jsp" %>
	
</body>
</html>