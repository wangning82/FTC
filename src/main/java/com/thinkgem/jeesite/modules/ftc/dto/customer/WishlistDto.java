package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Wishlist;

/**
 * Created by bingbing on 2017/7/6.
 * 收藏
 */

public class WishlistDto extends BaseDto<WishlistDto>{
    private String id;
    private ShopDto shop;
    private ProductDto goods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShopDto getShop() {
        return shop;
    }

    public void setShop(ShopDto shop) {
        this.shop = shop;
    }

    public ProductDto getGoods() {
        return goods;
    }

    public void setGoods(ProductDto goods) {
        this.goods = goods;
    }
}
