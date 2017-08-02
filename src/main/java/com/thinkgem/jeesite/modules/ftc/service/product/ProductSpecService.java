/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductImageDao;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductSpecDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 商品规格Service
 *
 * @author wangqignxiang
 * @version 2017-05-25
 */
@Service
@Transactional(readOnly = true)
public class ProductSpecService extends CrudService<ProductSpecDao, ProductSpec> {

    @Autowired
    private ProductImageDao productImageDao;

    public ProductSpec get(String id) {
        return super.get(id);
    }

    public ProductSpec getWithImages(String id) {
        ProductSpec spec = super.get(id);
        if (spec != null) {
            ProductImage image = new ProductImage();
            image.setProductSpec(spec);
            spec.setImages(productImageDao.findList(image));
        }
        return spec;

    }

    public List<ProductSpec> findList(ProductSpec productSpec) {
        return super.findList(productSpec);
    }
    public List<ProductSpec> findListWithImage(ProductSpec productSpec) {
        List<ProductSpec> specs=findList(productSpec);
        if(!CollectionUtils.isEmpty(specs)){
            for(int i=0;i<specs.size();i++){
                ProductImage image=new ProductImage();
                image.setProductSpec(specs.get(i));
                List<ProductImage> productImages=productImageDao.findList(image);
                specs.get(i).setImages(productImages);
            }

        }
        return specs;
    }

    public Page<ProductSpec> findPage(Page<ProductSpec> page, ProductSpec productSpec) {
        return super.findPage(page, productSpec);
    }

    @Transactional(readOnly = false)
    public void save(ProductSpec productSpec) {
        super.save(productSpec);
        List<ProductImage> images = productSpec.getImages();
        if (images != null && images.size() > 0) {
            for (ProductImage image : images) {
                if (image.getIsNewRecord()) {
                    image.preInsert();
                    image.setProductSpec(productSpec);
                    productImageDao.insert(image);
                } else {
                    image.preUpdate();
                    productImageDao.update(image);
                }
            }
        }

    }

    @Transactional(readOnly = false)
    public void delete(ProductSpec productSpec) {
        super.delete(productSpec);
    }

    public List<ProductSpec> findForRest(Page<ProductSpec> page, ProductSpec productSpec) {
        return dao.findForRest(productSpec);
    }

    /**
     * 卖出产品列表
     *
     * @param page
     * @param productSpec
     * @return
     */
    public Page<ProductSpec> findSoldPage(Page<ProductSpec> page, ProductSpec productSpec) {
        productSpec.setPage(page);
        page.setList(dao.findSoldList(productSpec));
        return page;
    }

}