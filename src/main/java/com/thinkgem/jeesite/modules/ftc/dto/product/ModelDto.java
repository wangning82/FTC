package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.util.List;

/**
 * Created by bingbing on 2017/6/19.
 */
public class ModelDto extends BaseDto<ModelDto> {
    private String id;

    private String name;//名称
    private String desc;//描述

    private String category;

    ProductSpecDto showAttributeGroup;//默认的规格
    List<ProductSpecDto> othersAttributeGroup;//规格列表
    private List<PositionDto> sprites;//位置信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductSpecDto getShowAttributeGroup() {
        return showAttributeGroup;
    }

    public void setShowAttributeGroup(ProductSpecDto showAttributeGroup) {
        this.showAttributeGroup = showAttributeGroup;
    }

    public List<ProductSpecDto> getOthersAttributeGroup() {
        return othersAttributeGroup;
    }

    public void setOthersAttributeGroup(List<ProductSpecDto> othersAttributeGroup) {
        this.othersAttributeGroup = othersAttributeGroup;
    }

    public List<PositionDto> getSprites() {
        return sprites;
    }

    public void setSprites(List<PositionDto> sprites) {
        this.sprites = sprites;
    }
}
