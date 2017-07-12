/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.order;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 订单DAO接口
 *
 * @author houyi
 * @version 2017-05-28
 */
@MyBatisDao
public interface OrderDao extends CrudDao<Order> {

    /**
     * 当天销售
     * @param designerId
     * @return
     */
    Map<String, BigDecimal> findIncomeToday(String designerId);

    /**
     * 本月销售
     * @param designerId
     * @return
     */
    Map<String, BigDecimal> findIncomeMonth(String designerId);

    /**
     * 合计销售
     * @param designerId
     * @return
     */
    Map<String, BigDecimal> findIncomeAll(String designerId);

}