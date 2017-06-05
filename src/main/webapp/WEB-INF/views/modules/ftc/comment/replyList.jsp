<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>回复管理</title>
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
		<li class="active"><a href="${ctx}/ftc/comment/reply/">回复列表</a></li>
		<shiro:hasPermission name="ftc:comment:reply:edit"><li><a href="${ctx}/ftc/comment/reply/form">回复添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reply" action="${ctx}/ftc/comment/reply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评论内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_commont_reply_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>评论类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_commont_reply_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>评论内容</th>
				<th>评论类型</th>
				<th>是否显示</th>
				<th>客户昵称</th>
				<th>好评数</th>
				<th>创建时间</th>
				<shiro:hasPermission name="ftc:comment:reply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reply">
			<tr>
				<td><a href="${ctx}/ftc/comment/reply/form?id=${reply.id}">
					${reply.content}
				</a></td>
				<td>
						${fns:getDictLabel(reply.type, 'ftc_commont_reply_type', '')}
				</td>
				<td>
						${fns:getDictLabel(reply.status, 'ftc_commont_reply_status', '')}
				</td>
				<td>
					${reply.customer.userName}
				</td>
				<td>
					${reply.goodCount}
				</td>
				<td>
					<fmt:formatDate value="${reply.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ftc:comment:reply:edit"><td>
    				<a href="${ctx}/ftc/comment/reply/form?id=${reply.id}">修改</a>
					<a href="${ctx}/ftc/comment/reply/delete?id=${reply.id}" onclick="return confirmx('确认要删除该回复吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>