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
		<li class="active"><a href="${ctx}/ftc/comment/comment/">评论列表</a></li>
		<shiro:hasPermission name="ftc:comment:comment:edit"><li><a href="${ctx}/ftc/comment/comment/form">评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comment" action="${ctx}/ftc/comment/comment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品：</label>
				<form:input path="product" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户：</label>
				<sys:treeselect id="customer" name="customer.id" value="${comment.customer.userName}" labelName="" labelValue="${comment.customer.userName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>

			<li><label>订单：</label>
				<form:input path="order" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_comment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>评论类型：</label>
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
				<th>商品</th>
				<th>用户</th>
				<th>订单</th>

				<th>评论星级</th>
				<th>评论内容</th>
				<th>好评数</th>
				<th>状态</th>
				<th>评论类型</th>
				<th>创建时间</th>
				<th>创建者</th>
				<shiro:hasPermission name="ftc:comment:comment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comment">
			<tr>
				<td><a href="${ctx}/ftc/product/product/form?id=${commont.product.id}">
						${comment.product.name}
				</a></td>
				<td>
						${comment.customer.userName}
				</td>
				<td><a href="${ctx}/ftc/order/order/form?id=${comment.order.id}">
						${comment.order.orderNo}
				</a>
				</td>



				<td>
					${comment.star}
				</td>
				<td>
					${comment.content}
				</td>
				<td>
					${comment.goodCount}
				</td>
				<td>
					${fns:getDictLabel(comment.status, 'ftc_comment_status', '')}
				</td>
				<td>
					${fns:getDictLabel(comment.type, 'ftc_comment_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${comment.createBy.id}
				</td>
				<shiro:hasPermission name="ftc:comment:comment:edit"><td>
    				<a href="${ctx}/ftc/comment/comment/form?id=${comment.id}">修改</a>
					<a href="${ctx}/ftc/comment/comment/delete?id=${comment.id}" onclick="return confirmx('确认要删除该评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>