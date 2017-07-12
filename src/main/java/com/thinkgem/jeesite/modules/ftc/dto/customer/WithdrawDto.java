package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by houyi on 2017/7/12 0012.
 */
public class WithdrawDto extends BaseDto<WithdrawDto> {
    private Date time;
    private BigDecimal money;
    private String type; // 提现状态

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
