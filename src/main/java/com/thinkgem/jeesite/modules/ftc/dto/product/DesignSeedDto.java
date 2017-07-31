package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;

/**
 * Created by bingbing on 2017/6/16.
 */
public class DesignSeedDto extends BaseDto<DesignSeedDto> {


    private String id;//位置编号
    private String imgUrl;//原图地址
    private String imgNailUrl;//小图地址

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
