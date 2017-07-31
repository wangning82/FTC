/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.ImageUtils;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.modules.ftc.constant.ImgSourceEnum;
import com.thinkgem.jeesite.modules.ftc.dao.product.*;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import com.thinkgem.jeesite.modules.ftc.entity.product.DesignDetail;
import com.thinkgem.jeesite.modules.ftc.entity.product.DesignSeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 设计Service
 * @author wangqingxiang
 * @version 2017-05-21
 */
@Service
@Transactional(readOnly = true)
public class DesignService extends CrudService<DesignDao, Design> {
	public static PropertiesLoader loader = new PropertiesLoader("jeesite.properties");

	@Autowired
	private DesignDetailDao designDetailDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private DesignSeedDao designSeedDao;
	@Autowired
	private ProductSpecDao productSpecDao;
	public Design get(String id) {
		return super.get(id);
	}
	public Design findByProductId(String productId){
		return dao.findByProductId(productId);
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

	@Transactional(readOnly = false)
	public void saveForRest(Design design){
		String serverName = loader.getProperty("serverName");
		if(design.getPicImg()!=null&&design.getPicImg().length()>200){
			String url=ImageUtils.generateImg(design.getPicImg(),
					design.getCustomer().getId(),
					ImgSourceEnum.IMG_SOURCE_SHEJI.getValue());
			design.setPicImg(serverName + "/upload" +
					""+url);
		}
		super.save(design);
		List<DesignDetail> designDetails=design.getDetails();
		//保存设计明细
		for(DesignDetail detail:designDetails){
			if(detail.getIsNewRecord()){
				detail.setId(IdGen.uuid());
				detail.setDesign(design);
				detail.setCustomer(design.getCustomer());
				detail.setCreateDate(new Date());
				detail.preInsert();
				designDetailDao.insert(detail);
			}else{
				detail.preUpdate();
				designDetailDao.update(detail);
			}

		}
		//保存seed
		for (DesignSeed seed : design.getSeeds()) {
			if (seed.getImgNailUrl() != null&&seed.getImgNailUrl().length()>200) {
				String url = ImageUtils.generateImg(seed.getImgNailUrl(), design.getCustomer().getId(), ImgSourceEnum.IMG_SOURCE_SUCAI.getValue());
				seed.setImgNailUrl(serverName + "/upload" + url);
				seed.setImgUrl(serverName + "/upload" + url);
			}
			if (seed.getIsNewRecord()) {
				seed.setId(IdGen.uuid());
				seed.setDesign(design);
				seed.setCreateDate(new Date());
				seed.preInsert();
				designSeedDao.insert(seed);
			} else {
				seed.preUpdate();
				designSeedDao.update(seed);
			}

		}
	}

	public List<DesignSeed> findSeeds(Design design) {
		DesignSeed seed=new DesignSeed();
		seed.setDesign(design);
		return designSeedDao.findList(seed);
	}
}