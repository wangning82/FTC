<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商品管理</title>
    <meta name="decorator" content="default"/>
    <style type="text/css">
        .spec {
            margin-left: 30px;
        }
    </style>
    <script type="text/javascript">
		var specs=[];
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }


            });

			$(":checkbox").click(function(){
				fixTable(this);
//				alert("点击规格属性");
			});
        });

        function addRow(list, idx, tpl, row) {

			var  ids='';
			var  names='';
			$(":radio[checked]").each(function (index,element){
				ids+=element.value;
				names+=element.title;
			})
			alert(ids);
			alert(names);








            $(list).append(Mustache.render(tpl, {
                idx: idx, delBtn: true, row: row
            }));
            $(list + idx).find("select").each(function () {
                $(this).val($(this).attr("data-value"));
            });
            $(list + idx).find("input[type='checkbox'], input[type='radio']").each(function () {
                var ss = $(this).attr("data-value").split(',');
                for (var i = 0; i < ss.length; i++) {
                    if ($(this).val() == ss[i]) {
                        $(this).attr("checked", "checked");
                    }
                }
            });
        }
        function delRow(obj, prefix) {
            var id = $(prefix + "_id");
            var delFlag = $(prefix + "_delFlag");
            if (id.val() == "") {
                $(obj).parent().parent().remove();
            } else if (delFlag.val() == "0") {
                delFlag.val("1");
                $(obj).html("&divide;").attr("title", "撤销删除");
                $(obj).parent().parent().addClass("error");
            } else if (delFlag.val() == "1") {
                delFlag.val("0");
                $(obj).html("&times;").attr("title", "删除");
                $(obj).parent().parent().removeClass("error");
            }
        }
        function categoryTreeselectCallBack(v, h, f) {
//            动态增加规格数据
            $.ajax({
                type: "GET",
                url: "${ctx}/ftc/product/specification/treeData",
                data: {"extId": $("#categoryId").val()},
                dataType: "json",
                success: function (data) {
                    $('#specTable').empty();   //清空resText里面的所有内容
                    var html = '';
                    $.each(data, function (index,spec) {
                        html+="<tr>";
                        html += "<td><label class='control-label'>" + spec.name + "：</label></td>";
                        $.each(spec.specAttributeList, function (index,attribute) {
                            html += "<td><input onclick='fixTable()' type='radio' name='"+spec.id+"'title='" + attribute.name + "' value='" + attribute.id + "' style='margin-left: 30px'/>"+attribute.name+"</td>"

                        });
                        html += "</tr>";
                    });
                    $('#specTable').html(html);
                }
            });
        }


    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/ftc/product/product/">商品列表</a></li>
    <li class="active"><a href="${ctx}/ftc/product/product/form?id=${product.id}">商品<shiro:hasPermission
            name="ftc:product:product:edit">${not empty product.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="ftc:product:product:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="product" action="${ctx}/ftc/product/product/save" method="post"
           class="form-horizontal">
<form:hidden path="id"/>
<sys:message content="${message}"/>
<div class="control-group">
    <label class="control-label">分类：</label>
    <div class="controls">
        <sys:treeselect id="category" name="category.id" value="${category.id}" labelName=""
                        labelValue="${category.name}"
                        title="分类" url="/ftc/product/category/treeData" extId="${category.id}" cssClass=""
                        allowClear="true"/>
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
        <sys:ckfinder input="picImg" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100"
                      maxHeight="100"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label">商品规格：</label>

    <div class="controls">
        <table id="specTable">
            <tr>

            <td><label class="control-label">颜色：</label></td>
            <td><input type="checkbox" name="color" value="1" style="margin-left: 30px"/>red
            </td>
            <td><input type="checkbox" name="color" value="2" style="margin-left: 30px"/>yellow
            </td>
            <td><input type="checkbox" name="color" value="2" style="margin-left: 30px"/>yellow
            </td>

            <td><input type="checkbox" name="color" value="2" style="margin-left: 30px"/>yellow
            </td>

        </tr>
        </table>


    </div>
</div>
    <%--<script type="text/template" id="specTpl">//<!----%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label" >规格：</label>--%>
    <%--<div class="controls">--%>

    <%--</div>--%>
    <%--<div class="controls">--%>
    <%--<form:hidden id="picImg" path="picImg" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
    <%--<sys:ckfinder input="picImg" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--//-->--%>
    <%--</script>--%>
<div class="control-group">
    <label class="control-label">商品规格数据：</label>
    <div class="controls">
        <table id="contentTable" class="table table-striped table-bordered table-condensed">
            <thead>
            <tr>
                <th class="hide"></th>
                <th>规格</th>
                <th>库存</th>
                <th>价格</th>
                <th>积分</th>
                <shiro:hasPermission name="ftc:product:product:edit">
                    <th width="10">&nbsp;</th>
                </shiro:hasPermission>
            </tr>
            </thead>
            <tbody id="specList">
            </tbody>
            <shiro:hasPermission name="ftc:product:product:edit">
                <tfoot>
                <tr>
                    <td colspan="4"><a href="javascript:"
                                       onclick="addRow('#specList', specRowIdx, specTpl);specRowIdx = specRowIdx + 1;"
                                       class="btn">新增</a></td>
                </tr>
                </tfoot>
            </shiro:hasPermission>
        </table>
        <script type="text/template" id="specTpl">//<!--
						<tr id="specs{{idx}}">
							<td class="hide">
								<input id="specs{{idx}}_id" name="specs[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="specs{{idx}}_delFlag" name="specs[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="specs{{idx}}_name" name="specs[{{idx}}].productSpecNumber" type="text" value="{{row.productSpecNumber}}" maxlength="100" class="input-small "/>
							</td>
							<td>
								<input id="specs{{idx}}_stock" name="specs[{{idx}}].stock" type="text" value="{{row.stock}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="specs{{idx}}_price" name="specs[{{idx}}].price" type="text" value="{{row.price}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="specs{{idx}}_store" name="specs[{{idx}}].store" type="text" value="{{row.store}}" maxlength="255" class="input-small "/>
							</td>
							<shiro:hasPermission name="ftc:product:product:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#specs{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
        </script>
        <script type="text/javascript">
            var specRowIdx = 0, specTpl = $("#specTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g, "");
            $(document).ready(function () {
                var data = ${fns:toJson(product.specs)};
                for (var i = 0; i < data.length; i++) {
                    addRow('#specList', specRowIdx, specTpl, data[i]);
                    specRowIdx = specRowIdx + 1;
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
            <form:options items="${fns:getDictList('ftc_product_product_showInTop')}" itemLabel="label"
                          itemValue="value" htmlEscape="false"/>
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
                <form:options items="${fns:getDictList('ftc_product_product_showInShelve')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
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
        <shiro:hasPermission name="ftc:product:product:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                                    value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    </form:form>
</body>
</html>