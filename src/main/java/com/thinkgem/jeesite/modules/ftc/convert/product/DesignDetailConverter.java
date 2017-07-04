package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignDetailDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.DesignDetail;
import org.springframework.stereotype.Component;

/**
 * Created by bingbing on 2017/7/4.
 */
@Component
public class DesignDetailConverter extends BaseConverter<DesignDetail,DesignDetailDto>{
    @Override
    public DesignDetailDto convertModelToDto(DesignDetail model) {
        DesignDetailDto dto=new DesignDetailDto();
        if(model==null)return dto;
        dto.setId(model.getId());
        dto.setRotation(model.getRotation());
        dto.setScale(model.getScale());
        dto.setTexture(model.getPicImg());
        dto.setX(model.getX());
        dto.setY(model.getY());
        return dto;
    }
}
