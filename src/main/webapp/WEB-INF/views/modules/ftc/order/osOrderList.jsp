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
		<li class="active"><a href="${ctx}/ftc/order/osOrder/">订单列表</a></li>
		<shiro:hasPermission name="ftc:order:osOrder:edit"><li><a href="${ctx}/ftc/order/osOrder/form">订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="osOrder" action="${ctx}/ftc/order/osOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号,系统生成：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户ID：</label>
				<sys:treeselect id="user" name="user" value="${osOrder.user}" labelName="" labelValue="${osOrder.}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>支付方式 0=线下支付，1=在线支付：</label>
				<form:input path="payType" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号,系统生成</th>
				<th>用户ID</th>
				<th>支付方式 0=线下支付，1=在线支付</th>
				<th>配送时间 1=不限送货时间，2=工作日送货，3=双休日、假日送货</th>
				<th>配送方式 0=快递配送（免运费），1=快递配送（运费）</th>
				<th>快递费</th>
				<th>支付方式 1=不开发票，2=电子发票，3=普通发票</th>
				<th>发票抬头</th>
				<th>订单状态</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>订单金额</th>
				<th>订单积分</th>
				<th>支付金额 = 订单金额 + 快递费</th>
				<th>商品总数量</th>
				<shiro:hasPermission name="ftc:order:osOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="osOrder">
			<tr>
				<td><a href="${ctx}/ftc/order/osOrder/form?id=${osOrder.id}">
					${osOrder.orderNumber}
				</a></td>
				<td>
					${osOrder.}
				</td>
				<td>
					${osOrder.payType}
				</td>
				<td>
					${osOrder.shipmentTime}
				</td>
				<td>
					${osOrder.shipmentType}
				</td>
				<td>
					${osOrder.shipmentAmount}
				</td>
				<td>
					${osOrder.invoiceType}
				</td>
				<td>
					${osOrder.invoiceTitle}
				</td>
				<td>
					${osOrder.orderStatus}
				</td>
				<td>
					<fmt:formatDate value="${osOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${osOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${osOrder.orderAmount}
				</td>
				<td>
					${osOrder.orderScore}
				</td>
				<td>
					${osOrder.payAmount}
				</td>
				<td>
					${osOrder.buyNumber}
				</td>
				<shiro:hasPermission name="ftc:order:osOrder:edit"><td>
    				<a href="${ctx}/ftc/order/osOrder/form?id=${osOrder.id}">修改</a>
					<a href="${ctx}/ftc/order/osOrder/delete?id=${osOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>