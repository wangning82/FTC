<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ftc/product/specification/">规格列表</a></li>
		<li class="active"><a href="${ctx}/ftc/product/specification/form?id=${specification.id}">规格<shiro:hasPermission name="ftc:product:specification:edit">${not empty specification.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ftc:product:specification:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="specification" action="${ctx}/ftc/product/specification/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
				<sys:treeselect id="category" name="category.id" value="${specification.category.id}" labelName="" labelValue="${specification.category.name}"
								title="分类" url="/ftc/product/category/treeData" extId="${specification.category.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_product_spec_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">规格属性表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>规格属性名称</th>
								<shiro:hasPermission name="ftc:product:specification:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="specAttributeList">
						</tbody>
						<shiro:hasPermission name="ftc:product:specification:edit"><tfoot>
							<tr><td colspan="3"><a href="javascript:" onclick="addRow('#specAttributeList', specAttributeRowIdx, specAttributeTpl);specAttributeRowIdx = specAttributeRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="specAttributeTpl">//<!--
						<tr id="specAttributeList{{idx}}">
							<td class="hide">
								<input id="specAttributeList{{idx}}_id" name="specAttributeList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="specAttributeList{{idx}}_delFlag" name="specAttributeList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="specAttributeList{{idx}}_name" name="specAttributeList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="64" class="input-small "/>
							</td>
							<shiro:hasPermission name="ftc:product:specification:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#specAttributeList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var specAttributeRowIdx = 0, specAttributeTpl = $("#specAttributeTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(specification.specAttributeList)};
							for (var i=0; i<data.length; i++){
								addRow('#specAttributeList', specAttributeRowIdx, specAttributeTpl, data[i]);
								specAttributeRowIdx = specAttributeRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="ftc:product:specification:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>