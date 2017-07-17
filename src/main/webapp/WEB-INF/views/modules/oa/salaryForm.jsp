<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工资管理</title>
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
		<li><a href="${ctx}/oa/salary/">工资列表</a></li>
		<li class="active"><a href="${ctx}/oa/salary/form?id=${salary.id}">工资<shiro:hasPermission name="oa:salary:edit">${not empty salary.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:salary:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="salary" action="${ctx}/oa/salary/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<form:input path="year" htmlEscape="false" maxlength="4" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<form:input path="month" htmlEscape="false" maxlength="2" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${salary.user.id}" labelName="user.name" labelValue="${salary.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">岗位工资：</label>
			<div class="controls">
				<form:input path="basicWage" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绩效工资：</label>
			<div class="controls">
				<form:input path="performanceWage" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历津贴：</label>
			<div class="controls">
				<form:input path="degreeSubsidy" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工龄津贴：</label>
			<div class="controls">
				<form:input path="ageSubsidy" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通勤补助：</label>
			<div class="controls">
				<form:input path="busSubsidy" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">餐费补助：</label>
			<div class="controls">
				<form:input path="foodSubsidy" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应发绩效：</label>
			<div class="controls">
				<form:input path="realPerformanceWage" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">防暑降温：</label>
			<div class="controls">
				<form:input path="hotSubsidy" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">津补贴合计：</label>
			<div class="controls">
				<form:input path="totalSubsidy" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">冬季采暖：</label>
			<div class="controls">
				<form:input path="coldSubsidy" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">养老保险：</label>
			<div class="controls">
				<form:input path="pensionInsurance" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">失业保险：</label>
			<div class="controls">
				<form:input path="unempolyedInsurance" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医疗保险：</label>
			<div class="controls">
				<form:input path="medicalInsurance" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">个人所得税：</label>
			<div class="controls">
				<form:input path="incomeTax" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保险：</label>
			<div class="controls">
				<form:input path="pInsurance" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公积金扣除：</label>
			<div class="controls">
				<form:input path="pBond" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应发：</label>
			<div class="controls">
				<form:input path="shoudPay" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实发：</label>
			<div class="controls">
				<form:input path="realPay" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补发工资：</label>
			<div class="controls">
				<form:input path="addWage" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">奖金：</label>
			<div class="controls">
				<form:input path="reward" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出勤扣除：</label>
			<div class="controls">
				<form:input path="deductWork" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">病/事假扣除：</label>
			<div class="controls">
				<form:input path="deductLeave" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公积金贷款利息：</label>
			<div class="controls">
				<form:input path="bondInterest" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他1：</label>
			<div class="controls">
				<form:input path="other1" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他2：</label>
			<div class="controls">
				<form:input path="other2" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他3：</label>
			<div class="controls">
				<form:input path="other3" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他4：</label>
			<div class="controls">
				<form:input path="other4" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他5：</label>
			<div class="controls">
				<form:input path="other5" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">独生子女费：</label>
			<div class="controls">
				<form:input path="singleSubsidy" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绩效得分：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:salary:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>