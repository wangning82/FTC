/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.List;

/**
 * 商品规格Entity
 * @author wangqignxiang
 * @version 2017-05-25
 */
public class ProductSpec extends DataEntity<ProductSpec> {
	
	private static final long serialVersionUID = 1L;
	private String productSpecNumber;		// 商品规格编号
	private Product  product;		// 商品ID
	private SpecAttribute spec;		// 规格
	private Double stock;		// 库存
	private Double salesVolume;		// 销售量
	private Double price;		// 价格
	private Double score;		// 积分
	private String defaultStatus;		// 是否默认状态：0,不默认；1,默认
	private String status;		// 商品状态

	private List<ProductImage> images;

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
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
		this.product=product;
	}

	@Length(min=0, max=64, message="商品规格编号长度必须介于 0 和 64 之间")
	public String getProductSpecNumber() {
		return productSpecNumber;
	}

	public void setProductSpecNumber(String productSpecNumber) {
		this.productSpecNumber = productSpecNumber;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}
	
	public Double getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Double salesVolume) {
		this.salesVolume = salesVolume;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
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