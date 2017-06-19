/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.customer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;
import com.thinkgem.jeesite.modules.ftc.dao.customer.AddressDao;

/**
 * 收货地址Service
 * @author houyi
 * @version 2017-06-19
 */
@Service
@Transactional(readOnly = true)
public class AddressService extends CrudService<AddressDao, Address> {

	public Address get(String id) {
		return super.get(id);
	}
	
	public List<Address> findList(Address address) {
		return super.findList(address);
	}
	
	public Page<Address> findPage(Page<Address> page, Address address) {
		return super.findPage(page, address);
	}
	
	@Transactional(readOnly = false)
	public void save(Address address) {
		super.save(address);
	}
	
	@Transactional(readOnly = false)
	public void delete(Address address) {
		super.delete(address);
	}
	
}