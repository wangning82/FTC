<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>位置管理</title>
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
		<li class="active"><a href="${ctx}/ftc/product/position/">位置列表</a></li>
		<shiro:hasPermission name="ftc:product:position:edit"><li><a href="${ctx}/ftc/product/position/form">位置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="position" action="${ctx}/ftc/product/position/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>类目：</label>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

				<th>名称</th>
				<th>分类</th>
				<th>排序</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="ftc:product:position:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody >
		<c:forEach items="${page.list}" var="position">
			<tr>
				<td><a href="${ctx}/ftc/product/position/form?id=${position.id}">

						${position.name}
				</a></td>
				<td>
						${position.category.name}
				</td>
				<td>
						${position.sort}
				</td>
				<td>
						${position.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${product.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>

				</td>
				<td>
						${position.remarks}
				</td>
				<shiro:hasPermission name="ftc:product:position:edit"><td>
					<a href="${ctx}/ftc/product/position/form?id=${position.id}">修改</a>
					<a href="${ctx}/ftc/product/position/delete?id=${position.id}" onclick="return confirmx('确认要删除该位置及所有子位置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>