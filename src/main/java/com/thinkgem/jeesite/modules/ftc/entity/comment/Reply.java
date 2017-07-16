/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.comment;

import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评论Entity
 * @author wangqingxiang
 * @version 2017-06-04
 */
public class Reply extends DataEntity<Reply> {
	
	private static final long serialVersionUID = 1L;
	private Comment comment;		// 评论ID 父类
	private Customer customer;		// 用户ID
	private String content;		// 评论内容
	private String goodCount;		// 好评数
	private String status;		// 状态
	private String type;		// 评论类型
	private Reply parent;//上级
	
	public Reply() {
		super();
	}

	public Reply(String id){
		super(id);
	}
	public Reply(Comment comment){
		this.comment=comment;
	}

	public Reply getParent() {
		return parent;
	}

	public void setParent(Reply parent) {
		this.parent = parent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="评论类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}