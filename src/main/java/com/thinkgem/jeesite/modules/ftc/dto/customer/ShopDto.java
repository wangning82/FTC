package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;

import java.util.List;

/**
 * Created by houyi on 2017/7/8 0008.
 */
public class ShopDto extends BaseDto<ShopDto> {
    private String id;
    private String name;
    private String desc;
    private String backgroundUrl;
    private CustomerDto user;
    private List<ProductDto> goods;
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

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

    public CustomerDto getUser() {
        return user;
    }

    public void setUser(CustomerDto user) {
        this.user = user;
    }

    public List<ProductDto> getGoods() {
        return goods;
    }

    public void setGoods(List<ProductDto> goods) {
        this.goods = goods;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }
}
