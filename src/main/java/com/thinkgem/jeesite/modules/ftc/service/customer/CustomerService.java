/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.customer;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.dao.customer.CustomerDao;

/**
 * 会员Service
 * @author houyi
 * @version 2017-06-04
 */
@Service
@Transactional(readOnly = true)
public class CustomerService extends CrudService<CustomerDao, Customer> {

	/** 用户编号后缀位数 */
	private static final int SUFFIX_NUMBER = 2;

	/**
	 * 获得用户编号
	 * @return
	 */
	private String getUserCode() {
		String prefixNumber = Long.toString(new Date().getTime());
		String suffixNumber = RandomUtils.number(SUFFIX_NUMBER);
		return prefixNumber + suffixNumber;
	}

	public Customer get(String id) {
		return super.get(id);
	}
	
	public List<Customer> findList(Customer customer) {
		return super.findList(customer);
	}
	
	public Page<Customer> findPage(Page<Customer> page, Customer customer) {
		return super.findPage(page, customer);
	}
	
	@Transactional(readOnly = false)
	public void save(Customer customer) {
		if (customer.getIsNewRecord()){
			customer.setUserCode(getUserCode());
		}
		super.save(customer);
	}
	
	@Transactional(readOnly = false)
	public void delete(Customer customer) {
		super.delete(customer);
	}
	
}