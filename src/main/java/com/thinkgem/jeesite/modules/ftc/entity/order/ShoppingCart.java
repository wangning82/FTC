/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.order;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * 购物车Entity
 *
 * @author houyi
 * @version 2017-05-29
 */
public class ShoppingCart extends DataEntity<ShoppingCart> {

    private static final long serialVersionUID = 1L;
    private ProductSpec productSpec = new ProductSpec();   // 商品规格编号
    private Customer customer = new Customer();        // 顾客ID
    private BigDecimal buyNumber;     // 购买数量
    private String checkStatus;       // 购物车状态
    private Product product = new Product();

    public ShoppingCart() {
        super();
    }

    public ShoppingCart(String id) {
        super(id);
    }

    public ProductSpec getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(ProductSpec productSpec) {
        this.productSpec = productSpec;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(BigDecimal buyNumber) {
        this.buyNumber = buyNumber;
    }

    @Length(min = 0, max = 2, message = "购物车状态长度必须介于 0 和 2 之间")
    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}