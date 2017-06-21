package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Image;

/**
 * Created by bingbing on 2017/6/16.
 */
public class ImageDto extends BaseDto<ImageDto> {
    private String id;
    private String imgUrl;
    private MeshDto meshDto=new MeshDto();

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

    public MeshDto getMeshDto() {
        return meshDto;
    }

    public void setMeshDto(MeshDto meshDto) {
        this.meshDto = meshDto;
    }

}
