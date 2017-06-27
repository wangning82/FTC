package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ImageDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductSpecDto;
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
    private ImageConverter imageConverter;
    @Override
    public ModelDto convertModelToDto(Product model) {
        ModelDto d = new ModelDto();
        d.setId(model.getId());
        d.setDesc(model.getIntroduce());
        d.setName(model.getName());
        d.setOpen(true);
        d.setPrice(model.getShowPrice());
        d.setShowImg(model.getPicImg());
        List<ImageDto> textures=imageConverter.convertListFromModelToDto(model.getImages());
        d.setTextures(textures);

        List<ProductSpecDto> specDtos=
                productSpecConverter.convertListFromModelToDto(model.getSpecs());
        d.setAttrs(specDtos);
        return d;
    }

    @Override
    public Product convertDtoToModel(ModelDto dto) {
        Product model=new Product();
        model.setId(dto.getId());
        List<ProductSpec> productSpecList=productSpecConverter.convertListFromDtoToModel(dto.getAttrs());
        model.setSpecs(productSpecList);
        return model;
    }
}
