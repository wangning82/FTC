/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.customer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.customer.CustomerBill;
import com.thinkgem.jeesite.modules.ftc.dao.customer.CustomerBillDao;

/**
 * 账单Service
 * @author houyi
 * @version 2017-06-04
 */
@Service
@Transactional(readOnly = true)
public class CustomerBillService extends CrudService<CustomerBillDao, CustomerBill> {

	public CustomerBill get(String id) {
		return super.get(id);
	}
	
	public List<CustomerBill> findList(CustomerBill customerBill) {
		return super.findList(customerBill);
	}
	
	public Page<CustomerBill> findPage(Page<CustomerBill> page, CustomerBill customerBill) {
		return super.findPage(page, customerBill);
	}
	
	@Transactional(readOnly = false)
	public void save(CustomerBill customerBill) {
		super.save(customerBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(CustomerBill customerBill) {
		super.delete(customerBill);
	}
	
}