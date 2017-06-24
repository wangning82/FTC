package com.thinkgem.jeesite.modules.ftc.constant;

/**
 * Created by houyi on 2017/6/20 0020.
 */
public enum BillTypeEnum {

    ENTRY("1", "入账"),
    WITHDRAW("2", "提现"),
    CONSUME("3", "消费");

    private String value;

    private String valueInfo;

    BillTypeEnum(String value, String valueInfo){
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
