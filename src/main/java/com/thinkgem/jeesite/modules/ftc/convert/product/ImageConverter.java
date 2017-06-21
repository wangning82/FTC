package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ImageDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Image;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import org.springframework.stereotype.Component;

/**
 * Created by wangqingxiang on 2017/6/21.
 */
@Component
public class ImageConverter extends BaseConverter<Image,ImageDto> {
    @Override
    public ImageDto convertModelToDto(Image model) {
        ImageDto dto=new ImageDto();
        dto.setId(model.getId());
        dto.setImgUrl(model.getUrl());
        dto.getMeshDto().setRotation(model.getRotation());
        dto.getMeshDto().setScale(model.getScale());
        dto.getMeshDto().setX(model.getX());
        dto.getMeshDto().setY(model.getY());
        dto.getMeshDto().setName(model.getPosition().getName());
        return dto;
    }

    @Override
    public Image convertDtoToModel(ImageDto dto) {
        Image image=new Image();
        image.setUrl(dto.getImgUrl());
        image.setRotation(dto.getMeshDto().getRotation());
        image.setPosition(new Position(dto.getName()));
        image.setX(dto.getMeshDto().getX());
        image.setY(dto.getMeshDto().getY());
        image.setScale(dto.getMeshDto().getScale());
        return image;
    }
}
