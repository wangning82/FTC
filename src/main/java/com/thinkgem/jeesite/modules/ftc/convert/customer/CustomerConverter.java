package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import org.springframework.stereotype.Component;

/**
 * Created by bingbing on 2017/7/3.
 */
@Component
public class CustomerConverter extends BaseConverter<Customer, CustomerDto> {
    @Override
    public CustomerDto convertModelToDto(Customer model) {
        CustomerDto dto = new CustomerDto();
        if (model == null)
            return dto;
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

    @Override
    public Customer convertDtoToModel(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setUserName(dto.getName());
        customer.setSignature(dto.getDesc());
        customer.setPicImg(dto.getImgUrl());
        return customer;
    }
}
