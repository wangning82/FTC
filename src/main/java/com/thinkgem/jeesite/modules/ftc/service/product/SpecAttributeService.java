/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;
import com.thinkgem.jeesite.modules.ftc.dao.product.SpecAttributeDao;

/**
 * 规格属性Service
 * @author wangqingxiang
 * @version 2017-05-24
 */
@Service
@Transactional(readOnly = true)
public class SpecAttributeService extends CrudService<SpecAttributeDao, SpecAttribute> {

	
	public SpecAttribute get(String id) {
		SpecAttribute specAttribute = super.get(id);
		return specAttribute;
	}
	
	public List<SpecAttribute> findList(SpecAttribute specAttribute) {
		return super.findList(specAttribute);
	}
	
	public Page<SpecAttribute> findPage(Page<SpecAttribute> page, SpecAttribute specAttribute) {
		return super.findPage(page, specAttribute);
	}
	
	@Transactional(readOnly = false)
	public void save(SpecAttribute specAttribute) {
		super.save(specAttribute);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpecAttribute specAttribute) {
		super.delete(specAttribute);
	}
	
}