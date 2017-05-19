/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.order;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单Entity
 * @author wangqingxiang
 * @version 2017-05-19
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String orderNumber;		// 订单编号,系统生成
	private String user;		// 用户ID
	private String payType;		// 支付方式 0=线下支付，1=在线支付
	private String shipmentTime;		// 配送时间 1=不限送货时间，2=工作日送货，3=双休日、假日送货
	private String shipmentType;		// 配送方式 0=快递配送（免运费），1=快递配送（运费）
	private String shipmentAmount;		// 快递费
	private String invoiceType;		// 支付方式 1=不开发票，2=电子发票，3=普通发票
	private String invoiceTitle;		// 发票抬头
	private String orderStatus;		// 订单状态
	private String orderAmount;		// 订单金额
	private String orderScore;		// 订单积分
	private String payAmount;		// 支付金额 = 订单金额 + 快递费
	private String buyNumber;		// 商品总数量
	
	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单编号,系统生成长度必须介于 0 和 64 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Length(min=0, max=64, message="用户ID长度必须介于 0 和 64 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Length(min=0, max=2, message="支付方式 0=线下支付，1=在线支付长度必须介于 0 和 2 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=2, message="配送时间 1=不限送货时间，2=工作日送货，3=双休日、假日送货长度必须介于 0 和 2 之间")
	public String getShipmentTime() {
		return shipmentTime;
	}

	public void setShipmentTime(String shipmentTime) {
		this.shipmentTime = shipmentTime;
	}
	
	@Length(min=0, max=2, message="配送方式 0=快递配送（免运费），1=快递配送（运费）长度必须介于 0 和 2 之间")
	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}
	
	public String getShipmentAmount() {
		return shipmentAmount;
	}

	public void setShipmentAmount(String shipmentAmount) {
		this.shipmentAmount = shipmentAmount;
	}
	
	@Length(min=0, max=2, message="支付方式 1=不开发票，2=电子发票，3=普通发票长度必须介于 0 和 2 之间")
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	
	@Length(min=0, max=64, message="发票抬头长度必须介于 0 和 64 之间")
	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	
	@Length(min=0, max=2, message="订单状态长度必须介于 0 和 2 之间")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	@Length(min=0, max=11, message="订单积分长度必须介于 0 和 11 之间")
	public String getOrderScore() {
		return orderScore;
	}

	public void setOrderScore(String orderScore) {
		this.orderScore = orderScore;
	}
	
	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
	@Length(min=0, max=11, message="商品总数量长度必须介于 0 和 11 之间")
	public String getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(String buyNumber) {
		this.buyNumber = buyNumber;
	}
	
}