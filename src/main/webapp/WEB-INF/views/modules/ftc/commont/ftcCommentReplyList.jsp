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
		<li class="active"><a href="${ctx}/ftc/commont/ftcCommentReply/">回复列表</a></li>
		<shiro:hasPermission name="ftc:commont:ftcCommentReply:edit"><li><a href="${ctx}/ftc/commont/ftcCommentReply/form">回复添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ftcCommentReply" action="${ctx}/ftc/commont/ftcCommentReply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评论内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：1.显示；0.隐藏：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_commont_reply_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>评论类型：1,官方回复；0,用户回复：</label>
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
				<th>昵称</th>
				<th>评论内容</th>
				<th>好评数</th>
				<th>状态：1.显示；0.隐藏</th>
				<th>评论类型：1,官方回复；0,用户回复</th>
				<th>创建时间</th>
				<shiro:hasPermission name="ftc:commont:ftcCommentReply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ftcCommentReply">
			<tr>
				<td><a href="${ctx}/ftc/commont/ftcCommentReply/form?id=${ftcCommentReply.id}">
					${ftcCommentReply.userName}
				</a></td>
				<td>
					${ftcCommentReply.content}
				</td>
				<td>
					${ftcCommentReply.goodCount}
				</td>
				<td>
					${fns:getDictLabel(ftcCommentReply.status, 'ftc_commont_reply_status', '')}
				</td>
				<td>
					${fns:getDictLabel(ftcCommentReply.type, 'ftc_commont_reply_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${ftcCommentReply.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ftc:commont:ftcCommentReply:edit"><td>
    				<a href="${ctx}/ftc/commont/ftcCommentReply/form?id=${ftcCommentReply.id}">修改</a>
					<a href="${ctx}/ftc/commont/ftcCommentReply/delete?id=${ftcCommentReply.id}" onclick="return confirmx('确认要删除该回复吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>