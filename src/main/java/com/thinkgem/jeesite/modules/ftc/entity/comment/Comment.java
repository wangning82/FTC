/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.comment;

import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评论Entity
 * @author wangqingxiang
 * @version 2017-06-04
 */
public class Comment extends DataEntity<Comment> {
	
	private static final long serialVersionUID = 1L;
	private Product product;		// 商品ID
	private Customer customer;		// 客户
	private Order order;		// 订单ID
	private String star;		// 评论星级
	private String content;		// 评论内容
	private Integer goodCount;		// 好评数
	private String status;		// 状态
	private String type;		// 评论类型
	private List<Reply> replyList = Lists.newArrayList();		// 子表列表
	
	public Comment() {
		super();
	}

	public Comment(String id){
		super(id);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Length(min=0, max=4, message="评论星级长度必须介于 0 和 4 之间")
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
	
	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
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
	
	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
}