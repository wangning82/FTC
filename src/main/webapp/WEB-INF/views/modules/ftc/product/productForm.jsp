<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
		<li><a href="${ctx}/ftc/product/product/">商品列表</a></li>
		<li class="active"><a href="${ctx}/ftc/product/product/form?id=${product.id}">商品<shiro:hasPermission name="ftc:product:product:edit">${not empty product.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ftc:product:product:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="product" action="${ctx}/ftc/product/product/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
				<sys:treeselect id="category" name="category.id" value="${category.id}" labelName="" labelValue="${category.name}"
								title="分类" url="/ftc/product/category/treeData" extId="${category.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品编号：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签ID：</label>
			<div class="controls">
				<form:input path="labelId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">显示积分：</label>
			<div class="controls">
				<form:input path="showScore" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">显示价格：</label>
			<div class="controls">
				<form:input path="showPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品简介：</label>
			<div class="controls">
				<form:input path="introduce" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">展示图片：</label>
			<div class="controls">

			</div>
			<div class="controls">
				<form:hidden id="picImg" path="picImg" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="picImg" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
	<div class="control-group">
		<label class="control-label">业务数据子表：</label>
		<div class="controls">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
					<th class="hide"></th>
					<th>规格</th>
					<th>库存</th>
					<th>价格</th>
					<th>积分</th>
					<th>备注信息</th>
					<shiro:hasPermission name="test:testDataMain:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
				</tr>
				</thead>
				<tbody id="testDataChildList">
				</tbody>
				<shiro:hasPermission name="test:testDataMain:edit"><tfoot>
				<tr><td colspan="4"><a href="javascript:" onclick="addRow('#testDataChildList', testDataChildRowIdx, testDataChildTpl);testDataChildRowIdx = testDataChildRowIdx + 1;" class="btn">新增</a></td></tr>
				</tfoot></shiro:hasPermission>
			</table>
			<script type="text/template" id="testDataChildTpl">//<!--
						<tr id="testDataChildList{{idx}}">
							<td class="hide">
								<input id="testDataChildList{{idx}}_id" name="testDataChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="testDataChildList{{idx}}_delFlag" name="testDataChildList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="testDataChildList{{idx}}_name" name="testDataChildList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="100" class="input-small "/>
							</td>
							<td>
								<input id="testDataChildList{{idx}}_remarks" name="testDataChildList[{{idx}}].remarks" type="text" value="{{row.remarks}}" maxlength="255" class="input-small "/>
							</td>
							<shiro:hasPermission name="test:testDataMain:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#testDataChildList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
			</script>
			<script type="text/javascript">
				var testDataChildRowIdx = 0, testDataChildTpl = $("#testDataChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(testDataMain.testDataChildList)};
					for (var i=0; i<data.length; i++){
						addRow('#testDataChildList', testDataChildRowIdx, testDataChildTpl, data[i]);
						testDataChildRowIdx = testDataChildRowIdx + 1;
					}
				});
			</script>
		</div>
	</div>
	<div class="control-group">
		<div class="control-group">
			<label class="control-label">是否置顶：</label>
			<form:select path="showInTop" class="input-xlarge ">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('ftc_product_product_showInTop')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">是否导航栏 1=显示/0=隐藏：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="showInNav" htmlEscape="false" maxlength="2" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">是否热门 1=热门/0=默认：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="showInHot" htmlEscape="false" maxlength="2" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">是否上架：</label>
			<div class="controls">
				<%--<form:input path="showInShelve" htmlEscape="false" maxlength="2" class="input-xlarge "/>--%>

				<form:select path="showInShelve" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_product_product_showInShelve')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>


			</div>

		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${product.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上架时间：</label>
			<div class="controls">
				<input name="shelveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${product.shelveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上架人：</label>
			<div class="controls">
				<form:input path="shelveBy" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">搜索关键词：</label>
			<div class="controls">
				<form:input path="searchKey" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%----%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">页面标题：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="pageTitle" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">页面描述：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="pageDescription" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">页面关键词：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="pageKeyword" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ftc:product:product:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>