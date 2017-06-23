/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.OrderNoGenerator;
import com.thinkgem.jeesite.modules.ftc.constant.OrderStatusEnum;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
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

	@Autowired
	private ShoppingCartService shoppingCartService;
	
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

	/**
	 * 取消订单
	 * @param order
	 */
	@Transactional(readOnly = false)
	public void cancelOrder(Order order) {
		order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_CANCELED.getValue());
		super.save(order);
	}

	/**
	 * 创建订单
	 * @param customer
	 * @param shoppingCartIds
	 */
	@Transactional(readOnly = false)
	public void createOrder(Customer customer, String[] shoppingCartIds){
		Order order = new Order();
		order.setOrderNo(OrderNoGenerator.INSTANCE.nextId());
		order.setCustomer(customer);
		order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_FORPAID.getValue());
		super.save(order);

		// 订单明细
		for(String shoppingCartId : shoppingCartIds){
			ShoppingCart shoppingCart = shoppingCartService.get(shoppingCartId);

			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrder(order);
			orderProduct.setProductNumber(shoppingCart.getProduct().getNumber());
			orderProduct.setName(shoppingCart.getProduct().getName());
			orderProduct.setPicImg(shoppingCart.getProduct().getPicImg());
			orderProduct.setProductSpecNumber(shoppingCart.getProductSpec().getProductSpecNumber());
			orderProduct.setProductSpecName(shoppingCart.getProductSpec().getSpec().getName());
			orderProduct.setProductPrice(new BigDecimal(shoppingCart.getProductSpec().getPrice()));
			orderProduct.setBuyNumber(shoppingCart.getBuyNumber());
			orderProduct.setProductAmount(new BigDecimal(shoppingCart.getProductSpec().getPrice()).multiply(shoppingCart.getBuyNumber()));
			orderProduct.setDesign(shoppingCart.getProduct().getDesign());
			orderProduct.setDesignPrice(new BigDecimal(shoppingCart.getProduct().getDesign().getPrice()));
			orderProduct.setDesignAmount(new BigDecimal(shoppingCart.getProduct().getDesign().getPrice()).multiply(shoppingCart.getBuyNumber()));


			shoppingCartService.delete(shoppingCart); // 删除购物车
		}

	}



}