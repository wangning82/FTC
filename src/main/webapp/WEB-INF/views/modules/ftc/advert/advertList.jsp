<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告位管理</title>
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
		<li class="active"><a href="${ctx}/ftc/advert/advert/">广告位列表</a></li>
		<shiro:hasPermission name="ftc:advert:advert:edit"><li><a href="${ctx}/ftc/advert/advert/form">广告位添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="advert" action="${ctx}/ftc/advert/advert/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>代码简称：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_advert_advert_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>广告位类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_advert_advert_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>名称</th>
				<th>宽度</th>
				<th>高度</th>
				<th>描述</th>
				<th>代码简称</th>
				<th>模版内容</th>
				<th>默认显示个数</th>
				<th>广告数量</th>
				<th>状态</th>
				<th>广告位类型</th>
				<th>创建时间</th>
				<th>创建者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ftc:advert:advert:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="advert">
			<tr>
				<td><a href="${ctx}/ftc/advert/advert/form?id=${advert.id}">
					${advert.name}
				</a></td>
				<td>
					${advert.width}
				</td>
				<td>
					${advert.height}
				</td>
				<td>
					${advert.description}
				</td>
				<td>
					${advert.code}
				</td>
				<td>
					${advert.template}
				</td>
				<td>
					${advert.defultNumber}
				</td>
				<td>
					${advert.number}
				</td>
				<td>
					${fns:getDictLabel(advert.status, 'ftc_advert_advert_status', '')}
				</td>
				<td>
					${fns:getDictLabel(advert.type, 'ftc_advert_advert_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${advert.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${advert.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${advert.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ftc:advert:advert:edit"><td>
    				<a href="${ctx}/ftc/advert/advert/form?id=${advert.id}">修改</a>
					<a href="${ctx}/ftc/advert/advert/delete?id=${advert.id}" onclick="return confirmx('确认要删除该广告位吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>