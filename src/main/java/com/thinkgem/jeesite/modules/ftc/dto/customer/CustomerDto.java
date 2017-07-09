package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;

/**
 * Created by bingbing on 2017/7/3.
 */
public class CustomerDto extends BaseDto<CustomerDto> {
    private String id;//
    private String name;//名字
    private String desc;//描述
    private String imgUrl;//头像
    private Integer followingCount;//关注
    private Integer followerCount;//粉丝
    private Integer designCount;//设计数量
    private Integer favouriteCount;//收藏数量

    private String accessToken;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getDesignCount() {
        return designCount;
    }

    public void setDesignCount(Integer designCount) {
        this.designCount = designCount;
    }

    public Integer getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(Integer favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
