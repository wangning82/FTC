package com.thinkgem.jeesite.modules.ftc.dto;

import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;

/**
 * Created by wangqignxiang on 2017/6/17.
 */
public class ProductSpecDto {
    private String id;
    private String name;
    private String value;
    private Double stock;
    private Double price;        // 价格
    private Double score;
    private String imageUrl;
    private String number;


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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void convert(ProductSpec productSpec){
        this.setId(productSpec.getId());
        this.setName(productSpec.getSpec().getName());
        this.setValue(productSpec.getSpec().getId());
        this.setImageUrl(productSpec.getPicImg());
        this.setPrice(productSpec.getPrice());
        this.setStock(productSpec.getStock());
        this.setScore(productSpec.getScore());
        this.setNumber(productSpec.getProductSpecNumber());
    }
}
