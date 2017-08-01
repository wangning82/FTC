/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.product;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;

import java.util.List;

/**
 * 商品DAO接口
 * @author wangqingxiang
 * @version 2017-05-19
 */
@MyBatisDao
public interface ProductDao extends CrudDao<Product> {
    public void upShelve(Product product);
    public void downShelve(Product product);
    public void addHot(Product product);
    public List<Product> findBySpecCode(String code);

}