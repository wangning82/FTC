/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 规格Entity
 * @author wangqingxiang
 * @version 2017-05-24
 */
public class Specification extends DataEntity<Specification> {
	
	private static final long serialVersionUID = 1L;
	private Category category;		// 分类ID
	private String name;		// 规格名称
	private String status;		// 状态
	private String sort;		// 排序
	private List<SpecAttribute> specAttributeList = Lists.newArrayList();		// 子表列表
	
	public Specification() {
		super();
	}

	public Specification(String id){
		super(id);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Length(min=0, max=64, message="规格名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
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
	
	public List<SpecAttribute> getSpecAttributeList() {
		return specAttributeList;
	}

	public void setSpecAttributeList(List<SpecAttribute> specAttributeList) {
		this.specAttributeList = specAttributeList;
	}
}