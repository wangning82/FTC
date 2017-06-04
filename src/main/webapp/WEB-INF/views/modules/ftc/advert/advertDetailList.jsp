<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告管理</title>
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
		<li class="active"><a href="${ctx}/ftc/advert/detail/">广告列表</a></li>
		<shiro:hasPermission name="ftc:advert:detail:edit"><li><a href="${ctx}/ftc/advert/detail/form">广告添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="advertDetail" action="${ctx}/ftc/advert/detail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_advert_advert_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>标题</th>
				<th>排序</th>
				<th>链接地址</th>
				<th>状态</th>


				<th>广告起始时间</th>
				<th>广告结束时间</th>
				<th>广告内容</th>
				<th>备注信息</th>
				<shiro:hasPermission name="ftc:advert:detail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="advertDetail">
			<tr>
				<td><a href="${ctx}/ftc/advert/detail/form?id=${advertDetail.id}">
					${advertDetail.title}
				</a></td>
				<td>
					${advertDetail.sort}
				</td>
				<td>
					${advertDetail.href}
				</td>
				<td>
					${fns:getDictLabel(advertDetail.status, 'ftc_advert_advert_status', '')}
				</td>



				<td>
					<fmt:formatDate value="${advertDetail.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${advertDetail.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${advertDetail.content}
				</td>
				<td>
						${advertDetail.remarks}
				</td>
				<shiro:hasPermission name="ftc:advert:detail:edit"><td>
    				<a href="${ctx}/ftc/advert/detail/form?id=${advertDetail.id}">修改</a>
					<a href="${ctx}/ftc/advert/detail/delete?id=${advertDetail.id}" onclick="return confirmx('确认要删除该广告吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>