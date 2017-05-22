/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品Entity
 * @author wangqingxiang
 * @version 2017-05-19
 */
public class Product extends DataEntity<Product> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 商品编号
	private String labelId;		// 标签ID
	private String name;		// 商品名称
	private String showScore;		// 显示积分
	private String showPrice;		// 显示价格
	private String introduce;		// 商品简介
	private String picImg;		// 展示图片
	private String showInTop;		// 是否置顶 1=置顶/0=默认
	private String showInNav;		// 是否导航栏 1=显示/0=隐藏
	private String showInHot;		// 是否热门 1=热门/0=默认
	private String showInShelve;		// 是否上架：1=上架/0=下架
	private Date createTime;		// 创建时间
	private Date shelveTime;		// 上架时间
	private String shelveBy;		// 上架人
	private String searchKey;		// 搜索关键词
	private String pageTitle;		// 页面标题
	private String pageDescription;		// 页面描述
	private String pageKeyword;		// 页面关键词
	
	public Product() {
		super();
	}

	public Product(String id){
		super(id);
	}

	@Length(min=0, max=64, message="商品编号长度必须介于 0 和 64 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=64, message="标签ID长度必须介于 0 和 64 之间")
	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	
	@Length(min=0, max=64, message="商品名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="显示积分长度必须介于 0 和 11 之间")
	public String getShowScore() {
		return showScore;
	}

	public void setShowScore(String showScore) {
		this.showScore = showScore;
	}
	
	public String getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(String showPrice) {
		this.showPrice = showPrice;
	}
	
	@Length(min=0, max=64, message="商品简介长度必须介于 0 和 64 之间")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Length(min=0, max=255, message="展示图片长度必须介于 0 和 255 之间")
	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	
	@Length(min=0, max=2, message="是否置顶 1=置顶/0=默认长度必须介于 0 和 2 之间")
	public String getShowInTop() {
		return showInTop;
	}

	public void setShowInTop(String showInTop) {
		this.showInTop = showInTop;
	}
	
	@Length(min=0, max=2, message="是否导航栏 1=显示/0=隐藏长度必须介于 0 和 2 之间")
	public String getShowInNav() {
		return showInNav;
	}

	public void setShowInNav(String showInNav) {
		this.showInNav = showInNav;
	}
	
	@Length(min=0, max=2, message="是否热门 1=热门/0=默认长度必须介于 0 和 2 之间")
	public String getShowInHot() {
		return showInHot;
	}

	public void setShowInHot(String showInHot) {
		this.showInHot = showInHot;
	}
	
	@Length(min=0, max=2, message="是否上架：1=上架/0=下架长度必须介于 0 和 2 之间")
	public String getShowInShelve() {
		return showInShelve;
	}

	public void setShowInShelve(String showInShelve) {
		this.showInShelve = showInShelve;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShelveTime() {
		return shelveTime;
	}

	public void setShelveTime(Date shelveTime) {
		this.shelveTime = shelveTime;
	}
	
	@Length(min=0, max=64, message="上架人长度必须介于 0 和 64 之间")
	public String getShelveBy() {
		return shelveBy;
	}

	public void setShelveBy(String shelveBy) {
		this.shelveBy = shelveBy;
	}
	
	@Length(min=0, max=255, message="搜索关键词长度必须介于 0 和 255 之间")
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	@Length(min=0, max=64, message="页面标题长度必须介于 0 和 64 之间")
	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	@Length(min=0, max=255, message="页面描述长度必须介于 0 和 255 之间")
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
	
}