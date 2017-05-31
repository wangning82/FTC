/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderShipment;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderShipmentDao;

/**
 * 订单配送Service
 * @author houyi
 * @version 2017-05-30
 */
@Service
@Transactional(readOnly = true)
public class OrderShipmentService extends CrudService<OrderShipmentDao, OrderShipment> {

	public OrderShipment get(String id) {
		return super.get(id);
	}
	
	public List<OrderShipment> findList(OrderShipment orderShipment) {
		return super.findList(orderShipment);
	}
	
	public Page<OrderShipment> findPage(Page<OrderShipment> page, OrderShipment orderShipment) {
		return super.findPage(page, orderShipment);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderShipment orderShipment) {
		super.save(orderShipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderShipment orderShipment) {
		super.delete(orderShipment);
	}
	
}