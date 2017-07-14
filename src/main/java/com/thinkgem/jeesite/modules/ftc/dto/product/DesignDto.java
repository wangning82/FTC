package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.DesignDetail;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;

import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/14.
 */
public class DesignDto extends BaseDto<DesignDto>{
    private String id;
    private ProductDto model;
    private List<DesignDetailDto> meshes;
    private String name;
    private String number;
    private Double price;
    private String status;
    private Customer user;
    private List<ProductImageDto> seeds;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setSeeds(List<ProductImageDto> seeds) {
        this.seeds = seeds;
    }

    public List<ProductImageDto> getSeeds() {
        return seeds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public DesignDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductDto getModel() {
        return model;
    }

    public void setModel(ProductDto model) {
        this.model = model;
    }

    public List<DesignDetailDto> getMeshes() {
        return meshes;
    }

    public void setMeshes(List<DesignDetailDto> meshes) {
        this.meshes = meshes;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

   }

