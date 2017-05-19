<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设计管理</title>
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
		<li class="active"><a href="${ctx}/ftc/product/design/">设计列表</a></li>
		<shiro:hasPermission name="ftc:product:design:edit"><li><a href="${ctx}/ftc/product/design/form">设计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="design" action="${ctx}/ftc/product/design/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设计者：</label>
				<sys:treeselect id="createBy" name="createBy.id" value="${design.createBy.id}" labelName="" labelValue="${design.}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
				<th>编号</th>
				<th>商品id</th>
				<th>设计费</th>
				<th>设计者</th>
				<th>create_date</th>
				<th>状态</th>
				<shiro:hasPermission name="ftc:product:design:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="design">
			<tr>
				<td><a href="${ctx}/ftc/product/design/form?id=${design.id}">
					${design.name}
				</a></td>
				<td>
					${design.code}
				</td>
				<td>
					${design.productId}
				</td>
				<td>
					${design.price}
				</td>
				<td>
					${design.}
				</td>
				<td>
					<fmt:formatDate value="${design.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${design.designStatus}
				</td>
				<shiro:hasPermission name="ftc:product:design:edit"><td>
    				<a href="${ctx}/ftc/product/design/form?id=${design.id}">修改</a>
					<a href="${ctx}/ftc/product/design/delete?id=${design.id}" onclick="return confirmx('确认要删除该设计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>