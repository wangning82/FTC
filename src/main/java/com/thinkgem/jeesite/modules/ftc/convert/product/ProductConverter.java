package com.thinkgem.jeesite.modules.ftc.convert.product;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.convert.customer.CustomerConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductImageDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductSpecDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.Category;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bingbing on 2017/6/17.
 */
@Component
public class ProductConverter extends BaseConverter<Product, ProductDto> {
    @Autowired
    private ProductSpecConverter productSpecConverter;
    @Autowired
    private ProductImageConverter productImageConverter;
    @Autowired
    private CustomerConverter customerConverter;
    @Override
    public Product convertDtoToModel(ProductDto dto) {
        Product model = new Product();
        model.setId(dto.getId());
        model.setCategory(new Category(dto.getCategory()));
        model.setName(dto.getName());
        model.setSpecs(productSpecConverter.convertListFromDtoToModel(dto.getOthersAttributeGroup()));
        model.setIntroduce(dto.getDesc());
        return model;
    }

    @Override
    public ProductDto convertModelToDto(Product model) {
        ProductDto dto = new ProductDto();
        if(model==null)return dto;
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDesc(model.getIntroduce());
        dto.setOpen(false);


//        dto.setShowPrice(model.getShowPrice());
        dto.setDesignPrice(model.getDesignPrice());
        dto.setCategory(model.getCategory()==null?null:model.getCategory().getId());
        dto.setPraiseCount(model.getPriaseCount());
        dto.setFavouriteCount(model.getFavouriteCount());
        List<ProductSpecDto> attrs=
                productSpecConverter.convertListFromModelToDto(model.getSpecs());
        dto.setOthersAttributeGroup(attrs);
        if(attrs!=null&&attrs.size()>0){
            for(int i=0;i<attrs.size();i++){
                if(attrs.get(i).isDefaultFlag()){
                    dto.setShowAttributeGroup(attrs.get(i));
                }
            }

        }
        return dto;
    }


}
