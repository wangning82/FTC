package com.thinkgem.jeesite.modules.ftc.dto.product;

/**
 * Created by bingbing on 2017/6/16.
 */
public class MeshDto {
    private String name;
    private ImageDto texture;
    private Double x;
    private Double y;
    private Double rotation;
    private Double scale;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageDto getTexture() {
        return texture;
    }

    public void setTexture(ImageDto texture) {
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

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

}
