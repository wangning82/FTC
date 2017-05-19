/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设计Entity
 * @author wangqingxiang
 * @version 2017-05-19
 */
public class Design extends DataEntity<Design> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String code;		// 编号
	private String productId;		// 商品id
	private String price;		// 设计费
	private String designStatus;		// 状态
	
	public Design() {
		super();
	}

	public Design(String id){
		super(id);
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
	
	@Length(min=0, max=64, message="商品id长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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