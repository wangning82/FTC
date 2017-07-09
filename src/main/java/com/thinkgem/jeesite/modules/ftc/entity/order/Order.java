/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.order;

import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import java.math.BigDecimal;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单Entity
 * @author houyi
 * @version 2017-05-28
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单编号
	private Customer customer;		// 顾客
	private String payType;		// 支付方式
	private String shipmentTime;		// 配送时间
	private String shipmentType;		// 配送方式
	private BigDecimal shipmentAmount;		// 快递费
	private String invoiceType;		// 发票类型
	private String invoiceTitle;		// 发票抬头
	private String orderStatus;		// 订单状态
	private BigDecimal orderAmount;		// 订单金额
	private BigDecimal orderScore;		// 订单积分
	private BigDecimal payAmount;		// 支付金额
	private BigDecimal buyNumber;		// 商品总数量
	private List<OrderProduct> orderProductList = Lists.newArrayList();		// 子表列表

	private Address address;
	
	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单编号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Length(min=0, max=2, message="支付方式长度必须介于 0 和 2 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=2, message="配送时间长度必须介于 0 和 2 之间")
	public String getShipmentTime() {
		return shipmentTime;
	}

	public void setShipmentTime(String shipmentTime) {
		this.shipmentTime = shipmentTime;
	}
	
	@Length(min=0, max=2, message="配送方式长度必须介于 0 和 2 之间")
	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}
	
	public BigDecimal getShipmentAmount() {
		return shipmentAmount;
	}

	public void setShipmentAmount(BigDecimal shipmentAmount) {
		this.shipmentAmount = shipmentAmount;
	}
	
	@Length(min=0, max=2, message="发票类型长度必须介于 0 和 2 之间")
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
	
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public BigDecimal getOrderScore() {
		return orderScore;
	}

	public void setOrderScore(BigDecimal orderScore) {
		this.orderScore = orderScore;
	}
	
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	
	public BigDecimal getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(BigDecimal buyNumber) {
		this.buyNumber = buyNumber;
	}
	
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}