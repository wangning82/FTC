/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.advert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 广告Entity
 * @author wangqingxiang
 * @version 2017-06-03
 */
public class AdvertDetail extends DataEntity<AdvertDetail> {
	
	private static final long serialVersionUID = 1L;

	private Advert advert;		// 广告位ID 父类
	private String title;		// 标题
	private String sort;		// 排序
	private String href;		// 链接地址
	private String status;		// 状态
	private String picImg;		// 展示图片
	private Date beginTime;		// 广告起始时间
	private Date endTime;		// 广告结束时间
	private String content;		// 广告内容
	
	public AdvertDetail() {
		super();
	}

	public AdvertDetail(String id){
		super(id);
	}

	public AdvertDetail(Advert advert){
		this.advert = advert;
	}


	@JsonIgnore
	public Advert getAdvert() {
		return advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
	
	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=9, message="排序长度必须介于 0 和 9 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=255, message="链接地址长度必须介于 0 和 255 之间")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	@JsonIgnore
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="展示图片长度必须介于 0 和 255 之间")
	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	@JsonIgnore
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@JsonIgnore
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=255, message="广告内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonIgnore
	@Override
	public Date getUpdateDate() {
		return super.getUpdateDate();
	}

	@JsonIgnore
	@Override
	public boolean getIsNewRecord() {
		return super.getIsNewRecord();
	}

	@JsonIgnore
	@Override
	public Date getCreateDate() {
		return super.getCreateDate();
	}
	@JsonIgnore
	@Override
	public User getCreateBy() {
		return super.getCreateBy();
	}
	@JsonIgnore
	@Override
	public User getUpdateBy() {
		return super.getUpdateBy();
	}
}