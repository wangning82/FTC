<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="fnf" uri="/WEB-INF/tlds/fnf.tld" %>
<c:set var="prefix" value="${pageContext.request.servletContext().getRealPath('')}"/>
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


        $(document).ready(function () {
            alert(${prefix});
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

            if($("#categoryId").val()!=""){
                categoryTreeselectCallBack();
            }
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
                    $.each(data, function (index, spec) {
                        html += "<tr>";
                        html += "<td><label class='control-label'>" + spec.name + "：</label></td>";
                        $.each(spec.specAttributeList, function (index, attribute) {
                            html += "<td><input type='radio' father='"+spec.name+"' name='" + spec.id + "'title='" + attribute.name + "' value='" + attribute.id + "' style='margin-left: 30px'/>" + attribute.name + "</td>"

                        });
                        html += "</tr>";
                    });
                    $('#specTable').html(html);
                }
            });
        }


        function addSpecRow(){
            var ids = '';
            var names = '';
            $(":radio[checked]").each(function (index, element) {
                if (ids.length == 0) {
                    ids += element.value;
                    names += $(element).attr("father")+":"+element.title;
                } else {
                    ids += "," + element.value;
                    names += "," +$(element).attr("father")+":"+ element.title;
                }


            })
            <%--var no=${fnf:getNextNo()};--%>
            var row = {spec: {name: names, id: ids}, productSpecNumber:"" };
            addRow('#specList', specRowIdx, specTpl,row);
            specRowIdx = specRowIdx + 1;
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
    <form:hidden path="modelFlag"/>
    <div class="control-group">
        <label class="control-label">分类：</label>
        <div class="controls">
            <sys:treeselect id="category" name="category.id" value="${product.category.id}" labelName=""
                            labelValue="${product.category.name}"
                            title="分类" url="/ftc/product/category/treeData" extId="" cssClass=""
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
        <label class="control-label">设计者：</label>
        <div class="controls">
                <input type="text" value="${product.designBy.name}" class="input-xlarge"/>
        </div>
    </div>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">标签ID：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:input path="labelId" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
        <%--</div>--%>
    <%--</div>--%>
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
        <label class="control-label">搜索关键词：</label>
        <div class="controls">
            <form:input path="searchKey" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">展示图片：</label>
        <div class="controls">

        </div>
        <div class="controls">
            <form:hidden id="picImg" path="picImg" htmlEscape="false" maxlength="255" class="input-xlarge"/>
            <sys:ckfinder input="picImg" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100"
                          maxHeight="100" urlPrefix="${prefix}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">商品规格：</label>

        <div class="controls">
            <table id="specTable">
            </table>


        </div>
    </div>

    <div class="control-group">
        <label class="control-label">商品规格数据：</label>
        <div class="controls">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th class="hide"></th>
                    <th>编号</th>
                    <th>规格</th>
                    <th>库存</th>
                    <th>价格</th>
                    <th>积分</th>
                    <th>图片</th>
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
                                           onclick="addSpecRow()"
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
								<input id="specs{{idx}}_spec_id" name="specs[{{idx}}].spec.id" type="text" value="{{row.spec.id}}" maxlength="100" class="input-small "/>
							   <input id="specs{{idx}}_picImg" name="specs[{{idx}}].picImg" type="hidden" value="{{row.picImg}}" maxlength="100" class="input-small "/>

							</td>
							<td>
								<input id="specs{{idx}}_name" name="specs[{{idx}}].productSpecNumber" type="text" value="{{row.productSpecNumber}}" maxlength="100" class="input-small "  readonly="readonly"/>
							</td>
							<td>
								<input id="specs{{idx}}_spec_name" name="specs[{{idx}}].spec.name" type="text" value="{{row.spec.name}}" readonly="readonly" maxlength="100" class="input-small "/>
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
							<td>
							    <sys:ckfinder input="specs{{idx}}_picImg" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="200"
                          maxHeight="100" />
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
                    };

                });
            </script>
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
                    <th>旋转</th>
                    <th>缩放</th>

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
								<input id="images{{idx}}_delFlag" name="images[{{idx}}].delFlag" type="hidden" value="0"/>
								<input id="images{{idx}}_spec_id" name="images[{{idx}}].image.id" type="hidden" value="{{row.image.id}}" maxlength="100" class="input-small "/>
							<%--<form:hidden id="images{{idx}}_url" path="images[{{idx}}].url" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
    <input id="images{{idx}}_url" name="images[{{idx}}].url" type="hidden" value="{{row.url}}" maxlength="100" class="input-small "/>

							</td>
							<td>
							  <sys:treeselect id="images{{idx}}_position" name="images[{{idx}}].position.id" value="${row.position.name}" labelName="${row.position.name}"
                            labelValue="${row.position.id}"
                            title="位置" url="/ftc/product/position/treeData" extId="" cssClass="input-medium"
                            allowClear="true"/>
</td>
							<td>
        <sys:ckfinder input="images{{idx}}_url" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="200"
                          maxHeight="100" />

								</td>
							<td>
								<input id="images{{idx}}_rotation" name="images[{{idx}}].rotation" type="text" value="{{row.rotation}}"  maxlength="100" class="input-medium "/>
							</td>
							<td>
								<input id="images{{idx}}_scale" name="images[{{idx}}].scale" type="text" value="{{row.scale}}" maxlength="255" class="input-medium "/>
							</td>

							<shiro:hasPermission name="ftc:product:product:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#images{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission></tr>
						//-->
        </script>
        <script type="text/javascript">
            var imageRowIdx = 0,  imageTpl = $("#imageTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g, "");
            $(document).ready(function () {
                var data = ${fns:toJson(product.images)};
                for (var i = 0; i < data.length; i++) {
                    addRow('#imageList', imageRowIdx, imageTpl, data[i]);
                    imageRowIdx = imageRowIdx + 1;
                };

            });

        </script>
    </div>
    <div class="control-group">
        <label class="control-label">是否置顶：</label>
        <div class="controls">
            <form:select path="showInTop" class="input-xlarge ">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ftc_product_product_showInTop')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>

    <%--<div class="control-group">--%>
    <%--<label class="control-label">是否导航栏 1=显示/0=隐藏：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="showInNav" htmlEscape="false" maxlength="2" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="control-group">
    <label class="control-label">是否热门</label>
    <div class="controls">
    <form:input path="showInHot" htmlEscape="false" maxlength="2" class="input-xlarge "/>
    </div>
    </div>
    <div class="control-group">
        <label class="control-label">是否上架：</label>
        <div class="controls">
                <%--<form:input path="showInShelve" htmlEscape="false" maxlength="2" class="input-xlarge "/>--%>

            <form:select path="showInShelve" class="input-xlarge " readonly="readonly">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ftc_product_product_showInShelve')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>


        </div>

    </div>



    <div class="control-group">
        <label class="control-label">创建人：</label>
        <div class="controls">
            <form:input path="createBy.name" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="readonly"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">创建时间：</label>
        <div class="controls">
            <input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${product.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">上架人：</label>
        <div class="controls">
            <form:input path="shelveBy.name" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="readonly"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">上架时间：</label>
        <div class="controls">
            <input name="shelveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${product.shelveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   />
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