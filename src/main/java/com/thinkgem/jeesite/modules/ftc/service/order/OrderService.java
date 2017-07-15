/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.order;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.OrderNoGenerator;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.constant.*;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderDao;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderProductDao;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerIncomeDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerSoldDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.customer.CustomerBill;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderProduct;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderShipment;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.service.customer.AddressService;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerBillService;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单Service
 *
 * @author houyi
 * @version 2017-05-28
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends CrudService<OrderDao, Order> {

    @Autowired
    private OrderProductDao orderProductDao;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderShipmentService orderShipmentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerBillService customerBillService;

    @Autowired
    private ProductSpecService productSpecService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    public Order get(String id) {
        Order order = super.get(id);
        order.setOrderProductList(orderProductDao.findList(new OrderProduct(order)));
        return order;
    }

    public List<Order> findList(Order order) {
        return super.findList(order);
    }

    public Page<Order> findPage(Page<Order> page, Order order) {
        return super.findPage(page, order);
    }

    @Transactional(readOnly = false)
    public void save(Order order) {
        super.save(order);
        for (OrderProduct orderProduct : order.getOrderProductList()) {
            if (orderProduct.getId() == null) {
                continue;
            }
            if (OrderProduct.DEL_FLAG_NORMAL.equals(orderProduct.getDelFlag())) {
                if (StringUtils.isBlank(orderProduct.getId())) {
                    orderProduct.setOrder(order);
                    orderProduct.preInsert();
                    orderProductDao.insert(orderProduct);
                } else {
                    orderProduct.preUpdate();
                    orderProductDao.update(orderProduct);
                }
            } else {
                orderProductDao.delete(orderProduct);
            }
        }
    }

    /**
     * 取消订单
     *
     * @param customer
     * @param orderNo
     */
    @Transactional(readOnly = false)
    public void cancelOrder(Customer customer, String orderNo) {
        Order param = new Order();
        param.setCustomer(customer);
        param.setOrderNo(orderNo);
        List<Order> result = super.findList(param);
        if (CollectionUtils.isNotEmpty(result)) {
            Order order = result.get(0);
            order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_CANCELED.getValue());
            super.save(order);
        }
    }

    /**
     * 创建订单
     *
     * @param customer
     * @param shoppingCarts
     */
    @Transactional(readOnly = false)
    public Order createOrder(Customer customer, List<ShoppingCart> shoppingCarts) {
        Order order = new Order();
        order.setOrderNo(OrderNoGenerator.INSTANCE.nextId());
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_FORPAID.getValue());
        super.save(order);

        List<OrderProduct> orderProductList = new ArrayList<>();

        // 订单明细
        for (ShoppingCart shoppingCart : shoppingCarts) {
            OrderProduct orderProduct = cart2OrderProduct(order, shoppingCart);
            orderProduct.preInsert();
            orderProductDao.insert(orderProduct);
            orderProductList.add(orderProduct);

            shoppingCartService.delete(shoppingCart); // 删除购物车
        }

        order.setOrderProductList(orderProductList);
        return order;

    }

    /**
     * 购物车转订单
     * @param order
     * @param shoppingCart
     * @return
     */
    @Transactional(readOnly = false)
    public OrderProduct cart2OrderProduct(Order order, ShoppingCart shoppingCart){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProductNumber(shoppingCart.getProduct().getNumber());
        orderProduct.setName(shoppingCart.getProduct().getName());
        orderProduct.setPicImg(shoppingCart.getProduct().getPicImg());
        orderProduct.setProductSpecNumber(shoppingCart.getProductSpec().getProductSpecNumber());
        orderProduct.setProductSpecName(shoppingCart.getProductSpec().getSpec().getName());
        orderProduct.setProductPrice(new BigDecimal(shoppingCart.getProductSpec().getPrice()));
        orderProduct.setBuyNumber(shoppingCart.getBuyNumber());
        orderProduct.setProductAmount(new BigDecimal(shoppingCart.getProductSpec().getPrice()).multiply(shoppingCart.getBuyNumber()));
        orderProduct.setDesignBy(shoppingCart.getProduct().getDesignBy());
        orderProduct.setDesignPrice(shoppingCart.getProduct().getDesignPrice());
        orderProduct.setDesignAmount(shoppingCart.getProduct().getDesignPrice().multiply(shoppingCart.getBuyNumber()));
        orderProduct.setPrice(orderProduct.getProductAmount().add(orderProduct.getDesignAmount()));
        orderProduct.setCommentStatus(FlagEnum.Flag_NO.getValue());
        return orderProduct;
    }

    /**
     * 订单转购物车
     * @param orderProduct
     * @return
     */
    @Transactional(readOnly = false)
    public ShoppingCart orderProduct2Cart(OrderProduct orderProduct){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(orderProduct.getOrder().getCustomer());
        shoppingCart.setBuyNumber(orderProduct.getBuyNumber());

        Product productParam = new Product();
        productParam.setNumber(orderProduct.getProductNumber());
        List<Product> productList = productService.findList(productParam);
        if(CollectionUtils.isNotEmpty(productList)){
            shoppingCart.setProduct(productList.get(0));
        }

        ProductSpec productSpecParam = new ProductSpec();
        productSpecParam.setProductSpecNumber(orderProduct.getProductSpecNumber());
        List<ProductSpec> productSpecList = productSpecService.findList(productSpecParam);
        if(CollectionUtils.isNotEmpty(productSpecList)){
            shoppingCart.setProductSpec(productSpecList.get(0));
        }

        return shoppingCart;
    }

    /**
     * 订单确认
     *
     * @param customer
     * @param orderNo      订单号
     * @param addressId    收货地址标识
     * @param invoiceTitle 发票抬头
     */
    @Transactional(readOnly = false)
    public void confirmOrder(Customer customer, String orderNo, String addressId, String invoiceTitle) {
        Order param = new Order();
        param.setCustomer(customer);
        param.setOrderNo(orderNo);
        List<Order> result = this.findList(param);
        if (CollectionUtils.isNotEmpty(result)) {
            Order order = result.get(0);
            BigDecimal orderAmount = BigDecimal.ZERO; // 商品总价
            BigDecimal buyNumber = BigDecimal.ZERO;
            OrderProduct orderProductParam = new OrderProduct();
            orderProductParam.setOrder(order);
            List<OrderProduct> orderProductList = orderProductDao.findList(orderProductParam);
            for (OrderProduct orderProduct : orderProductList) {
                orderAmount = orderAmount.add(orderProduct.getPrice());
                buyNumber = buyNumber.add(orderProduct.getBuyNumber());
            }
            order.setBuyNumber(buyNumber);
            // order.setInvoiceType(invoiceType);
            order.setInvoiceTitle(invoiceTitle);
            // order.setShipmentTime(shipmentTime);
            // order.setShipmentType(shipmentType);
            // order.setOrderAmount(orderAmount.add(ShipmentAmountEnum.INSTANCE.getAmount(shipmentType))); // 订单金额 = 商品总价 + 物流费
            super.save(order);

            // 生成订单配送记录
            Address address = addressService.get(addressId);
            OrderShipment orderShipment = new OrderShipment();
            orderShipment.setOrder(order);
            orderShipment.setUserName(address.getUserName());
            orderShipment.setUserPhone(address.getUserPhone());
            orderShipment.setProvinceId(address.getProvince().getId());
            orderShipment.setProvinceName(address.getProvince().getName());
            orderShipment.setCityId(address.getCity().getId());
            orderShipment.setCityName(address.getCity().getName());
            orderShipment.setDistrictId(address.getDistrict().getId());
            orderShipment.setDistrictName(address.getDistrict().getName());
            orderShipment.setUserAdress(address.getUserAdress());
            orderShipment.setUserZipcode(Integer.parseInt(address.getUserZipcode()));
            orderShipmentService.save(orderShipment);
        }
    }

    /**
     * 订单支付后回调
     *
     * @param tradeNo
     * @param payType
     */
    @Transactional(readOnly = false)
    public void payOrder(String tradeNo, String payType) {
        Order param = new Order();
        param.setTradeNo(tradeNo);
        List<Order> result = this.findList(param);
        if (CollectionUtils.isNotEmpty(result)) {
            Order order = result.get(0);
            order.setPayType(payType);
            order.setPayAmount(order.getOrderAmount());
            order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_FORDELIVERYED.getValue());
            super.save(order);

            // 生成账单
            CustomerBill customerBill = new CustomerBill();
            customerBill.setCustomer(order.getCustomer());
            customerBill.setOrder(order);
            customerBill.setMoney(order.getPayAmount());
            customerBill.setType(BillTypeEnum.CONSUME.getValue());
            customerBill.setStatus(BillStatusEnum.ARRIVE.getValue());
            customerBillService.save(customerBill);

            // 消费额累加
            Customer user = customerService.get(order.getCustomer().getId());
            user.setAmount(user.getAmount().add(order.getPayAmount()));
            customerService.save(user);

            // 账户余额累加
            List<OrderProduct> orderProductList = orderProductDao.findList(new OrderProduct(order));
            for(OrderProduct orderProduct : orderProductList){
                Customer designer = customerService.get(orderProduct.getDesignBy().getId());
                designer.setBillBlance(designer.getBillBlance().add(orderProduct.getDesignAmount())); // 设计费
                customerService.save(designer);
            }

        }
    }

    /**
     * 营收统计
     * @param customer
     * @return
     */
    @Transactional(readOnly = false)
    public CustomerIncomeDto findIncomeByDesigner(Customer customer){
        CustomerIncomeDto incomeDto = new CustomerIncomeDto();
        Map<String, BigDecimal> today = dao.findIncomeToday(customer.getId());
        incomeDto.setDayReal(today.get("income"));
        incomeDto.setDayTotal(today.get("total"));

        Map<String, BigDecimal> month = dao.findIncomeMonth(customer.getId());
        incomeDto.setMonthReal(month.get("income"));
        incomeDto.setMonthTotal(month.get("total"));

        Map<String, BigDecimal> total = dao.findIncomeAll(customer.getId());
        incomeDto.setLifeReal(total.get("income"));
        incomeDto.setLifeTotal(total.get("total"));

        return incomeDto;
    }

    /**
     * 销售统计
     * @return
     */
    @Transactional(readOnly = false)
    public CustomerSoldDto findSoldInfo(String productId){
        CustomerSoldDto soldDto = new CustomerSoldDto();
        Product product = productService.get(productId);
        soldDto.setGood(productConverter.convertModelToDto(product));

        OrderProduct param = new OrderProduct();
        param.setProductNumber(product.getNumber());
        OrderProduct orderProduct = orderProductDao.findSoldInfo(param);
        soldDto.setCount(orderProduct.getBuyNumber());
        soldDto.setIncomeReal(orderProduct.getDesignAmount());
        soldDto.setIncomeTotal(orderProduct.getPrice());

        return soldDto;
    }

}