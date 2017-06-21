package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.util.List;

/**
 * Created by bingbing on 2017/6/19.
 */
public class ModelDto extends BaseDto<ModelDto> {
    private String id;
    private String did;
    private String name;//名称
    private String desc;//描述
    private double  price;//价格
    private int  count;//库存
    private boolean open;
    private String showImg;

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    List<ImageDto> textures;//图片
    private List<PositionDto> positionList;
    private List<ProductSpecDto>  specList;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public List<ProductSpecDto> getSpecList() {
        return specList;
    }

    public void setSpecList(List<ProductSpecDto> specList) {
        this.specList = specList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PositionDto> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<PositionDto> positionList) {
        this.positionList = positionList;
    }

}
