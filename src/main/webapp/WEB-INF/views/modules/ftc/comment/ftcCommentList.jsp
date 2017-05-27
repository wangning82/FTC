<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
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
		<li class="active"><a href="${ctx}/ftc/comment/ftcComment/">评论列表</a></li>
		<shiro:hasPermission name="ftc:comment:ftcComment:edit"><li><a href="${ctx}/ftc/comment/ftcComment/form">评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ftcComment" action="${ctx}/ftc/comment/ftcComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评论内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：1.显示；0.隐藏：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_comment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>评论类型：1,优质；0,普通：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_comment_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>商品ID</th>
				<th>用户ID</th>
				<th>订单ID</th>
				<th>评论星级：1,2,3,4,5</th>
				<th>评论内容</th>
				<th>好评数</th>
				<th>状态：1.显示；0.隐藏</th>
				<th>评论类型：1,优质；0,普通</th>
				<th>创建时间</th>
				<th>创建者</th>
				<shiro:hasPermission name="ftc:comment:ftcComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ftcComment">
			<tr>
				<td><a href="${ctx}/ftc/comment/ftcComment/form?id=${ftcComment.id}">
					${ftcComment.product}
				</a></td>
				<td>
					${ftcComment.}
				</td>
				<td>
					${ftcComment.order}
				</td>
				<td>
					${ftcComment.star}
				</td>
				<td>
					${ftcComment.content}
				</td>
				<td>
					${ftcComment.goodCount}
				</td>
				<td>
					${fns:getDictLabel(ftcComment.status, 'ftc_comment_status', '')}
				</td>
				<td>
					${fns:getDictLabel(ftcComment.type, 'ftc_comment_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${ftcComment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ftcComment.createBy.id}
				</td>
				<shiro:hasPermission name="ftc:comment:ftcComment:edit"><td>
    				<a href="${ctx}/ftc/comment/ftcComment/form?id=${ftcComment.id}">修改</a>
					<a href="${ctx}/ftc/comment/ftcComment/delete?id=${ftcComment.id}" onclick="return confirmx('确认要删除该评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>