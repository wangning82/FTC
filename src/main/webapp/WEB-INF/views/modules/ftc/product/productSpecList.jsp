<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品规格管理</title>
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
		<li class="active"><a href="${ctx}/ftc/product/productSpec/">商品规格列表</a></li>
		<shiro:hasPermission name="ftc:product:productSpec:edit"><li><a href="${ctx}/ftc/product/productSpec/form">商品规格添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="productSpec" action="${ctx}/ftc/product/productSpec/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>规格编号：</label>
				<form:input path="productSpecNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品规格编号</th>
				<th>商品</th>
				<th>规格</th>
				<%--<th>库存</th>--%>
				<%--<th>销售量</th>--%>
				<th>价格</th>
				<%--<th>积分</th>--%>
				<th>是否默认</th>
				<shiro:hasPermission name="ftc:product:productSpec:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="productSpec">
			<tr>
				<td><a href="${ctx}/ftc/product/productSpec/form?id=${productSpec.id}">
					${productSpec.productSpecNumber}
				</a></td>
				<td>
					${productSpec.product.name}
				</td>
				<td>
					${productSpec.spec.name}
				</td>
				<%--<td>--%>
					<%--${productSpec.stock}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${productSpec.salesVolume}--%>
				<%--</td>--%>
				<td>
					${productSpec.price}
				</td>
				<%--<td>--%>
					<%--${productSpec.score}--%>
				<%--</td>--%>
				<td>
					${fns:getDictLabel(productSpec.defaultStatus, 'ftc_product_productspec_isdefault', '')}
				</td>

				<shiro:hasPermission name="ftc:product:productSpec:edit"><td>
    				<a href="${ctx}/ftc/product/productSpec/form?id=${productSpec.id}">修改</a>
					<a href="${ctx}/ftc/product/productSpec/delete?id=${productSpec.id}" onclick="return confirmx('确认要删除该商品规格吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>