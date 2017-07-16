/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.customer;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.constant.BillStatusEnum;
import com.thinkgem.jeesite.modules.ftc.constant.BillTypeEnum;
import com.thinkgem.jeesite.modules.ftc.dao.customer.CustomerBillDao;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.customer.CustomerBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账单Service
 *
 * @author houyi
 * @version 2017-06-04
 */
@Service
@Transactional(readOnly = true)
public class CustomerBillService extends CrudService<CustomerBillDao, CustomerBill> {

    @Autowired
    private CustomerService customerService;

    public CustomerBill get(String id) {
        return super.get(id);
    }

    public List<CustomerBill> findList(CustomerBill customerBill) {
        return super.findList(customerBill);
    }

    public Page<CustomerBill> findPage(Page<CustomerBill> page, CustomerBill customerBill) {
        return super.findPage(page, customerBill);
    }

    @Transactional(readOnly = false)
    public void save(CustomerBill customerBill) {
        super.save(customerBill);

        // 退款到帐扣除账户余额
        if (BillTypeEnum.WITHDRAW.getValue().equals(customerBill.getType())
                && BillStatusEnum.ARRIVE.getValue().equals(customerBill.getStatus())) {
            Customer customer = customerService.get(customerBill.getCustomer().getId());
            customer.setBillBlance(customer.getBillBlance().subtract(customerBill.getMoney()));
            customerService.save(customer);
        }

    }

    @Transactional(readOnly = false)
    public void delete(CustomerBill customerBill) {
        super.delete(customerBill);
    }

    @Transactional(readOnly = false)
    public CustomerBill findTotalBill(CustomerBill customerBill) {
        return dao.findTotalBill(customerBill);
    }

}