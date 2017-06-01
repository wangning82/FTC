/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import java.util.List;

import com.thinkgem.jeesite.modules.ftc.dao.product.ImageDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import com.thinkgem.jeesite.modules.ftc.dao.product.DesignDao;

/**
 * 设计Service
 * @author wangqingxiang
 * @version 2017-05-21
 */
@Service
@Transactional(readOnly = true)
public class DesignService extends CrudService<DesignDao, Design> {

	@Autowired
	private ImageDao imageDao;
	public Design get(String id) {
		Design design=super.get(id);
		Image image=new Image();
		image.setDesign(design);
		List<Image> images=imageDao.findList(image);
		design.setImages(images);
		return design;
	}
	
	public List<Design> findList(Design design) {
		return super.findList(design);
	}
	
	public Page<Design> findPage(Page<Design> page, Design design) {
		return super.findPage(page, design);
	}
	
	@Transactional(readOnly = false)
	public void save(Design design) {
		super.save(design);
	}
	
	@Transactional(readOnly = false)
	public void delete(Design design) {
		super.delete(design);
	}
	
}