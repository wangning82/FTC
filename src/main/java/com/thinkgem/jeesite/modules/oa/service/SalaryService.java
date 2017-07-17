/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.Salary;
import com.thinkgem.jeesite.modules.oa.dao.SalaryDao;

/**
 * 工资Service
 * @author wangqignxiang
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class SalaryService extends CrudService<SalaryDao, Salary> {

	public Salary get(String id) {
		return super.get(id);
	}
	
	public List<Salary> findList(Salary salary) {
		return super.findList(salary);
	}
	
	public Page<Salary> findPage(Page<Salary> page, Salary salary) {
		return super.findPage(page, salary);
	}
	
	@Transactional(readOnly = false)
	public void save(Salary salary) {
		super.save(salary);
	}
	
	@Transactional(readOnly = false)
	public void delete(Salary salary) {
		super.delete(salary);
	}
	
}