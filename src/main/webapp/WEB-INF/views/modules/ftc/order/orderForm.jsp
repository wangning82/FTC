<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>订单管理</title>
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
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/ftc/order/order/">订单列表</a></li>
    <li class="active"><a href="${ctx}/ftc/order/order/form?id=${order.id}">订单<shiro:hasPermission
            name="ftc:order:order:edit">${not empty order.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="ftc:order:order:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="order" action="${ctx}/ftc/order/order/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">订单编号：</label>
        <div class="controls">
            <form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">顾客：</label>
        <div class="controls">
            <sys:treeselect id="customer" name="customer.id" value="${order.customer.id}" labelName="customer.userName"
                            labelValue="${order.customer.userName}"
                            title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true"
                            notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">支付方式：</label>
        <div class="controls">
            <form:select path="payType" class="input-xlarge ">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ftc_order_pay_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">配送时间：</label>
        <div class="controls">
            <form:select path="shipmentTime" class="input-xlarge ">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ftc_order_shipment_time')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">配送方式：</label>
        <div class="controls">
            <form:select path="shipmentType" class="input-xlarge ">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ftc_order_shipment_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">快递费：</label>
        <div class="controls">
            <form:input path="shipmentAmount" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">发票类型：</label>
        <div class="controls">
            <form:select path="invoiceType" class="input-xlarge ">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ftc_order_invoice_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">发票抬头：</label>
        <div class="controls">
            <form:input path="invoiceTitle" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">订单金额：</label>
        <div class="controls">
            <form:input path="orderAmount" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">商品总数量：</label>
        <div class="controls">
            <form:input path="buyNumber" htmlEscape="false" maxlength="11" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">订单状态：</label>
        <div class="controls">
            <form:select path="orderStatus" class="input-xlarge ">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ftc_order_status')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">支付金额：</label>
        <div class="controls">
            <form:input path="payAmount" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">订单明细表：</label>
        <div class="controls">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th class="hide"></th>
                    <th>商品编号</th>
                    <th>商品名称</th>
                    <th>展示图片</th>
                    <th>商品规格编号</th>
                    <th>商品规格名称</th>
                    <th>生产成本</th>
                    <th>商品总数量</th>
                    <th>商品总金额</th>
                    <th>设计单价</th>
                    <th>设计总费用</th>
                    <th>销售价格</th>
                    <th>评论状态</th>
                    <shiro:hasPermission name="ftc:order:order:edit">
                        <th width="10">&nbsp;</th>
                    </shiro:hasPermission>
                </tr>
                </thead>
                <tbody id="orderProductList">
                </tbody>
                <shiro:hasPermission name="ftc:order:order:edit">
                    <tfoot>
                    <tr>
                        <td colspan="16">
                            <a href="javascript:" onclick="addRow('#orderProductList', orderProductRowIdx, orderProductTpl);orderProductRowIdx = orderProductRowIdx + 1;" class="btn">新增</a>
                        </td>
                    </tr>
                    </tfoot>
                </shiro:hasPermission>
            </table>
            <script type="text/template" id="orderProductTpl">//<!--
						<tr id="orderProductList{{idx}}">
							<td class="hide">
								<input id="orderProductList{{idx}}_id" name="orderProductList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="orderProductList{{idx}}_delFlag" name="orderProductList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_productNumber" name="orderProductList[{{idx}}].productNumber" type="text" value="{{row.productNumber}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_name" name="orderProductList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_picImg" name="orderProductList[{{idx}}].picImg" type="hidden" value="{{row.picImg}}" maxlength="255"/>
								<sys:ckfinder input="orderProductList{{idx}}_picImg" type="files" uploadPath="/ftc/order/order" selectMultiple="true"/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_productSpecNumber" name="orderProductList[{{idx}}].productSpecNumber" type="text" value="{{row.productSpecNumber}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_productSpecName" name="orderProductList[{{idx}}].productSpecName" type="text" value="{{row.productSpecName}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_productPrice" name="orderProductList[{{idx}}].productPrice" type="text" value="{{row.productPrice}}" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_buyNumber" name="orderProductList[{{idx}}].buyNumber" type="text" value="{{row.buyNumber}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_productAmount" name="orderProductList[{{idx}}].productAmount" type="text" value="{{row.productAmount}}" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_designPrice" name="orderProductList[{{idx}}].designPrice" type="text" value="{{row.designPrice}}" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_designAmount" name="orderProductList[{{idx}}].designAmount" type="text" value="{{row.designAmount}}" class="input-small "/>
							</td>
							<td>
								<input id="orderProductList{{idx}}_price" name="orderProductList[{{idx}}].price" type="text" value="{{row.price}}" class="input-small "/>
							</td>
							<td>
								<select id="orderProductList{{idx}}_commentStatus" name="orderProductList[{{idx}}].commentStatus" data-value="{{row.commentStatus}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('ftc_comment_status')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<shiro:hasPermission name="ftc:order:order:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#orderProductList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
            </script>
            <script type="text/javascript">
                var orderProductRowIdx = 0, orderProductTpl = $("#orderProductTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g, "");
                $(document).ready(function () {
                    var data = ${fns:toJson(order.orderProductList)};
                    for (var i = 0; i < data.length; i++) {
                        addRow('#orderProductList', orderProductRowIdx, orderProductTpl, data[i]);
                        orderProductRowIdx = orderProductRowIdx + 1;
                    }
                });
            </script>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="ftc:order:order:edit">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>