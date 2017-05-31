/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.order;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderShipment;

/**
 * 订单配送DAO接口
 * @author houyi
 * @version 2017-05-30
 */
@MyBatisDao
public interface OrderShipmentDao extends CrudDao<OrderShipment> {
	
}