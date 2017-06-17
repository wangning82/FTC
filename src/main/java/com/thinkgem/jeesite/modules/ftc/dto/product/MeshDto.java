package com.thinkgem.jeesite.modules.ftc.dto.product;

/**
 * Created by bingbing on 2017/6/16.
 */
public class MeshDto {
    private String name;
    private ImageDto texture;
    private double x;
    private double y;
    private double rotation;
    private double scale;

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

}
