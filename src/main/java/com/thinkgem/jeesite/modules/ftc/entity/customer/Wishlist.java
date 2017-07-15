/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.customer;

import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 收藏Entity
 * @author wangqingxiang
 * @version 2017-07-06
 */
public class Wishlist extends DataEntity<Wishlist> {
	
	private static final long serialVersionUID = 1L;
	private Product product = new Product();		// 商品ID
	private Customer customer = new Customer();		// 用户ID
	private Customer designer = new Customer();		// 店铺id
	private String type;		// 类型
	
	public Wishlist() {
		super();
	}

	public Wishlist(String id){
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
	
	public Customer getDesigner() {
		return designer;
	}

	public void setDesigner(Customer designer) {
		this.designer = designer;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}