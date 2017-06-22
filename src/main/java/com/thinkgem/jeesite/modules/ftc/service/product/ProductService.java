/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.ProductNoGenerator;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.dao.product.DesignDao;
import com.thinkgem.jeesite.modules.ftc.dao.product.ImageDao;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductDao;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductSpecDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品Service
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Service
@Transactional(readOnly = true)
public class ProductService extends CrudService<ProductDao, Product> {

	@Autowired
	private ProductSpecDao productSpecDao;
	@Autowired
	private ImageDao imageDao;
	@Autowired
	private DesignDao designDao;
	public Product get(String id) {
		Product product= super.get(id);
		ProductSpec spec=new ProductSpec();
		spec.setProductId(product.getId());
		List<ProductSpec> specs=productSpecDao.findList(spec);
		product.setSpecs(specs);
		Image image=new Image();
		image.setProduct(product);
		List<Image> images=imageDao.findList(image);
		product.setImages(images);

		Design design=designDao.findByProductId(id);
		product.setDesign(design);
		return product;
	}
	
	public List<Product> findList(Product product) {
		return super.findList(product);
	}
	
	public Page<Product> findPage(Page<Product> page, Product product) {
		return super.findPage(page, product);
	}
	
	@Transactional(readOnly = false)
	public void save(Product product) {
		//保存商品时，检查设计，一个商品一个设计，如果商品是新增，要同时生成一个设计

		if (product.getIsNewRecord()){
			product.preInsert();
			dao.insert(product);

			if(product.getNumber()==null||product.getNumber().length()==0){
				product.setNumber(ProductNoGenerator.INSTANCE.nextId());
			}
			Design design=new Design();
			design.setCode(ProductNoGenerator.INSTANCE.nextId());
			design.setName(product.getName());
			design.setDesignStatus("0");;
			design.setPrice(0d);
			design.setProduct(product);
			design.preInsert();
			designDao.insert(design);

		}else{
			product.preUpdate();
			dao.update(product);
		}
		for (ProductSpec productSpec : product.getSpecs()){
			if (productSpec.getId() == null){
				continue;
			}
			if (SpecAttribute.DEL_FLAG_NORMAL.equals(productSpec.getDelFlag())){
				if (StringUtils.isBlank(productSpec.getId())){
					productSpec.setProductId(product.getId());
					productSpec.preInsert();
					productSpecDao.insert(productSpec);
				}else{
					productSpec.preUpdate();
					productSpecDao.update(productSpec);
				}
			}else{
				productSpecDao.delete(productSpec);
			}
		}
		//将设计保存到图片上
		Design design=product.getDesign();
		if(design==null){
			design=designDao.findByProductId(product.getId());
		}
		for (Image  image : product.getImages()){
			if (image.getId() == null){
				continue;
			}
			if (SpecAttribute.DEL_FLAG_NORMAL.equals(image.getDelFlag())){
				if (StringUtils.isBlank(image.getId())){
					image.setProduct(product);
					image.setDesign(design);
					image.preInsert();
					imageDao.insert(image);
				}else{
					image.preUpdate();
					imageDao.update(image);
				}
			}else{
				imageDao.delete(image);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Product product) {
		super.delete(product);
		//将设计保存到图片上
		Design design=product.getDesign();
		if(design==null){
			design=designDao.findByProductId(product.getId());
		}
		designDao.delete(design);
	}
	
}