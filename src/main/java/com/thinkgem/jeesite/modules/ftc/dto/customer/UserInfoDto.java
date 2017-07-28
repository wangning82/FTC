package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.util.List;

/**
 * Created by houyi on 2017/7/12 0012.
 */
public class UserInfoDto extends BaseDto<UserInfoDto> {
    private String token;
    private ShopDto shop;
    private Long designCount;
    private Long wishCount;

    public Long getDesignCount() {
        return designCount;
    }

    public void setDesignCount(Long designCount) {
        this.designCount = designCount;
    }

    public Long getWishCount() {
        return wishCount;
    }

    public void setWishCount(Long wishCount) {
        this.wishCount = wishCount;
    }

    private List<PlatformDto> platforms;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ShopDto getShop() {
        return shop;
    }

    public void setShop(ShopDto shop) {
        this.shop = shop;
    }

    public List<PlatformDto> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformDto> platforms) {
        this.platforms = platforms;
    }
}
