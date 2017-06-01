<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设计管理</title>
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
		<li><a href="${ctx}/ftc/product/design/">设计列表</a></li>
		<li class="active"><a href="${ctx}/ftc/product/design/form?id=${design.id}">设计<shiro:hasPermission name="ftc:product:design:edit">${not empty design.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ftc:product:design:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="design" action="${ctx}/ftc/product/design/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">编号：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品：</label>
			<div class="controls">
				<form:hidden path="product.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				<form:input path="product.name" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设计费：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设计图片：</label>

			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
					<tr>
						<th>位置</th>
						<th>图片</th>
						<th>旋转</th>
						<th>缩放</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${design.images}" var="image">
						<tr>

							<td>
									${image.position.name}
							</td>
							<td>
									<img src="${image.url}" height="40px"/>
							</td>
							<td>
									${image.rotation}
							</td>
							<td>
									${image.scale}
							</td>

						</tr>
					</c:forEach>

					</tbody>


				</table>


			</div>
		</div>





		<div class="form-actions">
			<shiro:hasPermission name="ftc:product:design:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>