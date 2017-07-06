package com.thinkgem.jeesite.modules.ftc.constant;

import com.thinkgem.jeesite.common.rest.BaseEnum;

/**
 * Created by houyi on 2017/6/20 0020.
 */
public enum WishlistTypeEnum  {

    WISHLIST_PRODUCT("1", "商品"),
    WISHLIST_SHOP("2", "店铺");
    private String value;

    private  String valueInfo;

    WishlistTypeEnum(String value, String valueInfo) {
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


