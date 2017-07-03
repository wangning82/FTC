/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图片Entity
 * @author wangqingxiang
 * @version 2017-06-01
 */
public class Image extends DataEntity<Image> {
	
	private static final long serialVersionUID = 1L;
	private Product product;		// 商品
	private String url;		// 展示图片
	private Integer sort;		// 排序
	private String status;		// 状态
	private Position position;		// 位置id
	private Design design;		// 设计id
	private String info;		// 信息
	private Double rotation;		// 旋转
	private Double scale;		// 缩放
	private String sourceUrl;		// 原图路径
	private Double x;//横坐标
	private Double y;//纵坐标

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Image() {
		super();
	}

	public Image(String id){
		super(id);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Length(min=0, max=255, message="展示图片长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}
	
	@Length(min=0, max=255, message="信息长度必须介于 0 和 255 之间")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public Double getRotation() {
		return rotation;
	}

	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}
	
	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
}