<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
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
		<li class="active"><a href="${ctx}/ftc/customer/customer/">会员列表</a></li>
		<shiro:hasPermission name="ftc:customer:customer:edit"><li><a href="${ctx}/ftc/customer/customer/form">会员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="customer" action="${ctx}/ftc/customer/customer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户编号：</label>
				<form:input path="userCode" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="userName" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_customer_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>手机号码：</label>
				<form:input path="telephone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户编号</th>
				<th>昵称</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>状态</th>
				<th>手机号码</th>
				<th>最后登录时间</th>
				<th>最后登录IP</th>
				<th>注册时间</th>
				<th>消费额</th>
				<shiro:hasPermission name="ftc:customer:customer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="customer">
			<tr>
				<td><a href="${ctx}/ftc/customer/customer/form?id=${customer.id}">
					${customer.userCode}
				</a></td>
				<td>
					${customer.userName}
				</td>
				<td>
					${customer.realName}
				</td>
				<td>
					${fns:getDictLabel(customer.sex, 'sex', '')}
				</td>
				<td>
					${customer.age}
				</td>
				<td>
					${fns:getDictLabel(customer.status, 'ftc_customer_status', '')}
				</td>
				<td>
					${customer.telephone}
				</td>
				<td>
					<fmt:formatDate value="${customer.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${customer.lastLoginIp}
				</td>
				<td>
					<fmt:formatDate value="${customer.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${customer.amount}
				</td>
				<shiro:hasPermission name="ftc:customer:customer:edit"><td>
    				<a href="${ctx}/ftc/customer/customer/form?id=${customer.id}">修改</a>
					<a href="${ctx}/ftc/customer/customer/delete?id=${customer.id}" onclick="return confirmx('确认要删除该会员吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>