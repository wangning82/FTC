package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;

import java.math.BigDecimal;

/**
 * Created by houyi on 2017/7/11.
 */
public class CustomerSoldDto extends BaseDto<CustomerSoldDto> {
    private String id;
    private ProductDto good;
    private BigDecimal count; // 销售数量
    private BigDecimal incomeTotal; // 总销售
    private BigDecimal incomeReal; // 总盈利

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

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(BigDecimal incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public BigDecimal getIncomeReal() {
        return incomeReal;
    }

    public void setIncomeReal(BigDecimal incomeReal) {
        this.incomeReal = incomeReal;
    }
}
