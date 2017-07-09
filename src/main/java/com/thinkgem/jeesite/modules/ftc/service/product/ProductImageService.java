/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductImageDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.Image;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 图片Service
 * @author wangqingxiang
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class ProductImageService extends CrudService<ProductImageDao, ProductImage> {

	public ProductImage get(String id) {
		return super.get(id);
	}
	
	public List<ProductImage> findList(ProductImage image) {
		return super.findList(image);
	}
	
	public Page<ProductImage> findPage(Page<ProductImage> page, ProductImage image) {
		return super.findPage(page, image);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductImage image) {
		super.save(image);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductImage image) {
		super.delete(image);
	}
	
}