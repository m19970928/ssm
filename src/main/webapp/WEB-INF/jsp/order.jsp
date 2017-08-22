<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
					<li class="current"></li>
					<li>生成订单成功</li>
				</ul>
			</div>

			<table>
				<tbody>
					<tr>
						<th colspan="5">订单编号：${sessionScope.order.oid }</th>
					</tr>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>

<!-- 遍历订单信息 -->		
<c:forEach items="${sessionScope.order.orderItems }" var="orderItem">
	<tr>
		<td width="60">
			<input type="hidden" name="id" value="22" /> 
			<img src="${pageContext.request.contextPath}/${orderItem.product.image }" />
		</td>
			
		<td>
			<a target="_blank">${orderItem.product.pname }</a>
		</td>
		<td>
			${orderItem.product.shopPrice }
		</td>
		<td class="quantity" width="60">
			${orderItem.count }
		</td>
		<td width="140">
			<span class="subtotal">￥${orderItem.subtotal }</span>
		</td>
		
	</tr>
</c:forEach>

				</tbody>
			</table>
			<dl id="giftItems" class="hidden" style="display: none;">
			</dl>
			<div class="total">
				<em id="promotion"></em> 商品金额: <strong id="effectivePrice">￥${sessionScope.order.total }元</strong>
			</div>
		
<!-- 支付订单 -->			
<form id="orderForm" action="#" method="post">
	<input type="hidden" name="order.oid" value="" />
	<div class="span24">
		<p>
		收货地址：<input name="order.user.addr" type="text" value="${sessionScope.order.user.address }" style="width:350px" /> <br />
		收&nbsp;货&nbsp;人&nbsp;： <input name="order.user.username" type="text" value="${sessionScope.order.user.name }" style="width:150px" /> <br />
		联系方式：<input name="order.user.phone" type="text" value="${sessionScope.order.user.phone }" style="width:150px" />
		</p>
		<hr />
		<p>
			选择银行：<br /> <input type="radio" name="pd_FrpId"
				value="ICBC-NET-B2C" checked="checked" />工商银行 <img
				src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pd_FrpId" value="BOC-NET-B2C" />中国银行 <img
				src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />农业银行 <img
				src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle" /> <br /> <input
				type="radio" name="pd_FrpId" value="BOCO-NET-B2C" />交通银行 <img
				src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />平安银行
			<img src="${pageContext.request.contextPath}/bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pd_FrpId" value="CCB-NET-B2C" />建设银行 <img
				src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle" /> <br /> <input
				type="radio" name="pd_FrpId" value="CEB-NET-B2C" />光大银行 <img
				src="${pageContext.request.contextPath}/bank_img/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" />招商银行
			<img src="${pageContext.request.contextPath}/bank_img/cmb.bmp" align="middle" />
		</p>
		<hr />
		<p style="text-align:right">
			<a href="${pageContext.request.contextPath}/order/confirmOrder?oid=${sessionScope.order.oid }">
				<img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="204" height="51" border="0" /> 
			</a>
		</p>
	</div>
</form>
		</div>

	</div>
	
	<%@ include file="tail.jsp" %>

</body>
</html>