package com.thinkgem.jeesite.modules.ftc.dto.order;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.AddressDto;

import java.util.List;

/**
 * Created by houyi on 2017/7/8 0008.
 */
public class OrderDto extends BaseDto<OrderDto> {
    private String id;
    private String order; // 订单号
    private String type; // 订单状态
    private List<ShoppingCartDto> bags;
    private AddressDto address;
    private String receipt; // 发票信息
    private String addition; // 备注信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ShoppingCartDto> getBags() {
        return bags;
    }

    public void setBags(List<ShoppingCartDto> bags) {
        this.bags = bags;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
