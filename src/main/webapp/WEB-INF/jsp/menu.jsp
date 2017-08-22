<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/index"> 
					<img src="${pageContext.request.contextPath}/image/renleipic_01/logo.gif" alt="传智播客" />
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障"
					title="正品保障" />
			</div>
		</div>
		<div class="span10 last">
			<div class="topNav clearfix">
				<ul>

			<c:choose>
				
				<c:when test="${empty sessionScope.user  }">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/user/loginPage">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/user/registPage">注册</a>|
					</li>
				</c:when>
				
				<c:otherwise>
					<li>${sessionScope.user.name } |</li>
					<li><a href="${pageContext.request.contextPath }/user/quit">退出</a>|</li>
					<li><a href="${ pageContext.request.contextPath }/order/myOrder">我的订单</a>|</li>
				</c:otherwise>
			  </c:choose>
				
					
					<li><a>会员中心</a> |</li>
					<li><a>购物指南</a> |</li>
					<li><a>关于我们</a></li>
				</ul>
			</div>
			<div class="cart">
				<a href="${pageContext.request.contextPath}/cart/myCart">购物车</a>
			</div>
			<div class="phone">
				客服热线: <strong>96008/53277764</strong>
			</div>
		</div>
		<div class="span24">
			<ul class="mainNav">
				<li><a href="${pageContext.request.contextPath}/index">首页</a> |</li>
				<li><a href="${pageContext.request.contextPath}/product/productList?cid=1&page=1">定制套餐</a> |</li>
				<li><a>安全频道</a> |</li>
				<li><a>商城卡</a> |</li>
				<li><a>蔬菜基地</a> |</li>
				<li><a>节气养生</a> |</li>
				<li><a>便民服务</a> |</li>

			</ul>
		</div>


	</div>