package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;

/**
 * Created by wangqingxiang on 2017/6/17.
 */
public class SpecAttributeDto extends BaseDto<SpecAttributeDto>{
    private String value;
    private String id;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void convert(SpecAttribute attribute){
        this.setId(attribute.getId());
        this.setValue(attribute.getName());
    }
}
