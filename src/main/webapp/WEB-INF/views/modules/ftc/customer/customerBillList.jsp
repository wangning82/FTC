<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账单管理</title>
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
		<li class="active"><a href="${ctx}/ftc/customer/customerBill/">账单列表</a></li>
		<!--
		<shiro:hasPermission name="ftc:customer:customerBill:edit"><li><a href="${ctx}/ftc/customer/customerBill/form">账单添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="customerBill" action="${ctx}/ftc/customer/customerBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>客户名称：</label>
				<form:input path="customer.userName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>订单编号：</label>
				<form:input path="order.orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_customer_bill_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_customer_bill_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>客户名称</th>
				<th>订单编号</th>
				<th>金额</th>
				<th>类型</th>
				<th>状态</th>
				<shiro:hasPermission name="ftc:customer:customerBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="customerBill">
			<tr>
				<td><a href="${ctx}/ftc/customer/customerBill/form?id=${customerBill.id}">
					${customerBill.customer.userName}
				</a></td>
				<td>
					${customerBill.order.orderNo}
				</td>
				<td>
					${customerBill.money}
				</td>
				<td>
					${fns:getDictLabel(customerBill.type, 'ftc_customer_bill_type', '')}
				</td>
				<td>
					${fns:getDictLabel(customerBill.status, 'ftc_customer_bill_status', '')}
				</td>
				<shiro:hasPermission name="ftc:customer:customerBill:edit"><td>
    				<a href="${ctx}/ftc/customer/customerBill/form?id=${customerBill.id}">修改</a>
					<a href="${ctx}/ftc/customer/customerBill/delete?id=${customerBill.id}" onclick="return confirmx('确认要删除该账单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>