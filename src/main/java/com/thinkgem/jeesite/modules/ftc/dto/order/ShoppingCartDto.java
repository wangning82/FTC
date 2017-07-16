package com.thinkgem.jeesite.modules.ftc.dto.order;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;

import java.math.BigDecimal;

/**
 * Created by houyi on 2017/7/6 0006.
 */
public class ShoppingCartDto extends BaseDto<ShoppingCartDto> {

    private String id;
    private ProductDto good;
    private boolean selected; // 是否选中
    private BigDecimal count; // 购买数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductDto getGood() {
        return good;
    }

    public void setGood(ProductDto good) {
        this.good = good;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
