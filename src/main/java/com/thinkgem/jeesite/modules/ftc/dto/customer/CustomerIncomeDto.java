package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.math.BigDecimal;

/**
 * Created by houyi on 2017/7/12.
 */
public class CustomerIncomeDto extends BaseDto<CustomerIncomeDto> {
    private BigDecimal dayTotal; // 日销售
    private BigDecimal dayReal; // 日盈利
    private BigDecimal monthTotal; // 月销售
    private BigDecimal monthReal; // 月盈利
    private BigDecimal lifeTotal; // 全部销售
    private BigDecimal lifeReal; // 全部盈利

    public BigDecimal getLifeTotal() {
        return lifeTotal;
    }

    public void setLifeTotal(BigDecimal lifeTotal) {
        this.lifeTotal = lifeTotal;
    }

    public BigDecimal getLifeReal() {
        return lifeReal;
    }

    public void setLifeReal(BigDecimal lifeReal) {
        this.lifeReal = lifeReal;
    }

    public BigDecimal getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(BigDecimal dayTotal) {
        this.dayTotal = dayTotal;
    }

    public BigDecimal getDayReal() {
        return dayReal;
    }

    public void setDayReal(BigDecimal dayReal) {
        this.dayReal = dayReal;
    }

    public BigDecimal getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(BigDecimal monthTotal) {
        this.monthTotal = monthTotal;
    }

    public BigDecimal getMonthReal() {
        return monthReal;
    }

    public void setMonthReal(BigDecimal monthReal) {
        this.monthReal = monthReal;
    }
}
