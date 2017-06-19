/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.customer;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;

/**
 * 收货地址DAO接口
 * @author houyi
 * @version 2017-06-19
 */
@MyBatisDao
public interface AddressDao extends CrudDao<Address> {
	
}