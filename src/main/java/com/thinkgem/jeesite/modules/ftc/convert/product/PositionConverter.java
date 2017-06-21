package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.PositionDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductSpecDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by bingbing on 2017/6/19.
 */
@Component
public class PositionConverter extends BaseConverter<Position, PositionDto> {
    @Override
    public PositionDto convertModelToDto(Position model) {
        PositionDto dto=new PositionDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setFromX(model.getFromX());
        dto.setFromY(model.getFromY());
        dto.setHeight(model.getHeight());
        dto.setRotation(model.getRotation());
        dto.setScale(model.getScale());
        return dto;
    }
}
