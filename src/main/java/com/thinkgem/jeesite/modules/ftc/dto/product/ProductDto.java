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
    private Double price;//价格
    private String category;//分类id
    private boolean open;//是否开放
    private BigDecimal designPrice;//设计费

    ProductSpecDto showAttibuteGroup;//默认的规格
    List<ProductSpecDto> othersAttributeGroup;//规格列表


    private Integer priaseCount;//点赞数量
    private Integer favouritCount;//收藏数量
    private boolean priased;//点赞数量
    private boolean favourited;//收藏数量

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


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

    public ProductSpecDto getShowAttibuteGroup() {
        return showAttibuteGroup;
    }

    public void setShowAttibuteGroup(ProductSpecDto showAttibuteGroup) {
        this.showAttibuteGroup = showAttibuteGroup;
    }

    public List<ProductSpecDto> getOthersAttributeGroup() {
        return othersAttributeGroup;
    }

    public void setOthersAttributeGroup(List<ProductSpecDto> othersAttributeGroup) {
        this.othersAttributeGroup = othersAttributeGroup;
    }

    public Integer getPriaseCount() {
        return priaseCount;
    }

    public void setPriaseCount(Integer priaseCount) {
        this.priaseCount = priaseCount;
    }

    public Integer getFavouritCount() {
        return favouritCount;
    }

    public void setFavouritCount(Integer favouritCount) {
        this.favouritCount = favouritCount;
    }

    public boolean isPriased() {
        return priased;
    }

    public void setPriased(boolean priased) {
        this.priased = priased;
    }

    public boolean isFavourited() {
        return favourited;
    }

    public void setFavourited(boolean favourited) {
        this.favourited = favourited;
    }
}



