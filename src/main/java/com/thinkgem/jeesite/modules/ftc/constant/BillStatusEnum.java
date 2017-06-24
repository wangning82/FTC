package com.thinkgem.jeesite.modules.ftc.constant;

/**
 * Created by houyi on 2017/6/20 0020.
 */
public enum BillStatusEnum {

    APPLY("1", "申请"),
    ARRIVE("2", "到账"),
    FAIL("3", "未通过"),
    REFUND("4", "退款");

    private String value;

    private String valueInfo;

    BillStatusEnum(String value, String valueInfo){
        this.value = value;
        this.valueInfo = valueInfo;
    }

    public String getValue() {
        return value;
    }

    public String getValueInfo() {
        return valueInfo;
    }

}
