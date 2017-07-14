package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductImageDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductSpecDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Category;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by bingbing on 2017/6/19.
 */
@Component
public class ModelConverter extends BaseConverter<Product, ModelDto> {
    @Autowired
    private ProductSpecConverter productSpecConverter;
    @Autowired
    private ProductImageConverter productImageConverter;
    @Override
    public ModelDto convertModelToDto(Product model) {
        ModelDto dto = new ModelDto();
        if(model==null)return dto;
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDesc(model.getIntroduce());
        dto.setOpen(false);
        return dto;
    }

    @Override
    public Product convertDtoToModel(ModelDto dto) {
        Product model=new Product();
        model.setId(dto.getId());
        model.setCategory(new Category(dto.getCategoryId()));
        List<ProductSpec> productSpecList=productSpecConverter.convertListFromDtoToModel(dto.getAttrs());
        model.setSpecs(productSpecList);
        return model;
    }
}
