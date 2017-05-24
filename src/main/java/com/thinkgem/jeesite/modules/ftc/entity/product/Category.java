/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 分类Entity
 * @author wangqingxiang
 * @version 2017-05-19
 */
public class Category extends TreeEntity<Category> {
	
	private static final long serialVersionUID = 1L;
	private Category parent;		// 父分类ID
	private String name;		// 分类名称

	private String type;		// 目录类型 2=二级目录/1=一级目录/0=总目录
	private String status;		// 状态 1=显示/0=隐藏
	private String showInNav;		// 是否导航栏 1=显示/0=隐藏
	private String showInTop;		// 是否置顶 1=置顶/0=默认
	private String showInHot;		// 是否热门 1=热门/0=默认
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String pageTitle;		// 页面标题
	private String pageDescription;		// 页面描述
	private String pageKeyword;		// 页面关键词
	
	public Category() {
		super();
	}

	public Category(String id){
		super(id);
	}

	@JsonBackReference
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=64, message="分类名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Length(min=0, max=2, message="目录类型 2=二级目录/1=一级目录/0=总目录长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=2, message="状态 1=显示/0=隐藏长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="是否导航栏 1=显示/0=隐藏长度必须介于 0 和 2 之间")
	public String getShowInNav() {
		return showInNav;
	}

	public void setShowInNav(String showInNav) {
		this.showInNav = showInNav;
	}
	
	@Length(min=0, max=2, message="是否置顶 1=置顶/0=默认长度必须介于 0 和 2 之间")
	public String getShowInTop() {
		return showInTop;
	}

	public void setShowInTop(String showInTop) {
		this.showInTop = showInTop;
	}
	
	@Length(min=0, max=2, message="是否热门 1=热门/0=默认长度必须介于 0 和 2 之间")
	public String getShowInHot() {
		return showInHot;
	}

	public void setShowInHot(String showInHot) {
		this.showInHot = showInHot;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=64, message="页面标题长度必须介于 0 和 64 之间")
	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	@Length(min=0, max=64, message="页面描述长度必须介于 0 和 64 之间")
	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}
	
	@Length(min=0, max=64, message="页面关键词长度必须介于 0 和 64 之间")
	public String getPageKeyword() {
		return pageKeyword;
	}

	public void setPageKeyword(String pageKeyword) {
		this.pageKeyword = pageKeyword;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}