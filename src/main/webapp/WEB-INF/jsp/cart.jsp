<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">


</head>
<body>
		
	<%@ include file="menu.jsp" %>
	
	<div class="container cart">
	<c:if test="${sessionScope.cart.cartItems.size() != 0 }">		
		<div class="span24">
			<div class="step step1"></div>
			<table>
				<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
<!-- 遍历购物车信息 -->			
<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">	
	<tr>
		<td width="60"><input type="hidden" name="id" value="22">
			<img src="${pageContext.request.contextPath }/${cartItem.product.image }">
		</td>
		<td><a target="_blank">${cartItem.product.pname }</a></td>
		<td>￥${cartItem.product.shopPrice }</td>
		<td class="quantity" width="60">${cartItem.count }</td>
		<td width="140"><span class="subtotal">￥${cartItem.subtotal }</span></td>
		<td><a href="${pageContext.request.contextPath }/cart/deleteCart?pid=${cartItem.product.pid}" class="delete">删除</a></td>
	</tr>
</c:forEach>
					
				</tbody>
			</table>
			<dl id="giftItems" class="hidden" style="display: none;">
			</dl>
			<div class="total">
				 商品金额: <strong id="effectivePrice">￥${sessionScope.cart.total }元</strong>
			</div>
			<div class="bottom">
				<a href="${pageContext.request.contextPath }/cart/clearCart" id="clear" class="clear">清空购物车</a> 
				<a href="${pageContext.request.contextPath }/order/addOrder" id="submit" class="submit">提交订单</a>
			</div>
		</div>
	</c:if>	
	<c:if test="${sessionScope.cart.cartItems.size() == 0 }">
		<div class="span24">
			<div class="step step1">
				<span><h2>购物车为空，快去买东西</h2></span>
			</div>
		</div>	
	</c:if>
	
	</div>
	
	<%@include file="tail.jsp" %>

</body>
</html>