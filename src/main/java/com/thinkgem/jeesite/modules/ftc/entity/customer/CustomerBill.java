/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.customer;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * 账单Entity
 * @author houyi
 * @version 2017-06-04
 */
public class CustomerBill extends DataEntity<CustomerBill> {
	
	private static final long serialVersionUID = 1L;
	private Customer customer = new Customer();		// 客户id
	private Order order = new Order();		// 订单id
	private BigDecimal money;		// 金额
	private String type;		// 类型
	private String status;		// 状态
	private String remark;		// 备注
	
	public CustomerBill() {
		super();
	}

	public CustomerBill(String id){
		super(id);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}