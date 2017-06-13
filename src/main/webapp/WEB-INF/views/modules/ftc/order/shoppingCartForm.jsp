<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>购物车管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ftc/order/shoppingCart/">购物车列表</a></li>
		<li class="active"><a href="${ctx}/ftc/order/shoppingCart/form?id=${shoppingCart.id}">购物车<shiro:hasPermission name="ftc:order:shoppingCart:edit">${not empty shoppingCart.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ftc:order:shoppingCart:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shoppingCart" action="${ctx}/ftc/order/shoppingCart/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="product.name" htmlEscape="false" maxlength="64" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品规格：</label>
			<div class="controls">
				<form:input path="productSpec.spec.name" htmlEscape="false" maxlength="64" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">顾客：</label>
			<div class="controls">
				<form:input path="customer.userName" htmlEscape="false" maxlength="64" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">购买数量：</label>
			<div class="controls">
				<form:input path="buyNumber" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">购物车状态：</label>
			<div class="controls">
				<form:select path="checkStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_cart_check_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ftc:order:shoppingCart:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>