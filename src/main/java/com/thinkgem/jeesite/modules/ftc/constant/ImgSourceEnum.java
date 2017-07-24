package com.thinkgem.jeesite.modules.ftc.constant;

/**
 * Created by wangqingxiang on 2017/7/14.
 */
public enum ImgSourceEnum {
    IMG_SOURCE_SUCAI("0", "素材"),
    IMG_SOURCE_TOUXIANG("1", "头像"),
    IMG_SOURCE_DIANPU("2", "店铺"),
    IMG_SOURCE_GUIGE("3", "规格"),
    IMG_SOURCE_CHANPIN("5", "产品"),
    IMG_SOURCE_SHEJI("4","设计");

    private String value;

    private String valueInfo;

    ImgSourceEnum(String value, String valueInfo) {
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
