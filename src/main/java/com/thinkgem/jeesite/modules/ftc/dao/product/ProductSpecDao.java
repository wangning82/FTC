/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.product;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;

import java.util.List;

/**
 * 商品规格DAO接口
 *
 * @author wangqignxiang
 * @version 2017-05-25
 */
@MyBatisDao
public interface ProductSpecDao extends CrudDao<ProductSpec> {
    public List<ProductSpec> findForRest(ProductSpec spec);

    /**
     * 卖出产品列表
     *
     * @param entity
     * @return
     */
    List<ProductSpec> findSoldList(ProductSpec entity);

}