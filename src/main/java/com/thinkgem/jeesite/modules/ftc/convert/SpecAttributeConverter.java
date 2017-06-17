package com.thinkgem.jeesite.modules.ftc.convert;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.SpecAttributeDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.SpecificationDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;

/**
 * Created by bingbing on 2017/6/17.
 */
public class SpecAttributeConverter extends BaseConverter<SpecAttribute,SpecAttributeDto>{

    @Override
    public SpecAttribute convertDtoToModel(SpecAttributeDto dto) {
        return super.convertDtoToModel(dto);
    }

    @Override
    public SpecAttributeDto convertModelToDto(SpecAttribute model) {
        return super.convertModelToDto(model);
    }
}
