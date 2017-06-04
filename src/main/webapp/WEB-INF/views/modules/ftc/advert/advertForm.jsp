<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告管理</title>
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
		<li><a href="${ctx}/ftc/advert/">广告位列表</a></li>
		<li class="active"><a href="${ctx}/ftc/advert/form?id=${advert.id}">广告位<shiro:hasPermission name="ftc:advert:advert:edit">${not empty advert.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ftc:advert:advert:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="advert" action="${ctx}/ftc/advert/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宽度：</label>
			<div class="controls">
				<form:input path="width" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高度：</label>
			<div class="controls">
				<form:input path="height" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代码简称：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">模版内容：</label>--%>
			<%--&lt;%&ndash;<div class="controls">&ndash;%&gt;--%>
				<%--&lt;%&ndash;<form:input path="template" htmlEscape="false" class="input-xlarge "/>&ndash;%&gt;--%>
			<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
			<%--${template}--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">默认显示个数：</label>
			<div class="controls">
				<form:input path="defultNumber" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">广告数量：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_advert_advert_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">广告位类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ftc_advert_advert_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">广告管理表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>标题</th>
								<th>排序</th>
								<th>链接地址</th>
								<th>状态</th>
								<th>展示图片</th>
								<th>备注信息</th>
								<th>广告起始时间</th>
								<th>广告结束时间</th>
								<th>广告内容</th>
								<shiro:hasPermission name="ftc:advert:advert:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="advertDetailList">
						</tbody>
						<shiro:hasPermission name="ftc:advert:advert:edit"><tfoot>
							<tr><td colspan="11"><a href="javascript:" onclick="addRow('#advertDetailList', advertDetailRowIdx, advertDetailTpl);advertDetailRowIdx = advertDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="advertDetailTpl">//<!--
						<tr id="advertDetailList{{idx}}">
							<td class="hide">
								<input id="advertDetailList{{idx}}_id" name="advertDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="advertDetailList{{idx}}_delFlag" name="advertDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="advertDetailList{{idx}}_title" name="advertDetailList[{{idx}}].title" type="text" value="{{row.title}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="advertDetailList{{idx}}_sort" name="advertDetailList[{{idx}}].sort" type="text" value="{{row.sort}}" maxlength="9" class="input-small "/>
							</td>
							<td>
								<input id="advertDetailList{{idx}}_href" name="advertDetailList[{{idx}}].href" type="text" value="{{row.href}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<select id="advertDetailList{{idx}}_status" name="advertDetailList[{{idx}}].status" data-value="{{row.status}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('ftc_advert_advert_status')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
							   <sys:ckfinder input="advertDetailList{{idx}}_picImg" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="200"
                          maxHeight="100" />
								<input id="advertDetailList{{idx}}_picImg" name="advertDetailList[{{idx}}].picImg" type="hidden" value="{{row.picImg}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="advertDetailList{{idx}}_remarks" name="advertDetailList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<td>
								<input id="advertDetailList{{idx}}_beginTime" name="advertDetailList[{{idx}}].beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.beginTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="advertDetailList{{idx}}_endTime" name="advertDetailList[{{idx}}].endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.endTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<textarea id="advertDetailList{{idx}}_content" name="advertDetailList[{{idx}}].content" rows="4" maxlength="255" class="input-small ">{{row.content}}</textarea>
							</td>
							<shiro:hasPermission name="ftc:advert:advert:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#advertDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var advertDetailRowIdx = 0, advertDetailTpl = $("#advertDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(advert.advertDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#advertDetailList', advertDetailRowIdx, advertDetailTpl, data[i]);
								advertDetailRowIdx = advertDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="ftc:advert:advert:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>