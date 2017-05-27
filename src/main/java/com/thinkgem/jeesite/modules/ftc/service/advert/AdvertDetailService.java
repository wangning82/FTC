/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.advert;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.advert.AdvertDetail;
import com.thinkgem.jeesite.modules.ftc.dao.advert.AdvertDetailDao;

/**
 * 广告Service
 * @author wangqingxiang
 * @version 2017-05-21
 */
@Service
@Transactional(readOnly = true)
public class AdvertDetailService extends CrudService<AdvertDetailDao, AdvertDetail> {

	public AdvertDetail get(String id) {
		return super.get(id);
	}
	
	public List<AdvertDetail> findList(AdvertDetail advertDetail) {
		return super.findList(advertDetail);
	}
	
	public Page<AdvertDetail> findPage(Page<AdvertDetail> page, AdvertDetail advertDetail) {
		return super.findPage(page, advertDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(AdvertDetail advertDetail) {
		super.save(advertDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(AdvertDetail advertDetail) {
		super.delete(advertDetail);
	}
	
}