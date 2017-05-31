<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单配送管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ftc/order/orderShipment/">订单配送列表</a></li>
		<shiro:hasPermission name="ftc:order:orderShipment:edit"><li><a href="${ctx}/ftc/order/orderShipment/form">订单配送添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orderShipment" action="${ctx}/ftc/order/orderShipment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="order.orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="userPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>姓名</th>
				<th>手机号码</th>
				<th>详细地址</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ftc:order:orderShipment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderShipment">
			<tr>
				<td><a href="${ctx}/ftc/order/orderShipment/form?id=${orderShipment.id}">
					${orderShipment.order.orderNo}
				</a></td>
				<td>
					${orderShipment.userName}
				</td>
				<td>
					${orderShipment.userPhone}
				</td>
				<td>
					${orderShipment.userAdress}
				</td>
				<td>
					<fmt:formatDate value="${orderShipment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ftc:order:orderShipment:edit"><td>
    				<a href="${ctx}/ftc/order/orderShipment/form?id=${orderShipment.id}">修改</a>
					<a href="${ctx}/ftc/order/orderShipment/delete?id=${orderShipment.id}" onclick="return confirmx('确认要删除该订单配送吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>