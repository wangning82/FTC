/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 位置Entity
 * @author wangqingxiang
 * @version 2017-05-31
 */
public class Position extends DataEntity<Position> {
	
	private static final long serialVersionUID = 1L;
	private Category category;		// 类目ID
	private String name;		// 名称
	private String sort;		// 排序
	private String status;		// 状态
	private Double width; //宽度

	private Double height;//高度
	private Double fromX;//横坐标
	private Double fromY;//纵坐标
	private Double scale;//缩放
	private Double rotation;//旋转

	public Position() {
		super();
	}

	public Position(String id){
		super(id);
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getFromX() {
		return fromX;
	}

	public void setFromX(Double fromX) {
		this.fromX = fromX;
	}

	public Double getFromY() {
		return fromY;
	}

	public void setFromY(Double fromY) {
		this.fromY = fromY;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public Double getRotation() {
		return rotation;
	}

	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	public Position(Category category){
		this.category=category;
	}
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=9, message="排序长度必须介于 0 和 9 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
}