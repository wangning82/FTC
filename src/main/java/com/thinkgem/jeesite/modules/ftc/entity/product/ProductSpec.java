/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品规格Entity
 * @author wangqignxiang
 * @version 2017-05-25
 */
public class ProductSpec extends DataEntity<ProductSpec> {
	
	private static final long serialVersionUID = 1L;
	private String productSpecNumber;		// 商品规格编号
	private String productId;		// 商品ID
	private SpecAttribute spec;		// 规格
	private String stock;		// 库存
	private String salesVolume;		// 销售量
	private String price;		// 价格
	private String score;		// 积分
	private String defaultStatus;		// 是否默认状态：0,不默认；1,默认
	private String status;		// 商品状态
	private String picImg;   //图片

	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}

	public SpecAttribute getSpec() {
		return spec;
	}

	public void setSpec(SpecAttribute spec) {
		this.spec = spec;
	}

	public ProductSpec() {
		super();
	}

	public ProductSpec(String id){
		super(id);
	}
	public ProductSpec(Product product){
		this.setProductId(product.getId());
	}

	@Length(min=0, max=64, message="商品规格编号长度必须介于 0 和 64 之间")
	public String getProductSpecNumber() {
		return productSpecNumber;
	}

	public void setProductSpecNumber(String productSpecNumber) {
		this.productSpecNumber = productSpecNumber;
	}
	
	@Length(min=0, max=64, message="商品ID长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	

	@Length(min=0, max=11, message="库存长度必须介于 0 和 11 之间")
	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	
	@Length(min=0, max=11, message="销售量长度必须介于 0 和 11 之间")
	public String getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(String salesVolume) {
		this.salesVolume = salesVolume;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=11, message="积分长度必须介于 0 和 11 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=2, message="是否默认状态：0,不默认；1,默认长度必须介于 0 和 2 之间")
	public String getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
	
	@Length(min=0, max=2, message="商品状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}