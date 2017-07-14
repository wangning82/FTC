package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by bingbing on 2017/6/14.
 */
public class ProductDto extends BaseDto<ProductDto> {
    private String id;//id
    private String did;//设计id
    private String name;//名称
    private String desc;//描述
//    private Double showPrice;//价格
    private String category;//分类id
    private boolean open;//是否开放
    private BigDecimal designPrice;//设计费
    private List<PositionDto> sprites;//位置信息


    ProductSpecDto showAttributeGroup;//默认的规格
    List<ProductSpecDto> othersAttributeGroup;//规格列表


    public List<PositionDto> getSprites() {
        return sprites;
    }

    public void setSprites(List<PositionDto> sprites) {
        this.sprites = sprites;
    }

    private Integer praiseCount;//点赞数量
    private Integer favouriteCount;//收藏数量
    private boolean praised;//点赞数量
    private boolean favourited;//收藏数量
    private String attrDesc;//规格描述

    public String getAttrDesc() {
        return attrDesc;
    }

    public void setAttrDesc(String attrDesc) {
        this.attrDesc = attrDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
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

//    public void setShowPrice(Double showPrice) {
//        this.showPrice = showPrice;
//    }
//
//    public Double getShowPrice() {
//        return showPrice;
//    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public BigDecimal getDesignPrice() {
        return designPrice;
    }

    public void setDesignPrice(BigDecimal designPrice) {
        this.designPrice = designPrice;
    }

    public ProductSpecDto getShowAttributeGroup() {
        return showAttributeGroup;
    }

    public void setShowAttributeGroup(ProductSpecDto showAttibuteGroup) {
        this.showAttributeGroup = showAttibuteGroup;
    }

    public List<ProductSpecDto> getOthersAttributeGroup() {
        return othersAttributeGroup;
    }

    public void setOthersAttributeGroup(List<ProductSpecDto> othersAttributeGroup) {
        this.othersAttributeGroup = othersAttributeGroup;
    }

    public Integer getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(Integer favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public boolean isPraised() {
        return praised;
    }

    public void setPraised(boolean praised) {
        this.praised = praised;
    }

    public boolean isFavourited() {
        return favourited;
    }

    public void setFavourited(boolean favourited) {
        this.favourited = favourited;
    }
}



