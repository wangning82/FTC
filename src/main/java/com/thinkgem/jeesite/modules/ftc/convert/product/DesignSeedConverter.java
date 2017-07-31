package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignSeedDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.PositionDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductImageDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.DesignSeed;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import org.springframework.stereotype.Component;

/**
 * Created by wangqingxiang on 2017/6/21.
 */
@Component
public class DesignSeedConverter extends BaseConverter<DesignSeed,DesignSeedDto> {
    @Override
    public DesignSeedDto convertModelToDto(DesignSeed model) {
        DesignSeedDto dto=new DesignSeedDto();
        dto.setImgUrl(model.getImgUrl());
        dto.setImgNailUrl(model.getImgNailUrl());
        return dto;
    }

    @Override
    public DesignSeed convertDtoToModel(DesignSeedDto dto) {
        DesignSeed model=new DesignSeed();
        model.setImgUrl(dto.getImgUrl());
        model.setImgNailUrl(dto.getImgNailUrl());
        return model;
    }
}
