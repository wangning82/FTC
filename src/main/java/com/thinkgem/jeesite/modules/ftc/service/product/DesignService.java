/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.ProductNoGenerator;
import com.thinkgem.jeesite.modules.ftc.dao.product.*;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 设计Service
 * @author wangqingxiang
 * @version 2017-05-21
 */
@Service
@Transactional(readOnly = true)
public class DesignService extends CrudService<DesignDao, Design> {

	@Autowired
	private DesignDetailDao designDetailDao;
	@Autowired
	private ProductDao productDao;
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
		super.save(design);
		List<DesignDetail> designDetails=design.getDetails();
		//保存商品图片
		for(DesignDetail detail:designDetails){
			if(detail.getIsNewRecord()){
				detail.setId(IdGen.uuid());
				detail.setDesign(design);
				detail.setCustomer(design.getCustomer());
				detail.setCreateDate(new Date());
				designDetailDao.insert(detail);
			}else{
				designDetailDao.update(detail);
			}

		}
//		List<ProductSpec> specs=design.getModel().getSpecs();

//		//获取modelid,复制model信息为新的商品
//		String modelId=design.getModel().getId();
//		//复制product
//		Product product=productDao.get(modelId);
//		product.setId(IdGen.uuid());
//		product.setNumber(ProductNoGenerator.INSTANCE.nextId());
//		productDao.insert(product);
//
//		design.setCode(ProductNoGenerator.INSTANCE.nextId());
//		design.setDesignStatus("0");
//		//保存设计
//		super.save(design);
//
//		//保存商品图片
//		for(Image image:images){
//			image.setId(IdGen.uuid());
//			image.setProduct(product);
//			image.setCreateBy(null);
//			image.setCreateDate(new Date());
//			image.setDesign(design);
//			imageDao.insert(image);
//		}
//
//
//		//保存图片到规格小图
//		for(ProductSpec spec:specs){
//			ProductSpec modelSpec=productSpecDao.get(spec.getId());
//			ProductSpec productSpec=new ProductSpec();
//			productSpec.setProductId(product.getId());
//			productSpec.setSpec(modelSpec.getSpec());
//			productSpec.setProductSpecNumber(ProductNoGenerator.INSTANCE.nextId());
//			productSpec.setPrice(modelSpec.getPrice());
//			productSpec.setPicImg(spec.getPicImg());
//			productSpec.setScore(modelSpec.getScore());
//			productSpec.setStock(modelSpec.getStock());
//			productSpec.setId(IdGen.uuid());
//			productSpecDao.insert(productSpec);
//		}

	}
}