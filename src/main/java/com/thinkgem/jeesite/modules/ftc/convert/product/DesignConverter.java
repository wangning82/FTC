package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangqingxiang on 2017/6/21.
 */
@Component
public class DesignConverter extends BaseConverter<Design,DesignDto> {
    @Autowired
    private ModelConverter modelConverter;
    @Autowired
    private DesignDetailConverter designDetailConverter;
    @Override
    public Design convertDtoToModel(DesignDto dto) {
        Design model=new Design();
        model.setName(dto.getName());
        model.setPrice(dto.getPrice());
        model.setCode(dto.getNumber());
        Customer customer=model.getCustomer();

        return model;
    }

    @Override
    public DesignDto convertModelToDto(Design model) {
        DesignDto dto=new DesignDto();
        if(model==null)return dto;
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setUser(model.getCustomer());
        dto.setMeshes(designDetailConverter.convertListFromModelToDto(model.getDetails()));
        dto.setPrice(model.getPrice());
        dto.setNumber(model.getCode());
        return dto;
    }
}
