/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductDao;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductSpecDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;
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
	public Product get(String id) {
		Product product= super.get(id);
		ProductSpec spec=new ProductSpec();
		spec.setProductId(product.getId());
		List<ProductSpec> specs=productSpecDao.findList(spec);
		product.setSpecs(specs);
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
		super.save(product);
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
	}
	
	@Transactional(readOnly = false)
	public void delete(Product product) {
		super.delete(product);
	}
	
}