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
		<li class="active"><a href="${ctx}/ftc/customer/bill/">账单列表</a></li>
		<shiro:hasPermission name="ftc:customer:bill:edit"><li><a href="${ctx}/ftc/customer/bill/form">账单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bill" action="${ctx}/ftc/customer/bill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型 1-入账 2-提现：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_customer_bill_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态 1-申请 2-到账 3-未通过 4-退款：</label>
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
				<th>create_date</th>
				<th>create_by</th>
				<th>update_date</th>
				<th>update_by</th>
				<th>金额</th>
				<th>类型 1-入账 2-提现</th>
				<th>备注</th>
				<th>订单id</th>
				<th>客户id</th>
				<th>状态 1-申请 2-到账 3-未通过 4-退款</th>
				<shiro:hasPermission name="ftc:customer:bill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bill">
			<tr>
				<td><a href="${ctx}/ftc/customer/bill/form?id=${bill.id}">
					<fmt:formatDate value="${bill.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${bill.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bill.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bill.updateBy.id}
				</td>
				<td>
					${bill.money}
				</td>
				<td>
					${fns:getDictLabel(bill.type, 'ftc_customer_bill_type', '')}
				</td>
				<td>
					${bill.remark}
				</td>
				<td>
					${bill.orderId}
				</td>
				<td>
					${bill.customerId}
				</td>
				<td>
					${fns:getDictLabel(bill.status, 'ftc_customer_bill_status', '')}
				</td>
				<shiro:hasPermission name="ftc:customer:bill:edit"><td>
    				<a href="${ctx}/ftc/customer/bill/form?id=${bill.id}">修改</a>
					<a href="${ctx}/ftc/customer/bill/delete?id=${bill.id}" onclick="return confirmx('确认要删除该账单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>