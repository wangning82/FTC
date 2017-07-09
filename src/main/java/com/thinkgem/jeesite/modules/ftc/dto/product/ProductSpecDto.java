package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;

/**
 * Created by wangqignxiang on 2017/6/17.
 */
public class ProductSpecDto extends BaseDto<ProductSpecDto>{
    private String id;
    private String value;

    public ProductSpecDto(){

    }
    public ProductSpecDto(String id,String value){
        this.id=id;
        this.value=value;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
