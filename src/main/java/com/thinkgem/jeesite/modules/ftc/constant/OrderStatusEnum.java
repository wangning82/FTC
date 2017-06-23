package com.thinkgem.jeesite.modules.ftc.constant;

/**
 * Created by houyi on 2017/6/20 0020.
 */
public enum OrderStatusEnum {

    ORDER_STATUS_FORPAID("1", "待付款"),
    ORDER_STATUS_FORDELIVERYED("2", "待发货"),
    ORDER_STATUS_FORRECEIVED("3", "待收货"),
    ORDER_STATUS_FORCOMMENTED("4", "待评价"),
    ORDER_STATUS_CANCELED("5", "已取消");

    private String value;

    private String valueInfo;

    OrderStatusEnum(String value, String valueInfo){
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
