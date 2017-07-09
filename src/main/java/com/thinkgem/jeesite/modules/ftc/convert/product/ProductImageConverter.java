package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductImageDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import org.springframework.stereotype.Component;

/**
 * Created by wangqingxiang on 2017/6/21.
 */
@Component
public class ProductImageConverter extends BaseConverter<ProductImage,ProductImageDto> {
    @Override
    public ProductImageDto convertModelToDto(ProductImage model) {
        ProductImageDto dto=new ProductImageDto();
        dto.setId(model.getId());
        dto.setImgUrl(model.getUrl());

        return dto;
    }

    @Override
    public ProductImage convertDtoToModel(ProductImageDto dto) {
        ProductImage image=new ProductImage();
        image.setUrl(dto.getImgUrl());

        return image;
    }
}
