package com.thinkgem.jeesite.modules.ftc.dto;

import com.thinkgem.jeesite.modules.ftc.entity.advert.AdvertDetail;

/**
 * Created by bingbing on 2017/6/16.
 */
public class AdvertDetailDto {
    private String gid;
    private String imageUrl;
    private String linkUrl;
    private String title;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
