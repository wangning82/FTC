/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.commont2;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评论Entity
 * @author wangqingxiang
 * @version 2017-05-19
 */
public class FtcComment extends DataEntity<FtcComment> {
	
	private static final long serialVersionUID = 1L;
	private String product;		// 商品ID
	private User user;		// 用户ID
	private String userName;		// 昵称
	private String picImg;		// 用户头像
	private String order;		// 订单ID
	private String star;		// 评论星级：1,2,3,4,5
	private String content;		// 评论内容
	private String goodCount;		// 好评数
	private String status;		// 状态：1.显示；0.隐藏
	private String type;		// 评论类型：1,优质；0,普通
	
	public FtcComment() {
		super();
	}

	public FtcComment(String id){
		super(id);
	}

	@Length(min=0, max=64, message="商品ID长度必须介于 0 和 64 之间")
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=30, message="昵称长度必须介于 0 和 30 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="用户头像长度必须介于 0 和 255 之间")
	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	
	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	@Length(min=0, max=4, message="评论星级：1,2,3,4,5长度必须介于 0 和 4 之间")
	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}
	
	@Length(min=0, max=255, message="评论内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=11, message="好评数长度必须介于 0 和 11 之间")
	public String getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(String goodCount) {
		this.goodCount = goodCount;
	}
	
	@隐藏长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="评论类型：1,优质；0,普通长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}