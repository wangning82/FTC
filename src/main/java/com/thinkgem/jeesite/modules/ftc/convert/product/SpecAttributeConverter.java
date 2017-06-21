package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.SpecAttributeDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;

/**
 * Created by wangqingxiang on 2017/6/17.
 */
public class SpecAttributeConverter extends BaseConverter<SpecAttribute,SpecAttributeDto>{

    @Override
    public SpecAttribute convertDtoToModel(SpecAttributeDto dto) {
        return super.convertDtoToModel(dto);
    }

    @Override
    public SpecAttributeDto convertModelToDto(SpecAttribute model) {
        SpecAttributeDto dto=new SpecAttributeDto();
        dto.setName(model.getName());
        dto.setId(model.getId());
        return super.convertModelToDto(model);
    }
}
