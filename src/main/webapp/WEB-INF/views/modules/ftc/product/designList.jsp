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
				<sys:treeselect id="createBy" name="createBy.id" value="${design.createBy.id}" labelName="" labelValue="${design.createBy.name}"
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

				<th>模型</th>

				<th>设计者</th>
				<th>创建时间</th>
				<th>状态</th>
				<shiro:hasPermission name="ftc:product:design:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="design">
			<tr>

				<td>
					${design.model.name}
				</td>

				<td>
					${design.customer.userName}
				</td>
				<td>
					<fmt:formatDate value="${design.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fns:getDictLabel(design.designStatus, 'ftc_product_design_status', '')}
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