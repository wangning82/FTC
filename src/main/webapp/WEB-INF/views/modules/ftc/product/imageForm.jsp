<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片管理</title>
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
		<li><a href="${ctx}/ftc/product/image/">图片列表</a></li>
		<li class="active"><a href="${ctx}/ftc/product/image/form?id=${image.id}">图片<shiro:hasPermission name="ftc:product:image:edit">${not empty image.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ftc:product:image:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="image" action="${ctx}/ftc/product/image/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
				<sys:treeselect id="category" name="category.id" value="" labelName=""
								labelValue=""
								title="分类" url="/ftc/product/category/treeData" extId="" cssClass=""
								allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品：</label>
			<div class="controls">
				<sys:treeselect id="product" name="product.id" value="" labelName=""
								labelValue=""
								title="产品" url="/ftc/product/product/treeData" extId="" cssClass=""
								allowClear="true" />
			</div>
		</div>

		<div class="controls">
			<sys:treeselect id="productSpec.id" name="productSpec.id" value="" labelName=""
							labelValue=""
							title="规格" url="/ftc/product/productSpec/treeData" extId="" cssClass=""
							allowClear="true" />
		</div>
		<div class="control-group">
			<label class="control-label">展示图片：</label>
			<div class="controls">
				<form:input path="imgUrl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">位置：</label>
			<div class="controls">
				<table>
					<thead>
					<th>
						图片
					</th>
					</thead>
				</table>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="ftc:product:image:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>