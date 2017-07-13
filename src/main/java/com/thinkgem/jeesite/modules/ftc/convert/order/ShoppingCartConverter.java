package com.thinkgem.jeesite.modules.ftc.convert.order;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductSpecConverter;
import com.thinkgem.jeesite.modules.ftc.dto.order.ShoppingCartDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
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

    @Autowired
    private ProductSpecConverter productSpecConverter;

    @Override
    public ShoppingCart convertDtoToModel(ShoppingCartDto dto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(dto.getId());
        shoppingCart.setBuyNumber(dto.getCount());
        shoppingCart.setCheckStatus(dto.getSelected());
        shoppingCart.setProduct(productConverter.convertDtoToModel(dto.getGood()));
        shoppingCart.setProductSpec(productSpecConverter.convertDtoToModel(dto.getGood().getShowAttibuteGroup()));

        return shoppingCart;
    }

    @Override
    public ShoppingCartDto convertModelToDto(ShoppingCart model) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(model.getId());
        shoppingCartDto.setCount(model.getBuyNumber());
        shoppingCartDto.setSelected(model.getCheckStatus());

        ProductDto productDto = productConverter.convertModelToDto(model.getProduct());
        productDto.setShowAttibuteGroup(productSpecConverter.convertModelToDto(model.getProductSpec()));

        shoppingCartDto.setGood(productDto);
        return shoppingCartDto;
    }
}
