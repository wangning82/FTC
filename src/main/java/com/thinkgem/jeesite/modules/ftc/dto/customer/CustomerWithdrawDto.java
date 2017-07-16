package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.math.BigDecimal;

/**
 * Created by houyi on 2017/7/16 0016.
 */
public class CustomerWithdrawDto extends BaseDto<CustomerWithdrawDto> {
    private BigDecimal total; // 总收益
    private BigDecimal remain; // 当前余额
    private BigDecimal withdraw; // 已经提现

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getRemain() {
        return remain;
    }

    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    public BigDecimal getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }
}
