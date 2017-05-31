<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>购物车管理</title>
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
		<li class="active"><a href="${ctx}/ftc/order/shoppingCart/">购物车列表</a></li>
		<shiro:hasPermission name="ftc:order:shoppingCart:edit"><li><a href="${ctx}/ftc/order/shoppingCart/form">购物车添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shoppingCart" action="${ctx}/ftc/order/shoppingCart/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品规格：</label>
				<form:input path="productSpecNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>顾客：</label>
				<form:input path="customer.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品规格编号</th>
				<th>顾客</th>
				<th>购买数量</th>
				<th>加入购物车时间</th>
				<th>购物车状态</th>
				<shiro:hasPermission name="ftc:order:shoppingCart:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shoppingCart">
			<tr>
				<td><a href="${ctx}/ftc/order/shoppingCart/form?id=${shoppingCart.id}">
					${shoppingCart.productSpecNumber}
				</a></td>
				<td>
					${shoppingCart.customer.userName}
				</td>
				<td>
					${shoppingCart.buyNumber}
				</td>
				<td>
					<fmt:formatDate value="${shoppingCart.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(shoppingCart.checkStatus, 'ftc_cart_check_status', '')}
				</td>
				<shiro:hasPermission name="ftc:order:shoppingCart:edit"><td>
    				<a href="${ctx}/ftc/order/shoppingCart/form?id=${shoppingCart.id}">修改</a>
					<a href="${ctx}/ftc/order/shoppingCart/delete?id=${shoppingCart.id}" onclick="return confirmx('确认要删除该购物车吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>