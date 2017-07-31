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
//        sprite.setId(model.getPosition().getId());
        sprite.setX(model.getPosition().getFromX());
        sprite.setW(model.getPosition().getWidth());
        sprite.setH(model.getPosition().getHeight());
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
        model.setImgUrl(dto.getImgUrl());
        model.setImgNailUrl(dto.getImgNailUrl());
        Position position=new Position();
        position.setFromX(dto.getSprite().getX());
        position.setFromY(dto.getSprite().getY());
        position.setHeight(dto.getSprite().getH());
        position.setWidth(dto.getSprite().getW());
        position.setScale(dto.getSprite().getScale());
        position.setRotation(dto.getSprite().getRotation());
        position.setCode(dto.getId());
        position.setDelFlag("0");
        model.setPosition(position);
        return model;
    }
}
