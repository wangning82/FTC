/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderDao;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderProduct;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderProductDao;

/**
 * 订单Service
 * @author houyi
 * @version 2017-05-28
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends CrudService<OrderDao, Order> {

	@Autowired
	private OrderProductDao orderProductDao;
	
	public Order get(String id) {
		Order order = super.get(id);
		order.setOrderProductList(orderProductDao.findList(new OrderProduct(order)));
		return order;
	}
	
	public List<Order> findList(Order order) {
		return super.findList(order);
	}
	
	public Page<Order> findPage(Page<Order> page, Order order) {
		return super.findPage(page, order);
	}
	
	@Transactional(readOnly = false)
	public void save(Order order) {
		super.save(order);
		for (OrderProduct orderProduct : order.getOrderProductList()){
			if (orderProduct.getId() == null){
				continue;
			}
			if (OrderProduct.DEL_FLAG_NORMAL.equals(orderProduct.getDelFlag())){
				if (StringUtils.isBlank(orderProduct.getId())){
					orderProduct.setOrder(order);
					orderProduct.preInsert();
					orderProductDao.insert(orderProduct);
				}else{
					orderProduct.preUpdate();
					orderProductDao.update(orderProduct);
				}
			}else{
				orderProductDao.delete(orderProduct);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Order order) {
		super.delete(order);
		orderProductDao.delete(new OrderProduct(order));
	}
	
}