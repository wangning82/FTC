/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.advert;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 广告Entity
 * @author wangqingxiang
 * @version 2017-06-03
 */
public class Advert extends DataEntity<Advert> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String width;		// 宽度
	private String height;		// 高度
	private String description;		// 描述
	private String code;		// 代码简称
	private String template;		// 模版内容
	private String defultNumber;		// 默认显示个数
	private String number;		// 广告数量
	private String status;		// 状态
	private String type;		// 广告位类型
	private List<AdvertDetail> advertDetailList = Lists.newArrayList();		// 子表列表
	
	public Advert() {
		super();
	}

	public Advert(String id){
		super(id);
	}

	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="宽度长度必须介于 0 和 11 之间")
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	@Length(min=0, max=11, message="高度长度必须介于 0 和 11 之间")
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=64, message="代码简称长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	@Length(min=0, max=11, message="默认显示个数长度必须介于 0 和 11 之间")
	public String getDefultNumber() {
		return defultNumber;
	}

	public void setDefultNumber(String defultNumber) {
		this.defultNumber = defultNumber;
	}
	
	@Length(min=0, max=11, message="广告数量长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=4, message="广告位类型长度必须介于 0 和 4 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public List<AdvertDetail> getAdvertDetailList() {
		return advertDetailList;
	}

	public void setAdvertDetailList(List<AdvertDetail> advertDetailList) {
		this.advertDetailList = advertDetailList;
	}
}