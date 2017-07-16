/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.customer;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.customer.CustomerBill;

/**
 * 账单DAO接口
 * @author houyi
 * @version 2017-06-04
 */
@MyBatisDao
public interface CustomerBillDao extends CrudDao<CustomerBill> {

    /**
     * 统计
     * @param entry
     * @return
     */
    CustomerBill findTotalBill(CustomerBill entry);
}