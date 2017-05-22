/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.product.FtcCategory;
import com.thinkgem.jeesite.modules.ftc.dao.product.FtcCategoryDao;

/**
 * 分类Service
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Service
@Transactional(readOnly = true)
public class FtcCategoryService extends TreeService<FtcCategoryDao, FtcCategory> {

	public FtcCategory get(String id) {
		return super.get(id);
	}
	
	public List<FtcCategory> findList(FtcCategory category) {
		if (StringUtils.isNotBlank(category.getParentIds())){
			category.setParentIds(","+category.getParentIds()+",");
		}
		return super.findList(category);
	}
	
	@Transactional(readOnly = false)
	public void save(FtcCategory category) {
		super.save(category);
	}
	
	@Transactional(readOnly = false)
	public void delete(FtcCategory category) {
		super.delete(category);
	}
	
}