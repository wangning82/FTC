/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.commont;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 回复Entity
 * @author wangqingxiang
 * @version 2017-05-20
 */
public class Reply extends DataEntity<Reply> {
	
	private static final long serialVersionUID = 1L;
	private String commentId;		// 评论ID
	private User user;		// 用户ID
	private String userName;		// 昵称
	private String picImg;		// 用户头像
	private String content;		// 评论内容
	private String goodCount;		// 好评数
	private String status;		// 状态：1.显示；0.隐藏
	private String type;		// 评论类型：1,官方回复；0,用户回复
	
	public Reply() {
		super();
	}

	public Reply(String id){
		super(id);
	}

	@Length(min=0, max=64, message="评论ID长度必须介于 0 和 64 之间")
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
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

	@Length(min=0, max=2, message="隐藏长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="评论类型：1,官方回复；0,用户回复长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}