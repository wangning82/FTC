package com.thinkgem.jeesite.modules.ftc.constant;

/**
 * Created by houyi on 2017/6/20 0020.
 */
public enum FlagEnum {

    Flag_NO("0", "否"),
    Flag_YES("1", "是");

    private String value;

    private String valueInfo;

    FlagEnum(String value, String valueInfo) {
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


