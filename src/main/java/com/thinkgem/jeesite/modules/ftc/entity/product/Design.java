/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 设计Entity
 * @author wangqingxiang
 * @version 2017-05-21
 */
public class Design extends DataEntity<Design> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String code;		// 编号
	private Product model;		// 模型
	private Double price;		// 设计费
	private String designStatus;		// 状态
	private List<DesignDetail> details;//设计明细
	private Customer customer;
	private String picImg;//展示图片
	private List<DesignSeed> seeds;

	public List<DesignSeed> getSeeds() {
		return seeds;
	}

	public void setSeeds(List<DesignSeed> seeds) {
		this.seeds = seeds;
	}

	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Design() {
		super();
	}

	public Design(String id){
		super(id);
	}

	public List<DesignDetail> getDetails() {
		return details;
	}

	public void setDetails(List<DesignDetail> details) {
		this.details = details;
	}

	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="编号长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setModel(Product model) {
		this.model = model;
	}

	public Product getModel() {
		return model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getDesignStatus() {
		return designStatus;
	}

	public void setDesignStatus(String designStatus) {
		this.designStatus = designStatus;
	}
	
}