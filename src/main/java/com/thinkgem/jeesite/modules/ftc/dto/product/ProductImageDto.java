package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;

/**
 * Created by bingbing on 2017/6/16.
 */
public class ProductImageDto extends BaseDto<ProductImageDto> {


    private String name;//位置名称
    private String id;//位置编号
    private String imgUrl;//原图地址
    private String imgNailUrl;//小图地址

    private PositionDto sprite;//位置信息

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionDto getSprite() {
        return sprite;
    }

    public void setSprite(PositionDto sprite) {
        this.sprite = sprite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getImgUrl() {
        return imgUrl;
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgNailUrl() {
        return imgNailUrl;
    }

    public void setImgNailUrl(String imgNailUrl) {
        this.imgNailUrl = imgNailUrl;
    }
}
