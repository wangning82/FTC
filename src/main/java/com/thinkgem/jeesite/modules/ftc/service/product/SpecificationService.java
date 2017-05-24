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
import com.thinkgem.jeesite.modules.ftc.entity.product.Specification;
import com.thinkgem.jeesite.modules.ftc.dao.product.SpecificationDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;
import com.thinkgem.jeesite.modules.ftc.dao.product.SpecAttributeDao;

/**
 * 规格Service
 * @author wangqingxiang
 * @version 2017-05-24
 */
@Service
@Transactional(readOnly = true)
public class SpecificationService extends CrudService<SpecificationDao, Specification> {

	@Autowired
	private SpecAttributeDao specAttributeDao;
	
	public Specification get(String id) {
		Specification specification = super.get(id);
		specification.setSpecAttributeList(specAttributeDao.findList(new SpecAttribute(specification)));
		return specification;
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
		for (SpecAttribute specAttribute : specification.getSpecAttributeList()){
			if (specAttribute.getId() == null){
				continue;
			}
			if (SpecAttribute.DEL_FLAG_NORMAL.equals(specAttribute.getDelFlag())){
				if (StringUtils.isBlank(specAttribute.getId())){
					specAttribute.setSpec(specification);
					specAttribute.preInsert();
					specAttributeDao.insert(specAttribute);
				}else{
					specAttribute.preUpdate();
					specAttributeDao.update(specAttribute);
				}
			}else{
				specAttributeDao.delete(specAttribute);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Specification specification) {
		super.delete(specification);
		specAttributeDao.delete(new SpecAttribute(specification));
	}
	
}