/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.advert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.dao.advert.AdvertDao;
import com.thinkgem.jeesite.modules.ftc.entity.advert.AdvertDetail;
import com.thinkgem.jeesite.modules.ftc.dao.advert.AdvertDetailDao;

/**
 * 广告Service
 * @author wangqingxiang
 * @version 2017-06-03
 */
@Service
@Transactional(readOnly = true)
public class AdvertService extends CrudService<AdvertDao, Advert> {

	@Autowired
	private AdvertDetailDao advertDetailDao;
	
	public Advert get(String id) {
		Advert advert = super.get(id);
		advert.setAdvertDetailList(advertDetailDao.findList(new AdvertDetail(advert)));
		return advert;
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
		for (AdvertDetail advertDetail : advert.getAdvertDetailList()){
			if (advertDetail.getId() == null){
				continue;
			}
			if (AdvertDetail.DEL_FLAG_NORMAL.equals(advertDetail.getDelFlag())){
				if (StringUtils.isBlank(advertDetail.getId())){
					advertDetail.setAdvert(advert);
					advertDetail.preInsert();
					advertDetailDao.insert(advertDetail);
				}else{
					advertDetail.preUpdate();
					advertDetailDao.update(advertDetail);
				}
			}else{
				advertDetailDao.delete(advertDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Advert advert) {
		super.delete(advert);
		advertDetailDao.delete(new AdvertDetail(advert));
	}
	
}