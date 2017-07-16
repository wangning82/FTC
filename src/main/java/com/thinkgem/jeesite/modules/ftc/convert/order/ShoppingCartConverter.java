package com.thinkgem.jeesite.modules.ftc.convert.order;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.constant.FlagEnum;
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
        if(dto.isSelected()){
            shoppingCart.setCheckStatus(FlagEnum.Flag_YES.getValue());
        }else {
            shoppingCart.setCheckStatus(FlagEnum.Flag_NO.getValue());
        }
        shoppingCart.setProduct(productConverter.convertDtoToModel(dto.getGood()));
        shoppingCart.setProductSpec(productSpecConverter.convertDtoToModel(dto.getGood().getShowAttributeGroup()));
        return shoppingCart;
    }

    @Override
    public ShoppingCartDto convertModelToDto(ShoppingCart model) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(model.getId());
        shoppingCartDto.setCount(model.getBuyNumber());
        if(FlagEnum.Flag_YES.getValue().equals(model.getCheckStatus())){
            shoppingCartDto.setSelected(true);
        }else if(FlagEnum.Flag_NO.getValue().equals(model.getCheckStatus())){
            shoppingCartDto.setSelected(false);
        }
        ProductDto productDto = productConverter.convertModelToDto(model.getProduct());
        productDto.setShowAttributeGroup(productSpecConverter.convertModelToDto(model.getProductSpec()));

        shoppingCartDto.setGood(productDto);
        return shoppingCartDto;
    }
}
