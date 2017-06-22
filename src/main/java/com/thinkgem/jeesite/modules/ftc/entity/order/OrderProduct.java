/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.order;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * 订单Entity
 * @author houyi
 * @version 2017-05-28
 */
public class OrderProduct extends DataEntity<OrderProduct> {
	
	private static final long serialVersionUID = 1L;
	private Order order;		// 订单ID 父类
	private String productNumber;		// 商品编号
	private String name;		// 商品名称
	private String picImg;		// 展示图片
	private String productSpecNumber;		// 商品规格编号
	private String productSpecName;		// 商品规格名称
	private BigDecimal productPrice;		// 生产成本
	private BigDecimal productScore;		// 商品总积分
	private BigDecimal productAmount;		// 商品总金额
	private BigDecimal buyNumber;		// 商品总数量
	private Design design;		// 设计标识
	private BigDecimal designPrice;		// 设计单价
	private BigDecimal designAmount;		// 设计总费用
	private BigDecimal price;		// 价格
	private BigDecimal score;		// 积分
	private String commentStatus;		// 评论状态
	
	public OrderProduct() {
		super();
	}

	public OrderProduct(String id){
		super(id);
	}

	public OrderProduct(Order order){
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Length(min=0, max=64, message="商品编号长度必须介于 0 和 64 之间")
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	@Length(min=0, max=64, message="商品名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="展示图片长度必须介于 0 和 255 之间")
	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	
	@Length(min=0, max=64, message="商品规格编号长度必须介于 0 和 64 之间")
	public String getProductSpecNumber() {
		return productSpecNumber;
	}

	public void setProductSpecNumber(String productSpecNumber) {
		this.productSpecNumber = productSpecNumber;
	}
	
	@Length(min=0, max=64, message="商品规格名称长度必须介于 0 和 64 之间")
	public String getProductSpecName() {
		return productSpecName;
	}

	public void setProductSpecName(String productSpecName) {
		this.productSpecName = productSpecName;
	}
	
	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	public BigDecimal getProductScore() {
		return productScore;
	}

	public void setProductScore(BigDecimal productScore) {
		this.productScore = productScore;
	}
	
	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	
	public BigDecimal getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(BigDecimal buyNumber) {
		this.buyNumber = buyNumber;
	}
	
	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}
	
	public BigDecimal getDesignPrice() {
		return designPrice;
	}

	public void setDesignPrice(BigDecimal designPrice) {
		this.designPrice = designPrice;
	}
	
	public BigDecimal getDesignAmount() {
		return designAmount;
	}

	public void setDesignAmount(BigDecimal designAmount) {
		this.designAmount = designAmount;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	@Length(min=0, max=2, message="评论状态长度必须介于 0 和 2 之间")
	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	
}