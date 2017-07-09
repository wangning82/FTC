package com.thinkgem.jeesite.modules.ftc.dto.order;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.AddressDto;

import java.util.List;

/**
 * Created by houyi on 2017/7/8 0008.
 */
public class OrderDto extends BaseDto<OrderDto> {
    private String id;
    private String type;
    private List<ShoppingCartDto> bags;
    private AddressDto address;

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
}
