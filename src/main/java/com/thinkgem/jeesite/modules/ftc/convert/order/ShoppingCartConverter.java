package com.thinkgem.jeesite.modules.ftc.convert.order;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.order.ShoppingCartDto;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by houyi on 2017/7/6 0006.
 */
@Component
public class ShoppingCartConverter extends BaseConverter<ShoppingCart, ShoppingCartDto> {

    @Autowired
    private ProductConverter productConverter;

    @Override
    public ShoppingCart convertDtoToModel(ShoppingCartDto dto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(dto.getId());
        shoppingCart.setBuyNumber(dto.getBuyNumber());
        shoppingCart.setCheckStatus(dto.getSelected());
        shoppingCart.setProduct(productConverter.convertDtoToModel(dto.getProductDto()));
        return shoppingCart;
    }

    @Override
    public ShoppingCartDto convertModelToDto(ShoppingCart model) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(model.getId());
        shoppingCartDto.setBuyNumber(model.getBuyNumber());
        shoppingCartDto.setSelected(model.getCheckStatus());
        shoppingCartDto.setProductDto(productConverter.convertModelToDto(model.getProduct()));
        return shoppingCartDto;
    }
}
