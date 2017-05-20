/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 规格Entity
 * @author wangqingxiang
 * @version 2017-05-19
 */
public class Specification extends DataEntity<Specification> {
	
	private static final long serialVersionUID = 1L;
	private String category;		// 分类ID
	private String name;		// 规格名称
	private String status;		// 状态：1.显示；0.隐藏
	private String sort;		// 排序
	
	public Specification() {
		super();
	}

	public Specification(String id){
		super(id);
	}

	@Length(min=0, max=64, message="分类ID长度必须介于 0 和 64 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=0, max=64, message="规格名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=2, message="隐藏长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=9, message="排序长度必须介于 0 和 9 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}