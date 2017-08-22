<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css" />

</head>
<body>

	<%@ include file="menu.jsp" %>

	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>
					<li>以下是${sessionScope.user.name }的所有订单</li>
				</ul>
			</div>


			<table>
				<tbody>
				<!-- 遍历订单 -->
				<c:forEach items="${requestScope.orderList }" var="order">
					<tr>
						<th colspan="5">订单编号：${order.oid }
							订单状态：<c:if test="${order.state == 1 }">
										<a href="${pageContext.request.contextPath }/order/orderByOid?oid=${order.oid}">
											<font color="red">付款</font>
										</a>	
									</c:if>
									<c:if test="${order.state == 2 }">已付款</c:if>
									<c:if test="${order.state == 3 }">
										<a href="${pageContext.request.contextPath }/order/endOrder?oid=${order.oid}">
											<font color="red">确认收货</font>
										</a>
									</c:if>
									<c:if test="${order.state == 4 }">交易完成</c:if>
						</th>
					</tr>
				
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
	<!-- 遍历订单条目 -->				
	<c:forEach items="${order.orderItems }" var="orderItem">	
		<tr>
			<td width="60">
				<img src="${pageContext.request.contextPath}/${orderItem.product.image}" />
			</td>
			<td>
				${orderItem.product.pname}
			</td>
			<td>
				${orderItem.product.shopPrice}
			</td>
			<td class="quantity" width="60">
				${orderItem.count}
			</td>
			<td width="140">
				<span class="subtotal">￥${orderItem.subtotal}</span>
			</td>
			
		</tr>
	</c:forEach>
				</c:forEach>
				
				<!-- 显示页码  -->
				<tr>
					<th colspan="5">
					<div class="pagination">
						<!-- 后期优化显示页码  -->
					</div>
					</th>
				</tr>
				
				</tbody>
			</table>
		</div>
	</div>
	<%@ include file="tail.jsp" %>
</body>
</html>