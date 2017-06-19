<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收货地址管理</title>
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
		<li class="active"><a href="${ctx}/ftc/customer/address/">收货地址列表</a></li>
		<shiro:hasPermission name="ftc:customer:address:edit"><li><a href="${ctx}/ftc/customer/address/form">收货地址添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="address" action="${ctx}/ftc/customer/address/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="customer.userName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="userPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>地址标签</th>
				<th>手机号码</th>
				<th>省份名字</th>
				<th>城市名字</th>
				<th>区域名字</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ftc:customer:address:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="address">
			<tr>
				<td><a href="${ctx}/ftc/customer/address/form?id=${address.id}">
					${address.customer.userName}
				</a></td>
				<td>
					${address.userTag}
				</td>
				<td>
					${address.userPhone}
				</td>
				<td>
					${address.province.name}
				</td>
				<td>
					${address.city.name}
				</td>
				<td>
					${address.district.name}
				</td>
				<td>
					<fmt:formatDate value="${address.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ftc:customer:address:edit"><td>
    				<a href="${ctx}/ftc/customer/address/form?id=${address.id}">修改</a>
					<a href="${ctx}/ftc/customer/address/delete?id=${address.id}" onclick="return confirmx('确认要删除该收货地址吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>