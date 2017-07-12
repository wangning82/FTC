package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.constant.BillStatusEnum;
import com.thinkgem.jeesite.modules.ftc.constant.BillTypeEnum;
import com.thinkgem.jeesite.modules.ftc.dto.customer.WithdrawDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.CustomerBill;
import org.springframework.stereotype.Component;

/**
 * Created by houyi on 2017/7/12 0012.
 */
@Component
public class CustomerBillConverter extends BaseConverter<CustomerBill, WithdrawDto> {

    @Override
    public WithdrawDto convertModelToDto(CustomerBill model) {
        WithdrawDto withdrawDto = new WithdrawDto();
        withdrawDto.setMoney(model.getMoney());
        withdrawDto.setTime(model.getCreateDate());
        if (BillTypeEnum.WITHDRAW.getValue().equals(model.getType())) {
            if (BillStatusEnum.APPLY.getValue().equals(model.getStatus())) {
                withdrawDto.setType("0"); // 提现中
            } else if (BillStatusEnum.ARRIVE.getValue().equals(model.getStatus())) {
                withdrawDto.setType("1"); // 到帐
            }
        }

        return withdrawDto;
    }
}
