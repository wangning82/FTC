/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.product;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;

/**
 * 商品规格DAO接口
 * @author wangqignxiang
 * @version 2017-05-25
 */
@MyBatisDao
public interface ProductSpecDao extends CrudDao<ProductSpec> {
	
}