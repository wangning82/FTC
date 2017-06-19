package com.thinkgem.jeesite.modules.ftc.convert;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.springframework.stereotype.Component;

/**
 * Created by bingbing on 2017/6/17.
 */
@Component
public class ProductConverter extends BaseConverter<Product, ProductDto> {
    @Override
    public Product convertDtoToModel(ProductDto dto) {
        Product model = new Product();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public ProductDto convertModelToDto(Product model) {
        ProductDto dto = new ProductDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDesc(model.getIntroduce());
        return dto;
    }

}
