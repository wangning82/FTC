<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片管理</title>
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
		<li class="active"><a href="${ctx}/ftc/product/image/">图片列表</a></li>
		<shiro:hasPermission name="ftc:product:image:edit"><li><a href="${ctx}/ftc/product/image/form">图片添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="image" action="${ctx}/ftc/product/image/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>缩略图</th>
				<th>产品</th>
				<th>位置</th>
				<th>旋转</th>
				<th>缩放</th>
				<shiro:hasPermission name="ftc:product:image:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="image">
			<tr>
				<td>
					<img src="${image.url}" height="40px"/>
				</td>
				<td>
					${image.product.name}
				</td>
				<td>
					${image.position.name}
				</td>
				<td>
					${image.rotation}
				</td>
				<td>
						${image.scale}
				</td>
				<shiro:hasPermission name="ftc:product:image:edit"><td>
    				<a href="${ctx}/ftc/product/image/form?id=${image.id}">修改</a>
					<a href="${ctx}/ftc/product/image/delete?id=${image.id}" onclick="return confirmx('确认要删除该图片吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>