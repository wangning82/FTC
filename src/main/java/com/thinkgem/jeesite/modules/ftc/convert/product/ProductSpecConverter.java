package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductImageDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductSpecDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.SpecAttributeDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/20.
 */
@Component
public class ProductSpecConverter extends BaseConverter<ProductSpec, ProductSpecDto> {
    @Autowired
    private ProductImageConverter productImageConverter;

    @Override
    public ProductSpecDto convertModelToDto(ProductSpec model) {

        ProductSpecDto dto = new ProductSpecDto();
        if (model == null) return dto;
        dto.setId(model.getId());
        dto.setCode(model.getProductSpecNumber());
        dto.setDefaultFlag(false);
        dto.setPrice(model.getPrice());
        SpecAttribute attribute = model.getSpec();
        if(attribute != null && StringUtils.isNotEmpty(attribute.getName())){
            String[] attrArray = attribute.getName().split(",");
            List<SpecAttributeDto> attrs = new ArrayList<>();
            for (String a : attrArray) {
                SpecAttributeDto attr = new SpecAttributeDto();
                String[] s = a.split(":");
                attr.setId(s[0]);
                attr.setValue(s[1]);
                attrs.add(attr);
            }
            dto.setAttrs(attrs);
        }
        List<ProductImageDto> images = productImageConverter.convertListFromModelToDto(model.getImages());
        dto.setTextures(images);
        dto.setDefaultFlag("1".equals(model.getDefaultStatus()));
        return dto;
    }

    @Override
    public ProductSpec convertDtoToModel(ProductSpecDto dto) {
        ProductSpec model = new ProductSpec();
        model.setId(dto.getId());
        model.setProductSpecNumber(dto.getCode());
        model.setPrice(dto.getPrice());
        String spec="";
        for(SpecAttributeDto a:dto.getAttrs()){
            spec+=","+a.getId()+":"+a.getValue();
        }
        model.setSpec(new SpecAttribute(null,spec));
        List<ProductImage> images=productImageConverter.
                convertListFromDtoToModel(dto.getTextures());
        model.setImages(images);
        return model;
    }
}
