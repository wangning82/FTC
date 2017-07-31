package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.DesignDetail;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;

import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/14.
 */
public class DesignDto extends BaseDto<DesignDto>{
    private String id;
    private ModelDto model;
    private List<DesignDetailDto> meshes;
    private String name;
    private List<DesignSeedDto> seeds;
    private String imgUrl;



    private CustomerDto user;

    public CustomerDto getUser() {
        return user;
    }

    public void setUser(CustomerDto user) {
        this.user = user;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setSeeds(List<DesignSeedDto> seeds) {
        this.seeds = seeds;
    }

    public List<DesignSeedDto> getSeeds() {
        return seeds;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public DesignDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public List<DesignDetailDto> getMeshes() {
        return meshes;
    }

    public void setMeshes(List<DesignDetailDto> meshes) {
        this.meshes = meshes;
    }



   }

