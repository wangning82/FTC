package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import com.thinkgem.jeesite.modules.ftc.entity.product.Image;
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
    private ModelConverter modelConverter;
    @Autowired
    private ImageConverter imageConverter;
    @Override
    public Design convertDtoToModel(DesignDto dto) {
        Design model=new Design();
        model.setName(dto.getName());
        model.setPrice(dto.getPrice());
        model.setCode(dto.getNumber());
        Product product =modelConverter.convertDtoToModel(dto.getModelDto());
        model.setModel(product);
        List<Image> images=imageConverter.convertListFromDtoToModel(dto.getImageDtoList());
        model.setImages(images);

        Customer customer=model.getCustomer();

        return model;
    }
}
