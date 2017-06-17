package com.thinkgem.jeesite.modules.ftc.convert;

import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingbing on 2017/6/17.
 */
@Component
public class ProductConvert {
    public ProductDto convert(Product product){
        ProductDto productDto=new ProductDto();
        productDto.convert(product);
        return productDto;
    }
    public List<ProductDto> convertList(List<Product> productList){
        List<ProductDto> productDtoList=new ArrayList<>();
        for(Product product:productList){
            productDtoList.add( convert(product));
        }
        return productDtoList;
    }
}
