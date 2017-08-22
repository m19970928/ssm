<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">

<script>
	function saveCart(){
		document.getElementById("cartForm").submit();
	}
</script>
</head>
<body>
	<%@ include file="menu.jsp"%>


	<div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
				<!-- 显示左侧分类 -->
				<div class="span6">
					<div class="hotProductCategory">
					<!-- 一级分类 -->
					<c:forEach items="${sessionScope.categoryList}" var="category">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/product/productList?cid=${category.cid}">${category.cname }</a>
							</dt>
							<!-- 二级分类 -->
							<c:forEach items="${category.categorySeconds}" var="categorySecond">
							<dd>
								<a href="${pageContext.request.contextPath}/product/selectProductByCsid?csid=${categorySecond.csid}">${categorySecond.csname}</a>
							</dd>
							</c:forEach>
						</dl>
					</c:forEach>
					</div>
				</div>			
			</div>
		</div>
		
<!-- 显示商品详情 -->
<div class="span18 last">
	<div class="productImage">  
		<div class="zoomPad">
			<img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath }/${requestScope.product.image}">
		</div> 
	</div>
	<div class="name">${requestScope.product.pname}</div>
	<div class="sn">
		<div>编号：${requestScope.product.pid}</div>
	</div>
	<div class="info">
		<dl>
			<dt>商城价:</dt>
			<dd>
				<strong>￥：${requestScope.product.shopPrice}元/份</strong> 
				参 考 价：<del>￥${requestScope.product.marketPrice}元/份</del>
			</dd>
		</dl>
		<dl>
			<dt>促销:</dt>
			<dd>
				<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
			</dd>
		</dl>
	</div>
	
	<!-- 将商品加入购物车 -->
	<form id="cartForm" action="${pageContext.request.contextPath }/cart/addCart" method="post">
		<input type="hidden" name="pid" value="${requestScope.product.pid}"/>
		<div class="action">
			<dl class="quantity">
				<dt>购买数量:</dt>
				<dd><input id="count" name="count" value="1" maxlength="4" onpaste="return false;" type="text"/></dd>
				<dd>件</dd>
			</dl>
			<div class="buy">
				<input id="addCart" class="addCart" value="加入购物车" type="button" onclick="saveCart()">
			</div>
		</div>
	</form>
	
	
	<div id="bar" class="bar">
		<ul>
			<li id="introductionTab"><a href="#introduction">商品介绍</a></li>
		</ul>
	</div>
	<div id="introduction" name="introduction" class="introduction">
		<div class="title">
			<strong>${requestScope.product.pdesc}</strong>
		</div>
	</div>

</div>
	</div>

	<%@ include file="tail.jsp" %>
</body>
</html>