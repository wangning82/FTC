package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Image;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingbing on 2017/6/14.
 */
public class ProductDto extends BaseDto<ProductDto>{
    private String  id;//id
    private String did;
    private String name;//名称
    private String desc;//描述
    private Double price;//价格
    private int  count;//库存

    private boolean open;//是否开放
    private BigDecimal designPrice;
    private CustomerDto designBy;
    private String picImg;//产品主图

    List<ImageDto> textures;//图片
    List<ProductSpecDto> attrs;//规格

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }

    public CustomerDto getDesignBy() {
        return designBy;
    }

    public void setDesignBy(CustomerDto designBy) {
        this.designBy = designBy;
    }

    public BigDecimal getDesignPrice() {
        return designPrice;
    }

    public void setDesignPrice(BigDecimal designPrice) {
        this.designPrice = designPrice;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<ImageDto> getTextures() {
        return textures;
    }

    public void setTextures(List<ImageDto> textures) {
        this.textures = textures;
    }

    public List<ProductSpecDto> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<ProductSpecDto> attrs) {
        this.attrs = attrs;
    }
}
