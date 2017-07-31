/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设计用到的素材列表
 *
 * @author wangqingxiang
 * @version 2017-06-01
 */
public class DesignSeed extends DataEntity<DesignSeed> {

    private static final long serialVersionUID = 1L;
    private String imgUrl;//大图地址
    private String imgNailUrl;//小图地址
    private Design design;//设计

    public String getImgNailUrl() {
        return imgNailUrl;
    }

    public void setImgNailUrl(String imgNailUrl) {
        this.imgNailUrl = imgNailUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }
}