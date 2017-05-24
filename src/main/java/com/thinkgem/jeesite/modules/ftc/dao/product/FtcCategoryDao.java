/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.product;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.Category;

/**
 * 分类DAO接口
 * @author wangqingxiang
 * @version 2017-05-19
 */
@MyBatisDao
public interface FtcCategoryDao extends TreeDao<Category> {
	
}