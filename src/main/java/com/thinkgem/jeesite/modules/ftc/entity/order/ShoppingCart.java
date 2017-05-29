/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.order;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import java.math.BigDecimal;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 购物车Entity
 * @author houyi
 * @version 2017-05-29
 */
public class ShoppingCart extends DataEntity<ShoppingCart> {
	
	private static final long serialVersionUID = 1L;
	private String productSpecNumber;		// 商品规格编号
	private Customer customer;		// 顾客ID
	private BigDecimal buyNumber;		// 购买数量
	private String checkStatus;		// 购物车状态
	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(String id){
		super(id);
	}

	@Length(min=0, max=64, message="商品规格编号长度必须介于 0 和 64 之间")
	public String getProductSpecNumber() {
		return productSpecNumber;
	}

	public void setProductSpecNumber(String productSpecNumber) {
		this.productSpecNumber = productSpecNumber;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public BigDecimal getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(BigDecimal buyNumber) {
		this.buyNumber = buyNumber;
	}
	
	@Length(min=0, max=2, message="购物车状态长度必须介于 0 和 2 之间")
	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}