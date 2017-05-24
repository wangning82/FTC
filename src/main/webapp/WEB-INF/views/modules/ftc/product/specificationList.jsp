<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格管理</title>
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
		<li class="active"><a href="${ctx}/ftc/product/specification/">规格列表</a></li>
		<shiro:hasPermission name="ftc:product:specification:edit"><li><a href="${ctx}/ftc/product/specification/form">规格添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="specification" action="${ctx}/ftc/product/specification/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>规格名称：</label>
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
				<th>分类</th>
				<th>规格名称</th>
				<th>状态</th>
				<th>排序</th>
				<th>创建时间</th>
				<th>创建者</th>
				<shiro:hasPermission name="ftc:product:specification:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="specification">
			<tr>
				<td>
						${specification.category.name}
				</td>
				<td><a href="${ctx}/ftc/product/specification/form?id=${specification.id}">
					${specification.name}
				</a></td>
				<td>
					${fns:getDictLabel(specification.status, 'ftc_product_spec_status', '')}
				</td>
				<td>
					${specification.sort}
				</td>
				<td>
					<fmt:formatDate value="${specification.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${specification.createBy.id}
				</td>
				<shiro:hasPermission name="ftc:product:specification:edit"><td>
    				<a href="${ctx}/ftc/product/specification/form?id=${specification.id}">修改</a>
					<a href="${ctx}/ftc/product/specification/delete?id=${specification.id}" onclick="return confirmx('确认要删除该规格吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>