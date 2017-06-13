/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.advert;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;

/**
 * 广告DAO接口
 * @author wangqingxiang
 * @version 2017-06-03
 */
@MyBatisDao
public interface AdvertDao extends CrudDao<Advert> {
	Advert getByCode(String code);
}