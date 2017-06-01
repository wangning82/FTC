/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.order;

import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderShipment;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 运单Entity
 * @author houyi
 * @version 2017-05-30
 */
public class OrderWaybill extends DataEntity<OrderWaybill> {
	
	private static final long serialVersionUID = 1L;
	private Order order;		// 订单ID
	private OrderShipment shipment;		// 收货地址
	private String waybillNumber;		// 运单号
	private String expressCompany;		// 快递公司
	
	public OrderWaybill() {
		super();
	}

	public OrderWaybill(String id){
		super(id);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public OrderShipment getShipment() {
		return shipment;
	}

	public void setShipment(OrderShipment shipment) {
		this.shipment = shipment;
	}
	
	@Length(min=0, max=64, message="运单号长度必须介于 0 和 64 之间")
	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
	
	@Length(min=0, max=64, message="快递公司长度必须介于 0 和 64 之间")
	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	
}