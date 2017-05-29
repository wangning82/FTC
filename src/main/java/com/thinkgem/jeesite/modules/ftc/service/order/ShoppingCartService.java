/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.modules.ftc.dao.order.ShoppingCartDao;

/**
 * 购物车Service
 * @author houyi
 * @version 2017-05-29
 */
@Service
@Transactional(readOnly = true)
public class ShoppingCartService extends CrudService<ShoppingCartDao, ShoppingCart> {

	public ShoppingCart get(String id) {
		return super.get(id);
	}
	
	public List<ShoppingCart> findList(ShoppingCart shoppingCart) {
		return super.findList(shoppingCart);
	}
	
	public Page<ShoppingCart> findPage(Page<ShoppingCart> page, ShoppingCart shoppingCart) {
		return super.findPage(page, shoppingCart);
	}
	
	@Transactional(readOnly = false)
	public void save(ShoppingCart shoppingCart) {
		super.save(shoppingCart);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShoppingCart shoppingCart) {
		super.delete(shoppingCart);
	}
	
}