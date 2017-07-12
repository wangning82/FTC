/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.ProductNoGenerator;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.dao.product.DesignDao;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductImageDao;
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
	private ProductImageDao productImageDao;
	@Autowired
	private DesignDao designDao;
	public Product get(String id) {
		Product product= super.get(id);

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
		//保存商品时

		if (product.getIsNewRecord()){
			product.preInsert();
			if(product.getNumber()==null||product.getNumber().length()==0){
				product.setNumber(ProductNoGenerator.INSTANCE.nextId());
			}
			dao.insert(product);

		}else{
			product.preUpdate();
			dao.update(product);
		}
		//保存规格
		if(product.getSpecs()!=null&&product.getSpecs().size()>0){
			for (ProductSpec productSpec : product.getSpecs()){
				if (productSpec.getId() == null){
					continue;
				}
				if (SpecAttribute.DEL_FLAG_NORMAL.equals(productSpec.getDelFlag())){
					if (StringUtils.isBlank(productSpec.getId())){
						productSpec.setProduct(product);
						productSpec.setProductSpecNumber(ProductNoGenerator.INSTANCE.nextId());
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
		}

		//保存图片
		if(product.getImages()!=null&&product.getImages().size()>0){
			for (ProductImage  image : product.getImages()){
				if (image.getId() == null){
					continue;
				}
				if (SpecAttribute.DEL_FLAG_NORMAL.equals(image.getDelFlag())){
					if (StringUtils.isBlank(image.getId())){
						image.setProduct(product);
						image.preInsert();
						productImageDao.insert(image);
					}else{
						image.preUpdate();
						productImageDao.update(image);
					}
				}else{
					productImageDao.delete(image);
				}
			}
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(Product product) {
		super.delete(product);
	}
	@Transactional(readOnly = false)
	public void upShelve(Product product){
		dao.upShelve(product);
	}
	@Transactional(readOnly = false)
	public void downShelve(Product product){
		dao.downShelve(product);
	}
	@Transactional(readOnly = false)
	public void addHot(Product product){
		dao.addHot(product);
	}

	/**
	 * 卖出产品列表
	 * @param page
	 * @param product
	 * @return
	 */
	public Page<Product> findSoldPage(Page<Product> page, Product product){
		product.setPage(page);
		page.setList(dao.findSoldList(product));
		return page;
	}
	
}