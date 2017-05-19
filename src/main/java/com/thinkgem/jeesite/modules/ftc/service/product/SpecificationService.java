/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.product.Specification;
import com.thinkgem.jeesite.modules.ftc.dao.product.SpecificationDao;

/**
 * 规格Service
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Service
@Transactional(readOnly = true)
public class SpecificationService extends CrudService<SpecificationDao, Specification> {

	public Specification get(String id) {
		return super.get(id);
	}
	
	public List<Specification> findList(Specification specification) {
		return super.findList(specification);
	}
	
	public Page<Specification> findPage(Page<Specification> page, Specification specification) {
		return super.findPage(page, specification);
	}
	
	@Transactional(readOnly = false)
	public void save(Specification specification) {
		super.save(specification);
	}
	
	@Transactional(readOnly = false)
	public void delete(Specification specification) {
		super.delete(specification);
	}
	
}