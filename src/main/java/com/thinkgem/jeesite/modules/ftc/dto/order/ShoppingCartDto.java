package com.thinkgem.jeesite.modules.ftc.dto.order;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;

import java.math.BigDecimal;

/**
 * Created by houyi on 2017/7/6 0006.
 */
public class ShoppingCartDto extends BaseDto<ShoppingCartDto> {

    private String id;
    private ProductDto productDto;
    private BigDecimal buyNumber;
    private String selected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public BigDecimal getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(BigDecimal buyNumber) {
        this.buyNumber = buyNumber;
    }
}
