package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.ShopDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by houyi on 2017/7/8 0008.
 */
@Component
public class ShopConverter extends BaseConverter<Customer, ShopDto> {

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public Customer convertDtoToModel(ShopDto dto) {
        Customer customer = customerConverter.convertDtoToModel(dto.getUser());
        customer.setShopName(dto.getName());
        return customer;
    }

    @Override
    public ShopDto convertModelToDto(Customer model) {
        ShopDto shopDto = new ShopDto();
        shopDto.setId(model.getId());
        shopDto.setName(model.getShopName());
        shopDto.setDesc(model.getSignature());
        shopDto.setUser(customerConverter.convertModelToDto(model));
        return shopDto;
    }
}
