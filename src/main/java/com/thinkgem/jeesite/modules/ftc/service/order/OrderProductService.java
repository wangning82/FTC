/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.order;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderProductDao;
import com.thinkgem.jeesite.modules.ftc.dao.order.ShoppingCartDao;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderProduct;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车Service
 * @author houyi
 * @version 2017-05-29
 */
@Service
@Transactional(readOnly = true)
public class OrderProductService extends CrudService<OrderProductDao, OrderProduct> {


}