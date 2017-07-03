package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;

/**
 * Created by bingbing on 2017/7/3.
 */
public class CustomerConverter extends BaseConverter<Customer,CustomerDto>{
    @Override
    public CustomerDto convertModelToDto(Customer model) {
        CustomerDto dto=new CustomerDto();
        dto.setId(model.getId());
        dto.setName(model.getUserName());
        dto.setDesc(model.getSignature());
        dto.setFollowerCount(100);
        dto.setFollowingCount(100);
        dto.setFavouriteCount(100);
        dto.setDesignCount(100);
        dto.setImgUrl(model.getPicImg());
        return dto;
    }
}
