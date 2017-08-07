<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>

<c:set var="prefix" value="<%=basePath%>"/>
<html>
<head>
    <title>商品规格管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
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

        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/ftc/product/productSpec/">商品规格列表</a></li>
    <li class="active"><a href="${ctx}/ftc/product/productSpec/form?id=${productSpec.id}">商品规格<shiro:hasPermission
            name="ftc:product:productSpec:edit">${not empty productSpec.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="ftc:product:productSpec:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="productSpec" action="${ctx}/ftc/product/productSpec/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">商品规格编号：</label>
        <div class="controls">
            <form:input path="productSpecNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">商品：</label>
        <div class="controls">
            <form:input path="product.name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">规格：</label>
        <div class="controls">
            <form:input path="spec.name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">库存：</label>
        <div class="controls">
            <form:input path="stock" htmlEscape="false" maxlength="11" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">销售量：</label>
        <div class="controls">
            <form:input path="salesVolume" htmlEscape="false" maxlength="11" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">价格：</label>
        <div class="controls">
            <form:input path="price" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">积分：</label>
        <div class="controls">
            <form:input path="score" htmlEscape="false" maxlength="11" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">是否默认：</label>
        <div class="controls">
            <form:select path="defaultStatus" class="input-xlarge ">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ftc_product_productspec_isdefault')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">商品图片：</label>
        <div class="controls">
            <table id="imageTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th class="hide"></th>
                    <th>位置</th>
                    <th>图片</th>
                    <shiro:hasPermission name="ftc:product:product:edit">
                        <th width="10">&nbsp;</th>
                    </shiro:hasPermission>
                </tr>

                </thead>
                <tbody id="imageList">

                </tbody>
                <shiro:hasPermission name="ftc:product:product:edit">
                    <tfoot>
                    <tr>
                        <td colspan="4"><a href="javascript:"
                                           onclick="addRow('#imageList', imageRowIdx, imageTpl);imageRowIdx = imageRowIdx + 1;"
                                           class="btn">新增</a></td>
                    </tr>
                    </tfoot>
                </shiro:hasPermission>
            </table>
        </div>
        <script type="text/template" id="imageTpl">//<!--
<tr>
							<td class="hide">
								<input id="images{{idx}}_id" name="images[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="images{{idx}}_spec_id" name="images[{{idx}}].productSpec.id" type="hidden" value="{{row.productSpec.id}}" maxlength="100" class="input-small "/>
							<%--<form:hidden id="images{{idx}}_url" path="images[{{idx}}].url" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
    <input id="images{{idx}}_url" name="images[{{idx}}].imgUrl" type="hidden" value="{{row.imgUrl}}" maxlength="100" class="input-small "/>

							</td>
							<td>
							  <sys:treeselect id="images{{idx}}_position" name="images[{{idx}}].position.id" value="{{row.position.id}}" labelName="{{row.positon.name}}"
                            labelValue="{{row.position.name}}"
                            title="位置" url="/ftc/product/position/treeData" extId="${productSpec.product.id}" cssClass="input-medium"
                            allowClear="true"/>
</td>
							<td>
        <sys:ckfinder input="images{{idx}}_url" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="200"
                          maxHeight="100" urlPrefix="${prefix}"/>

								</td>

							<shiro:hasPermission name="ftc:product:product:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#images{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission></tr>
						//-->
        </script>
        <script type="text/javascript">
            var imageRowIdx = 0,  imageTpl = $("#imageTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g, "");
            $(document).ready(function () {
                var data = ${fns:toJson(images)};
                for (var i = 0; i < data.length; i++) {
                    addRow('#imageList', imageRowIdx, imageTpl, data[i]);
                    imageRowIdx = imageRowIdx + 1;
                };

            });
            function addRow(list, idx, tpl, row) {
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
        </script>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="ftc:product:productSpec:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                        type="submit"
                                                                        value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>