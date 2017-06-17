package com.thinkgem.jeesite.modules.ftc.dto;

import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;
import com.thinkgem.jeesite.modules.ftc.entity.product.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/17.
 */
public class SpecAttributeDto {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void convert(SpecAttribute attribute){
        this.setId(attribute.getId());
        this.setName(attribute.getName());
    }
}
