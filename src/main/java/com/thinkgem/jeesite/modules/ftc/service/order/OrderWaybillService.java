/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderWaybill;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderWaybillDao;

/**
 * 运单Service
 * @author houyi
 * @version 2017-05-30
 */
@Service
@Transactional(readOnly = true)
public class OrderWaybillService extends CrudService<OrderWaybillDao, OrderWaybill> {

	public OrderWaybill get(String id) {
		return super.get(id);
	}
	
	public List<OrderWaybill> findList(OrderWaybill orderWaybill) {
		return super.findList(orderWaybill);
	}
	
	public Page<OrderWaybill> findPage(Page<OrderWaybill> page, OrderWaybill orderWaybill) {
		return super.findPage(page, orderWaybill);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderWaybill orderWaybill) {
		super.save(orderWaybill);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderWaybill orderWaybill) {
		super.delete(orderWaybill);
	}
	
}