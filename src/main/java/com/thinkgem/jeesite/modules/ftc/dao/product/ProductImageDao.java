/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.product;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;

/**
 * 图片DAO接口
 * @author wangqingxiang
 * @version 2017-06-01
 */
@MyBatisDao
public interface ProductImageDao extends CrudDao<ProductImage> {
	
}