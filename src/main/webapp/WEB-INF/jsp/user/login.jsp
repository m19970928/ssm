<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0080)http://localhost:8080/mango/login.jhtml?redirectUrl=%2Fmango%2Fcart%2Flist.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员登录</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
  function reloadCode(){
       var time=new Date().getTime();
       document.getElementById("captchaImage").src="${pageContext.request.contextPath }/user/verifyCode?d="+time;
  }
</script>

</head>
<body>

	<!-- 引入菜单页面 -->
	<%@ include file="../menu.jsp"%>

	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					
<!-- 登陆表单 -->
<font color="red">${requestScope.erro }</font>
<form id="loginForm" action="${pageContext.request.contextPath}/user/login" method="post" novalidate="novalidate">
	<table>
		<tbody>
			<tr>
				<th>用户名/E-mail:</th>
				<td>
					<input type="text" id="username" name="username" class="text" maxlength="20" value="${cookie.username.value }">
				</td>
			</tr>
			<tr>
				<th>密&nbsp;&nbsp;码:</th>
				<td>
					<input type="password" id="password" name="password" class="text" maxlength="20" value="${cookie.password.value }">
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
				<td><label> 
					<input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true" <c:if test="${!empty cookie.username.value }">checked="checked"</c:if> /> 
					记住用户名 
				</label> 
				<label> &nbsp;&nbsp;<a>找回密码</a> </label>
				</td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td><input type="submit" class="submit" value="登 录">
				</td>
			</tr>
			<tr class="register">
				<th>&nbsp;</th>
				<td>
					<dl>
						<dt>还没有注册账号？</dt>
						<dd>
							立即注册即可体验在线购物！
							 <a href="${pageContext.request.contextPath}/user/regist">立即注册</a>
						</dd>
					</dl>
				</td>
			</tr>
		</tbody>
	</table>
</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 引入尾部页面 -->
	<%@ include file="../tail.jsp"%>
</body>
</html>