/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.customer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Wishlist;
import com.thinkgem.jeesite.modules.ftc.dao.customer.WishlistDao;

/**
 * 收藏Service
 * @author wangqingxiang
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class WishlistService extends CrudService<WishlistDao, Wishlist> {

	public Wishlist get(String id) {
		return super.get(id);
	}
	
	public List<Wishlist> findList(Wishlist wishlist) {
		return super.findList(wishlist);
	}
	
	public Page<Wishlist> findPage(Page<Wishlist> page, Wishlist wishlist) {
		return super.findPage(page, wishlist);
	}
	
	@Transactional(readOnly = false)
	public void save(Wishlist wishlist) {
		super.save(wishlist);
	}
	
	@Transactional(readOnly = false)
	public void delete(Wishlist wishlist) {
		super.delete(wishlist);
	}
	
}