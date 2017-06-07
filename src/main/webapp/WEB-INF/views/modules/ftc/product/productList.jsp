<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
		<li class="active"><a href="${ctx}/ftc/product/product/">商品列表</a></li>
		<shiro:hasPermission name="ftc:product:product:edit"><li><a href="${ctx}/ftc/product/product/form">商品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/ftc/product/product/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品分类：</label>
				<form:input path="category.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>商品编号：</label>
				<form:input path="number" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>创建者：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>搜索关键词：</label>
				<form:input path="searchKey" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品编号</th>
				<th>商品分类</th>
				<th>商品名称</th>
				<th>展示图片</th>
				<th>标签</th>

				<th>显示积分</th>
				<th>显示价格</th>
				<th>商品简介</th>
				<th>搜索关键词</th>


				<%--<th>是否置顶 1=置顶/0=默认</th>--%>
				<%--<th>是否导航栏 1=显示/0=隐藏</th>--%>
				<%--<th>是否热门 1=热门/0=默认</th>--%>


				<th>创建者</th>
				<th>创建时间</th>

				<th>上架人</th>
				<th>上架时间</th>

				<%--<th>更新时间</th>--%>

				<%--<th>页面标题</th>--%>
				<th>备注</th>
				<shiro:hasPermission name="ftc:product:product:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td><a href="${ctx}/ftc/product/product/form?id=${product.id}">
					${product.number}
				</a></td>
				<td>
				${product.category.name}
				</td>
				<td>
						${product.name}
				</td>
				<td>
					<img src="${product.picImg}" style="height: 50px;"/>

				</td>
				<td>
					${product.labelId}
				</td>

				<td>
					${product.showScore}
				</td>
				<td>
						${product.showPrice}
				</td>

				<td>
						${product.introduce}
				</td>
				<td>
						${product.searchKey}
				</td>

				<%--<td>--%>
					<%--${product.showInTop}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${product.showInNav}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${product.showInHot}--%>
				<%--</td>--%>


				<td>
					${product.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${product.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<td>
					${product.shelveBy.name}
				</td>
				<td>
					<fmt:formatDate value="${product.shelveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>


				<td>
					${product.remarks}
				</td>
				<shiro:hasPermission name="ftc:product:product:edit"><td>
    				<a href="${ctx}/ftc/product/product/form?id=${product.id}">修改</a>
					<a href="${ctx}/ftc/product/product/delete?id=${product.id}" onclick="return confirmx('确认要删除该商品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>