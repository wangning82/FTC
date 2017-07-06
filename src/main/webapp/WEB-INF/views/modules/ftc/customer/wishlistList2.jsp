<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店铺收藏</title>
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
		<li class="active">><a href="${ctx}/ftc/customer/wishlist/list?type=2">店铺收藏</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="wishlist" action="${ctx}/ftc/customer/wishlist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>店铺：</label>
				<form:input path="designer.shopName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

				<th>店铺名称</th>

				<shiro:hasPermission name="ftc:customer:wishlist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wishlist">
			<tr>

				<td>
					${wishlist.designer.id}
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