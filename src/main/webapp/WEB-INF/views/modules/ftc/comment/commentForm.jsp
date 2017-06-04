<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
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
		<li><a href="${ctx}/ftc/comment/comment/">评论列表</a></li>
		<li class="active"><a href="${ctx}/ftc/comment/comment/form?id=${comment.id}">评论<shiro:hasPermission name="ftc:comment:comment:edit">${not empty comment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ftc:comment:comment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="comment" action="${ctx}/ftc/comment/comment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商品：</label>
			<div class="controls">
				<form:input path="product" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户ID：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${comment.user.id}" labelName="" labelValue="${comment.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				<form:input path="user.name" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户头像：</label>
			<div class="controls">
				<form:input path="user.photo" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单编号：</label>
			<div class="controls">
				<form:input path="order.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论星级：</label>
			<div class="controls">
				<form:input path="star" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">好评数：</label>
			<div class="controls">
				<form:input path="goodCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_comment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_comment_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">评论回复表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>用户ID</th>
								<th>昵称</th>
								<th>用户头像</th>
								<th>评论内容</th>
								<th>好评数</th>
								<th>状态</th>
								<th>评论类型</th>
								<shiro:hasPermission name="ftc:comment:comment:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="replyList">
						</tbody>
						<shiro:hasPermission name="ftc:comment:comment:edit"><tfoot>
							<tr><td colspan="9"><a href="javascript:" onclick="addRow('#replyList', replyRowIdx, replyTpl);replyRowIdx = replyRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="replyTpl">//<!--
						<tr id="replyList{{idx}}">
							<td class="hide">
								<input id="replyList{{idx}}_id" name="replyList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="replyList{{idx}}_delFlag" name="replyList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<sys:treeselect id="replyList{{idx}}_user" name="replyList[{{idx}}].user.id" value="{{row.user.id}}" labelName="replyList{{idx}}.user.name" labelValue="{{row.user.id}}"
									title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
							</td>
							<td>
								<input id="replyList{{idx}}_userName" name="replyList[{{idx}}].userName" type="text" value="{{row.userName}}" maxlength="30" class="input-small "/>
							</td>
							<td>
								<input id="replyList{{idx}}_picImg" name="replyList[{{idx}}].picImg" type="text" value="{{row.picImg}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="replyList{{idx}}_content" name="replyList[{{idx}}].content" rows="4" maxlength="255" class="input-small ">{{row.content}}</textarea>
							</td>
							<td>
								<input id="replyList{{idx}}_goodCount" name="replyList[{{idx}}].goodCount" type="text" value="{{row.goodCount}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<select id="replyList{{idx}}_status" name="replyList[{{idx}}].status" data-value="{{row.status}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('ftc_commont_reply_status')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select id="replyList{{idx}}_type" name="replyList[{{idx}}].type" data-value="{{row.type}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('ftc_commont_reply_type')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<shiro:hasPermission name="ftc:comment:comment:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#replyList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var replyRowIdx = 0, replyTpl = $("#replyTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(comment.replyList)};
							for (var i=0; i<data.length; i++){
								addRow('#replyList', replyRowIdx, replyTpl, data[i]);
								replyRowIdx = replyRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="ftc:comment:comment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>