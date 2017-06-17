package com.thinkgem.jeesite.modules.ftc.dto;

import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;
import com.thinkgem.jeesite.modules.ftc.entity.product.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/16.
 */
public class SpecificationDto {
    private String name;
    private String id;
    private List<SpecAttributeDto> specAttributeList;

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

    public List<SpecAttributeDto> getSpecAttributeList() {
        return specAttributeList;
    }

    public void setSpecAttributeList(List<SpecAttributeDto> specAttributeList) {
        this.specAttributeList = specAttributeList;
    }
    public void convert(Specification specification){
        this.setName(specification.getName());
        this.setId(specification.getId());
        List<SpecAttributeDto> attributeDtos=new ArrayList<>();

        for(SpecAttribute attribute:specification.getSpecAttributeList()){
            SpecAttributeDto specAttributeDto=new SpecAttributeDto();
            specAttributeDto.convert(attribute);
            attributeDtos.add(specAttributeDto);
        }
        this.setSpecAttributeList(attributeDtos);
    }
}
