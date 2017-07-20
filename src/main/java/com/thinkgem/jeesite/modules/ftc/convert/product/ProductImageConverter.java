package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.PositionDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductImageDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import org.springframework.stereotype.Component;

/**
 * Created by wangqingxiang on 2017/6/21.
 */
@Component
public class ProductImageConverter extends BaseConverter<ProductImage,ProductImageDto> {
    public static String BASE_PATH="";
    @Override
    public ProductImageDto convertModelToDto(ProductImage model) {
        ProductImageDto dto=new ProductImageDto();
        dto.setId(model.getPosition().getCode());
        PositionDto sprite=new PositionDto();
//        sprite.setName(model.getPosition().getName());
        sprite.setFromX(model.getPosition().getFromX());
        sprite.setWidth(model.getPosition().getWidth());
        sprite.setHeight(model.getPosition().getHeight());
        sprite.setRotation(model.getPosition().getRotation());
        sprite.setScale(model.getPosition().getScale());
        dto.setSprite(sprite);
        dto.setImgUrl(model.getImgUrl()==null?"":BASE_PATH+model.getImgUrl());
        dto.setImgNailUrl(model.getImgNailUrl()==null?"":(BASE_PATH+model.getImgNailUrl()));
        return dto;
    }

    @Override
    public ProductImage convertDtoToModel(ProductImageDto dto) {
        ProductImage model=new ProductImage();
        return model;
    }
}
