/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.Salary;

/**
 * 工资DAO接口
 * @author wangqignxiang
 * @version 2017-07-17
 */
@MyBatisDao
public interface SalaryDao extends CrudDao<Salary> {
	
}