package com.thinkgem.jeesite.modules.ftc.constant;

/**
 * Created by houyi on 2017/6/20 0020.
 */
public enum PlatformTypeEnum {

    Phone("0", "手机"),
    WeChat("1", "微信"),
    QQ("2", "QQ");

    private String value;

    private String valueInfo;

    PlatformTypeEnum(String value, String valueInfo){
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
