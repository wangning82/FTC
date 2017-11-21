package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.ShopDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by houyi on 2017/7/8 0008.
 */
@Component
public class ShopConverter extends BaseConverter<Customer, ShopDto> {

    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private ProductConverter productConverter;

    @Override
    public Customer convertDtoToModel(ShopDto dto) {
        Customer customer = customerConverter.convertDtoToModel(dto.getUser());
        customer.setShopName(dto.getName());
        customer.setShopBackground(dto.getBackgroundUrl());
        return customer;
    }

    public ShopDto convertModelToDto(Customer model, List<Product> productList) {
        ShopDto shopDto = new ShopDto();
        shopDto.setId(model.getId());
        shopDto.setName(model.getShopName());
        shopDto.setDesc(model.getSignature());
        shopDto.setBackgroundUrl(model.getShopBackground());
        shopDto.setUser(customerConverter.convertModelToDto(model));
        shopDto.setGoods(productConverter.convertListFromModelToDto(productList));
        return shopDto;
    }
    @Override
    public ShopDto convertModelToDto(Customer model) {
        ShopDto shopDto = new ShopDto();

        shopDto.setId(model.getId());
        shopDto.setName(model.getShopName());
        shopDto.setDesc(model.getSignature());
        shopDto.setBackgroundUrl(model.getShopBackground());
        shopDto.setUser(customerConverter.convertModelToDto(model));
        return shopDto;
    }
}
