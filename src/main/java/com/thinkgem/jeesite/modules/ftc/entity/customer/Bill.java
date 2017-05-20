/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.customer;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 账单Entity
 * @author wangqingxiang
 * @version 2017-05-20
 */
public class Bill extends DataEntity<Bill> {
	
	private static final long serialVersionUID = 1L;
	private String money;		// 金额
	private String type;		// 类型 1-入账 2-提现
	private String remark;		// 备注
	private String orderId;		// 订单id
	private String customerId;		// 客户id
	private String status;		// 状态 1-申请 2-到账 3-未通过 4-退款
	
	public Bill() {
		super();
	}

	public Bill(String id){
		super(id);
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@Length(min=0, max=2, message="类型 1-入账 2-提现长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=64, message="订单id长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=64, message="客户id长度必须介于 0 和 64 之间")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Length(min=0, max=1, message="状态 1-申请 2-到账 3-未通过 4-退款长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}