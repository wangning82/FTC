package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignDetailDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import com.thinkgem.jeesite.modules.ftc.entity.product.DesignDetail;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/21.
 */
@Component
public class DesignConverter extends BaseConverter<Design,DesignDto> {

    @Autowired
    private DesignDetailConverter designDetailConverter;
    @Autowired
    private ModelConverter modelConverter;
    @Override
    public Design convertDtoToModel(DesignDto dto) {
        Design model=new Design();
//        model.setName(dto.getName());
        List<DesignDetail> details=designDetailConverter.
                convertListFromDtoToModel(dto.getMeshes());
        model.setDetails(details);
        model.setPicImg(dto.getImgUrl());
        model.setModel(new Product(dto.getModel().getId()));
        return model;
    }

    @Override
    public DesignDto convertModelToDto(Design model) {
        DesignDto dto=new DesignDto();
        if(model==null)return dto;
        dto.setId(model.getId());
        dto.setImgUrl(model.getPicImg());
//        dto.setName(model.getName());
        dto.setMeshes(designDetailConverter.convertListFromModelToDto(model.getDetails()));
        dto.setModel(modelConverter.convertModelToDto(model.getModel()));
        return dto;
    }
}
