/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.advert;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.dao.advert.AdvertDao;

/**
 * 广告位Service
 * @author wangqingxiang
 * @version 2017-05-21
 */
@Service
@Transactional(readOnly = true)
public class AdvertService extends CrudService<AdvertDao, Advert> {

	public Advert get(String id) {
		return super.get(id);
	}
	
	public List<Advert> findList(Advert advert) {
		return super.findList(advert);
	}
	
	public Page<Advert> findPage(Page<Advert> page, Advert advert) {
		return super.findPage(page, advert);
	}
	
	@Transactional(readOnly = false)
	public void save(Advert advert) {
		super.save(advert);
	}
	
	@Transactional(readOnly = false)
	public void delete(Advert advert) {
		super.delete(advert);
	}
	
}