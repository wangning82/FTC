package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.WishlistDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by bingbing on 2017/7/6.
 * 收藏
 */
@Component
public class WishlistConverter extends BaseConverter<Wishlist, WishlistDto> {
    @Autowired
    private ShopConverter shopConverter;
    @Autowired
    private ProductConverter productConverter;

    @Override
    public WishlistDto convertModelToDto(Wishlist model) {
        WishlistDto dto = new WishlistDto();
        dto.setId(model.getId());
        dto.setShop(shopConverter.convertModelToDto(model.getDesigner()));
        dto.setGoods(productConverter.convertModelToDto(model.getProduct()));
        return dto;

    }
}
