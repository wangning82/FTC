/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 规格Entity
 * @author wangqingxiang
 * @version 2017-05-24
 */
public class SpecAttribute extends DataEntity<SpecAttribute> {
	
	private static final long serialVersionUID = 1L;
	private Specification spec;		// 规格ID 父类
	private String name;		// 规格属性名称
	
	public SpecAttribute() {
		super();
	}

	public SpecAttribute(String id){
		super(id);
	}

	public SpecAttribute(Specification spec){
		this.spec = spec;
	}

	public Specification getSpec() {
		return spec;
	}

	public void setSpec(Specification spec) {
		this.spec = spec;
	}

	@Length(min=0, max=64, message="规格属性名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}