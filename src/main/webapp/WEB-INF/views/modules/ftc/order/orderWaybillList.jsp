<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>运单管理</title>
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
		<li class="active"><a href="${ctx}/ftc/order/orderWaybill/">运单列表</a></li>
		<shiro:hasPermission name="ftc:order:orderWaybill:edit"><li><a href="${ctx}/ftc/order/orderWaybill/form">运单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orderWaybill" action="${ctx}/ftc/order/orderWaybill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="order.orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>收货地址：</label>
				<form:input path="shipment.userAdress" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>运单号：</label>
				<form:input path="waybillNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>快递公司：</label>
				<form:select path="expressCompany" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_express_company')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>收货地址</th>
				<th>运单号</th>
				<th>快递公司</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ftc:order:orderWaybill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderWaybill">
			<tr>
				<td><a href="${ctx}/ftc/order/orderWaybill/form?id=${orderWaybill.id}">
					${orderWaybill.order.orderNo}
				</a></td>
				<td>
					${orderWaybill.shipment.userAdress}
				</td>
				<td>
					${orderWaybill.waybillNumber}
				</td>
				<td>
					${fns:getDictLabel(orderWaybill.expressCompany, 'ftc_express_company', '')}
				</td>
				<td>
					<fmt:formatDate value="${orderWaybill.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ftc:order:orderWaybill:edit"><td>
    				<a href="${ctx}/ftc/order/orderWaybill/form?id=${orderWaybill.id}">修改</a>
					<a href="${ctx}/ftc/order/orderWaybill/delete?id=${orderWaybill.id}" onclick="return confirmx('确认要删除该运单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>