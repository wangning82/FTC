<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品收藏</title>
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
		<li class="active"><a href="${ctx}/ftc/customer/wishlist/list?type=1">商品收藏</a></li>
		<shiro:hasPermission name="ftc:customer:wishlist:edit"><li><a href="${ctx}/ftc/customer/wishlist/list?type=2">店铺收藏</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wishlist" action="${ctx}/ftc/customer/wishlist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品：</label>
				<form:input path="product.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>店铺：</label>
				<form:input path="designer.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:input path="type" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品ID</th>
				<th>用户ID</th>
				<th>店铺id</th>
				<th>类型</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ftc:customer:wishlist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wishlist">
			<tr>
				<td><a href="${ctx}/ftc/customer/wishlist/form?id=${wishlist.id}">
					${wishlist.product.id}
				</a></td>
				<td>
					${wishlist.customer.id}
				</td>
				<td>
					${wishlist.designer.id}
				</td>
				<td>
					${wishlist.type}
				</td>
				<td>
					<fmt:formatDate value="${wishlist.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ftc:customer:wishlist:edit"><td>
    				<a href="${ctx}/ftc/customer/wishlist/form?id=${wishlist.id}">修改</a>
					<a href="${ctx}/ftc/customer/wishlist/delete?id=${wishlist.id}" onclick="return confirmx('确认要删除该收藏吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>