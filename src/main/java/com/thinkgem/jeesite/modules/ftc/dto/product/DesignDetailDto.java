package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;

/**
 * Created by bingbing on 2017/7/4.
 */
public class DesignDetailDto extends BaseDto<DesignDetailDto> {
    private String id;
    private ProductImageDto texture;
    private Double x;
    private Double y;
    private Double rotation;
    private Double scale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductImageDto getTexture() {
        return texture;
    }

    public void setTexture(ProductImageDto texture) {
        this.texture = texture;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getRotation() {
        return rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }
}
