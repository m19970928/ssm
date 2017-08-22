<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>传智网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<!-- 引入菜单页面 -->	
	<%@ include file="menu.jsp" %>
		
		
	<div class="container productList">
	
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
		
		<!-- 显示商品 -->
		<div class="span18 last">
			<div id="result" class="result table clearfix">
				<ul>
				
				<c:forEach items="${requestScope.pageBean.list }" var="product">
					
					<li><a href="${pageContext.request.contextPath}/product/selectProductByPid?pid=${product.pid}"> 
						<img src="${pageContext.request.contextPath}/${product.image}" width="170" height="170" style="display: inline-block;"> 
						<span style='color:green'> ${product.pname } </span>
						<span class="price"> 商城价： ￥${product.shopPrice }份 </span> 
					</a></li>
				
				</c:forEach>
					
				</ul>
				
			</div>

			<!-- 显示页码 -->
			<div class="pagination">

				<span>第${pageBean.page }/${pageBean.totalPage }页</span>
				
				<c:if test="${pageBean.page != 1}">
					<a class="firstPage" href="${pageContext.request.contextPath }/product/productList?cid=${requestScope.cid }&page=1" >&nbsp;</a>
					<a class="previousPage" href="${pageContext.request.contextPath }/product/productList?cid=${requestScope.cid }&page=${pageBean.page - 1}" >&nbsp;</a>
				</c:if>
				
				<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<a href="${pageContext.request.contextPath }/product/productList?cid=${requestScope.cid }&page=${i}">${i }</a>
				</c:forEach>
				
				<c:if test="${pageBean.page != pageBean.totalPage }">
					<a class="nextPage" href="${pageContext.request.contextPath }/product/productList?cid=${requestScope.cid }&page=${pageBean.page + 1}">&nbsp;</a>
					<a class="lastPage" href="${pageContext.request.contextPath }/product/productList?cid=${requestScope.cid }&page=${pageBean.totalPage}">&nbsp;</a>
				</c:if>	

			</div>
				
		</div>
	</div>
	
	<!-- 引入底部页面 -->	
	<%@ include file="tail.jsp" %>
</body>
</html>
