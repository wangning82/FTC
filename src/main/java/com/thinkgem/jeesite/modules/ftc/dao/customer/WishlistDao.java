/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.customer;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Wishlist;

/**
 * 收藏DAO接口
 * @author wangqingxiang
 * @version 2017-07-06
 */
@MyBatisDao
public interface WishlistDao extends CrudDao<Wishlist> {
    /**
     * 获取店铺收藏数量
     * @param shopId
     * @return
     */
	int getShopWishlistNumber(String shopId);

    /**
     * 获取产品收藏数量
     * @param productId
     * @return
     */
	int getProductWishlistNumber(String productId);
}
