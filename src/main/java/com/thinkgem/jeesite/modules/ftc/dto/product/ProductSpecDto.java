package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;

import java.util.List;

/**
 * Created by wangqignxiang on 2017/6/17.
 */
public class ProductSpecDto extends BaseDto<ProductSpecDto>{
    private String id;
    private String code;
    private String desc;
    private boolean defaultFlag;
    private Double price;
    private List<ProductImageDto> textures;//图片
    private List<PositionDto> sprites;//位置  只是model用
    private List<SpecAttributeDto> attrs;


    public ProductSpecDto(){

    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDefaultFlag(boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public boolean isDefaultFlag() {
        return defaultFlag;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<ProductImageDto> getTextures() {
        return textures;
    }

    public void setTextures(List<ProductImageDto> textures) {
        this.textures = textures;
    }

    public List<PositionDto> getSprites() {
        return sprites;
    }

    public void setSprites(List<PositionDto> sprites) {
        this.sprites = sprites;
    }

    public List<SpecAttributeDto> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<SpecAttributeDto> attrs) {
        this.attrs = attrs;
    }
}
