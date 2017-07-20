package com.thinkgem.jeesite.modules.ftc.dto.order;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.math.BigDecimal;

/**
 * Created by houyi on 2017/7/20 0020.
 */
public class OrderConfirmDto extends BaseDto<OrderDto> {
    private String orderId;
    private BigDecimal payPrice;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }
}
