<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />


</head>
<body>

	<%@ include file="menu.jsp"%>

	<div class="container index">
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
				<div class="title">
					<strong>热门商品</strong>
				</div>
				<ul class="tab"><li class="current"></li></ul>

				<!-- 显示最热商品 -->	
				<ul class="tabContent" style="display: block;">
				<c:forEach items="${sessionScope.productHot }" var="product">
					<li>
						<a href="${pageContext.request.contextPath }/product/selectProductByPid?pid=${product.pid}" target="_blank">
							<img src="${pageContext.request.contextPath }/${product.image}"></a>
					</li>
				</c:forEach>	
				</ul>
			</div>	
			<div id="newProduct" class="newProduct clearfix">
				<div class="title">
					<strong>最新商品</strong> <a target="_blank"></a>
				</div>
				<ul class="tab"><li class="current"></li></ul>
				
				<!-- 显示最新商品 -->	
				<ul class="tabContent" style="display: block;">
				<c:forEach items="${sessionScope.productPdate }" var="product">
					<li>
						<a href="${pageContext.request.contextPath }/product/selectProductByPid?pid=${product.pid}" target="_blank">
							<img src="${pageContext.request.contextPath}/${product.image}" /> </a>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<%@ include file="tail.jsp" %>
	
</body>
</html>

