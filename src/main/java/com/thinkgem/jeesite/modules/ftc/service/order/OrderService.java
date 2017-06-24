/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.OrderNoGenerator;
import com.thinkgem.jeesite.modules.ftc.constant.FlagEnum;
import com.thinkgem.jeesite.modules.ftc.constant.OrderStatusEnum;
import com.thinkgem.jeesite.modules.ftc.constant.ShipmentAmountEnum;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.customer.CustomerBill;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderShipment;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.modules.ftc.service.customer.AddressService;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerBillService;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderDao;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderProduct;
import com.thinkgem.jeesite.modules.ftc.dao.order.OrderProductDao;

/**
 * 订单Service
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
		for (OrderProduct orderProduct : order.getOrderProductList()){
			if (orderProduct.getId() == null){
				continue;
			}
			if (OrderProduct.DEL_FLAG_NORMAL.equals(orderProduct.getDelFlag())){
				if (StringUtils.isBlank(orderProduct.getId())){
					orderProduct.setOrder(order);
					orderProduct.preInsert();
					orderProductDao.insert(orderProduct);
				}else{
					orderProduct.preUpdate();
					orderProductDao.update(orderProduct);
				}
			}else{
				orderProductDao.delete(orderProduct);
			}
		}
	}

	/**
	 * 取消订单
	 * @param order
	 */
	@Transactional(readOnly = false)
	public void cancelOrder(Order order) {
		order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_CANCELED.getValue());
		super.save(order);
	}

	/**
	 * 创建订单
	 * @param customer
	 * @param shoppingCartIds
	 */
	@Transactional(readOnly = false)
	public void createOrder(Customer customer, String[] shoppingCartIds){
		Order order = new Order();
		order.setOrderNo(OrderNoGenerator.INSTANCE.nextId());
		order.setCustomer(customer);
		order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_FORPAID.getValue());
		super.save(order);

		// 订单明细
		for(String shoppingCartId : shoppingCartIds){
			ShoppingCart shoppingCart = shoppingCartService.get(shoppingCartId);

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
			orderProduct.setDesign(shoppingCart.getProduct().getDesign());
			orderProduct.setDesignPrice(new BigDecimal(shoppingCart.getProduct().getDesign().getPrice()));
			orderProduct.setDesignAmount(new BigDecimal(shoppingCart.getProduct().getDesign().getPrice()).multiply(shoppingCart.getBuyNumber()));
			orderProduct.setPrice(orderProduct.getProductAmount().add(orderProduct.getDesignAmount()));
			orderProduct.setCommentStatus(FlagEnum.Flag_NO.getValue());
			orderProduct.preInsert();
			orderProductDao.insert(orderProduct);
			shoppingCartService.delete(shoppingCart); // 删除购物车
		}

	}

	/**
	 * 订单确认
	 * @param customer
	 * @param orderNo
	 * @param addressId
	 * @param invoiceType
	 * @param invoiceTitle
	 * @param shipmentTime 配送时间
	 * @param shipmentType 配送方式
	 */
	@Transactional(readOnly = false)
	public void confirmOrder(Customer customer, String orderNo, String addressId, String invoiceType, String invoiceTitle, String shipmentTime, String shipmentType) {
		Order param = new Order();
		param.setOrderNo(orderNo);
		List<Order> result = this.findList(param);
		if(CollectionUtils.isNotEmpty(result)){
			Order order = result.get(0);
			BigDecimal orderAmount = BigDecimal.ZERO;
			BigDecimal buyNumber = BigDecimal.ZERO;
			OrderProduct orderProductParam = new OrderProduct();
			orderProductParam.setOrder(order);
			List<OrderProduct> orderProductList = orderProductDao.findList(orderProductParam);
			for(OrderProduct orderProduct : orderProductList){
				orderAmount = orderAmount.add(orderProduct.getPrice());
				buyNumber = buyNumber.add(orderProduct.getBuyNumber());
			}
			order.setBuyNumber(buyNumber);
			order.setInvoiceType(invoiceType);
			order.setInvoiceTitle(invoiceTitle);
			order.setShipmentTime(shipmentTime);
			order.setShipmentType(shipmentType);
			order.setOrderAmount(orderAmount.add(ShipmentAmountEnum.INSTANCE.getAmount(shipmentType)));
			super.save(order);

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

	@Transactional(readOnly = false)
	public void payOrder(Customer customer, String orderNo, String payType){
		Order param = new Order();
		param.setOrderNo(orderNo);
		List<Order> result = this.findList(param);
		if(CollectionUtils.isNotEmpty(result)){
			Order order = result.get(0);
			order.setPayAmount(order.getOrderAmount());
			order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_FORDELIVERYED.getValue());
			super.save(order);

			CustomerBill customerBill = new CustomerBill();
			customerBill.setCustomer(customer);

		}
	}


}