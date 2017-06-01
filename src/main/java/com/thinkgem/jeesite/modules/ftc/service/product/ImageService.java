/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.product.Image;
import com.thinkgem.jeesite.modules.ftc.dao.product.ImageDao;

/**
 * 图片Service
 * @author wangqingxiang
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class ImageService extends CrudService<ImageDao, Image> {

	public Image get(String id) {
		return super.get(id);
	}
	
	public List<Image> findList(Image image) {
		return super.findList(image);
	}
	
	public Page<Image> findPage(Page<Image> page, Image image) {
		return super.findPage(page, image);
	}
	
	@Transactional(readOnly = false)
	public void save(Image image) {
		super.save(image);
	}
	
	@Transactional(readOnly = false)
	public void delete(Image image) {
		super.delete(image);
	}
	
}