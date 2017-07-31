/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.customer;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.constant.WishlistTypeEnum;
import com.thinkgem.jeesite.modules.ftc.dao.customer.CustomerDao;
import com.thinkgem.jeesite.modules.ftc.dao.customer.WishlistDao;
import com.thinkgem.jeesite.modules.ftc.dao.product.ProductDao;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Wishlist;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收藏Service
 * @author wangqingxiang
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class WishlistService extends CrudService<WishlistDao, Wishlist> {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ProductDao productDao;

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
		updateWishListCount(wishlist);
	}
	
	@Transactional(readOnly = false)
	public void delete(Wishlist wishlist) {
		super.delete(wishlist);
		updateWishListCount(wishlist);

	}
	private void updateWishListCount(Wishlist wishlist){
		if(WishlistTypeEnum.WISHLIST_SHOP.getValue().equals(wishlist.getType())){
			// 统计店铺收藏数量
			Customer customer = customerDao.get(wishlist.getCustomer().getId());
			customer.setWishlistNumber(dao.getShopWishlistNumber(wishlist.getDesigner().getId()));
			customer.preUpdate();
			customerDao.update(customer);
		}else{
			// 统计商品收藏数量
			Product product = productDao.get(wishlist.getProduct().getId());
			product.setFavouriteCount(dao.getProductWishlistNumber(wishlist.getProduct().getId()));
			product.preUpdate();
			productDao.update(product);
		}
	}
	
}