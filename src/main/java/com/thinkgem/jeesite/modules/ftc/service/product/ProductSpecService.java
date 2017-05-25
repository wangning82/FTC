/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductSpecDao;

/**
 * 商品规格Service
 * @author wangqignxiang
 * @version 2017-05-25
 */
@Service
@Transactional(readOnly = true)
public class ProductSpecService extends CrudService<ProductSpecDao, ProductSpec> {

	public ProductSpec get(String id) {
		return super.get(id);
	}
	
	public List<ProductSpec> findList(ProductSpec productSpec) {
		return super.findList(productSpec);
	}
	
	public Page<ProductSpec> findPage(Page<ProductSpec> page, ProductSpec productSpec) {
		return super.findPage(page, productSpec);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductSpec productSpec) {
		super.save(productSpec);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductSpec productSpec) {
		super.delete(productSpec);
	}
	
}