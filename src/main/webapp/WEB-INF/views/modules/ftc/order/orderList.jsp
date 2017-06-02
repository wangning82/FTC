<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/ftc/order/order/">订单列表</a></li>
		<shiro:hasPermission name="ftc:order:order:edit"><li><a href="${ctx}/ftc/order/order/form">订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/ftc/order/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>支付方式：</label>
				<form:select path="payType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_order_pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单状态：</label>
				<form:select path="orderStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_order_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>顾客</th>
				<th>支付方式</th>
				<th>订单状态</th>
				<th>下单时间</th>
				<th>订单金额</th>
				<shiro:hasPermission name="ftc:order:order:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td><a href="${ctx}/ftc/order/order/form?id=${order.id}">
					${order.orderNo}
				</a></td>
				<td>
					${order.customer.userName}
				</td>
				<td>
					${fns:getDictLabel(order.payType, 'ftc_order_pay_type', '')}
				</td>
				<td>
					${fns:getDictLabel(order.orderStatus, 'ftc_order_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatNumber value="${order.orderAmount}" type="currency"/>
				</td>
				<shiro:hasPermission name="ftc:order:order:edit"><td>
    				<a href="${ctx}/ftc/order/order/form?id=${order.id}">修改</a>
					<a href="${ctx}/ftc/order/order/delete?id=${order.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>