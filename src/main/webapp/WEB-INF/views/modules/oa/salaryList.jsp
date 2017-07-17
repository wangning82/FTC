<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工资管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/sys/user/export");
                        $("#searchForm").submit();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
            $("#btnImport").click(function(){
                $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
                    bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
            });
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
<div id="importBox" class="hide">
	<form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
		  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
		<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
		<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
		<a href="${ctx}/sys/user/import/template">下载模板</a>
	</form>
</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/salary/">工资列表</a></li>
		<shiro:hasPermission name="oa:salary:edit"><li><a href="${ctx}/oa/salary/form">工资添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="salary" action="${ctx}/oa/salary/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年：</label>
				<form:input path="year" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>月：</label>
				<form:input path="month" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>用户</label>
				<sys:treeselect id="user" name="user.id" value="${salary.user.id}" labelName="user.name" labelValue="${salary.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年</th>
				<th>月</th>
				<th>姓名</th>
				<th>岗位</th>
				<th>岗位工资</th>
				<th>绩效工资</th>
				<th>学历津贴</th>
				<th>工龄津贴</th>
				<th>通勤补助</th>
				<th>餐费补助</th>
				<th>应发绩效</th>
				<th>防暑降温</th>
				<th>津补贴合计</th>
				<th>冬季采暖</th>
				<th>养老保险</th>
				<th>失业保险</th>
				<th>医疗保险</th>
				<th>个人所得税</th>
				<th>保险</th>
				<th>公积金扣除</th>
				<th>应发</th>
				<th>实发</th>
				<th>补发工资</th>
				<th>奖金</th>
				<th>出勤扣除</th>
				<th>病/事假扣除</th>
				<th>公积金贷款利息</th>
				<th>其他1</th>
				<th>其他2</th>
				<th>其他3</th>
				<th>其他4</th>
				<th>其他5</th>
				<th>创建着</th>
				<th>创建时间</th>
				<th>独生子女费</th>
				<th>绩效得分</th>
				<th>更新时间</th>
				<th>创建者</th>
				<th>状态</th>
				<shiro:hasPermission name="oa:salary:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="salary">
			<tr>
				<td><a href="${ctx}/oa/salary/form?id=${salary.id}">
					${salary.year}
				</a></td>
				<td>
					${salary.month}
				</td>
				<td>
					${salary.user.name}
				</td>
				<td>
					${salary.basicWage}
				</td>
				<td>
					${salary.performanceWage}
				</td>
				<td>
					${salary.degreeSubsidy}
				</td>
				<td>
					${salary.ageSubsidy}
				</td>
				<td>
					${salary.busSubsidy}
				</td>
				<td>
					${salary.foodSubsidy}
				</td>
				<td>
					${salary.realPerformanceWage}
				</td>
				<td>
					${salary.hotSubsidy}
				</td>
				<td>
					${salary.totalSubsidy}
				</td>
				<td>
					${salary.coldSubsidy}
				</td>
				<td>
					${salary.pensionInsurance}
				</td>
				<td>
					${salary.unempolyedInsurance}
				</td>
				<td>
					${salary.medicalInsurance}
				</td>
				<td>
					${salary.incomeTax}
				</td>
				<td>
					<%--${salary.pInsurance}--%>
				</td>
				<td>
					<%--${salary.pBond}--%>
				</td>
				<td>
					${salary.shoudPay}
				</td>
				<td>
					${salary.realPay}
				</td>
				<td>
					${salary.addWage}
				</td>
				<td>
					${salary.reward}
				</td>
				<td>
					${salary.deductWork}
				</td>
				<td>
					${salary.deductLeave}
				</td>
				<td>
					${salary.bondInterest}
				</td>
				<td>
					${salary.other1}
				</td>
				<td>
					${salary.other2}
				</td>
				<td>
					${salary.other3}
				</td>
				<td>
					${salary.other4}
				</td>
				<td>
					${salary.other5}
				</td>
				<td>
					${salary.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${salary.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${salary.singleSubsidy}
				</td>
				<td>
					${salary.score}
				</td>
				<td>
					<fmt:formatDate value="${salary.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${salary.updateBy.id}
				</td>
				<td>
					${salary.status}
				</td>
				<shiro:hasPermission name="oa:salary:edit"><td>
    				<a href="${ctx}/oa/salary/form?id=${salary.id}">修改</a>
					<a href="${ctx}/oa/salary/delete?id=${salary.id}" onclick="return confirmx('确认要删除该工资吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>