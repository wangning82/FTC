package com.thinkgem.jeesite.modules.ftc.rest.order;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderWaybill;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderService;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderWaybillService;
import com.thinkgem.jeesite.modules.ftc.service.order.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by houyi on 2017/6/17 0017.
 */
@RestController
@RequestMapping(value = "/rest/ftc/order/")
@Api(value = "购物车", description = "购物车")
public class OrderRestController extends BaseRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderWaybillService waybillService;

    @Autowired
    private ShoppingCartService cartService;

    /**
     * 添加购物车
     *
     * @param token
     * @param productSpecNumber 商品规格编号
     * @param buyNumber
     * @return
     */
    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车")
    @RequestMapping(value = {"addToCart"}, method = {RequestMethod.POST, RequestMethod.GET})
    public RestResult addToCart(@RequestParam("token") String token, @RequestParam("productSpecNumber") String productSpecNumber, @RequestParam("buyNumber") int buyNumber) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            ProductSpec spec = new ProductSpec();
            spec.setProductSpecNumber(productSpecNumber);
            shoppingCart.setCustomer(customer);
            shoppingCart.setProductSpec(spec);
            shoppingCart.setBuyNumber(new BigDecimal(buyNumber));
            cartService.save(shoppingCart);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_ERROR, "用户访问超时，请重新登录！");
        }

    }

    /**
     * 查看购物车商品
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "查看购物车商品", notes = "查看购物车商品")
    @RequestMapping(value = {"productSpecInCart"}, method = {RequestMethod.POST, RequestMethod.GET})
    public RestResult productSpecInCart(@RequestParam("token") String token) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            ShoppingCart param = new ShoppingCart();
            param.setCustomer(customer);
            List<ShoppingCart> result = cartService.findList(param);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, result);
        } else {
            return new RestResult(CODE_ERROR, "用户访问超时，请重新登录！");
        }

    }

    /**
     * 生成订单
     *
     * @param token
     * @param cartIds 购物车标识
     * @return
     */
    @ApiOperation(value = "生成订单", notes = "生成订单")
    @RequestMapping(value = {"createOrder"}, method = {RequestMethod.POST})
    public RestResult createOrder(@RequestParam("token") String token, String[] cartIds) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            Order order = orderService.createOrder(customer, cartIds);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, order);
        } else {
            return new RestResult(CODE_ERROR, "用户访问超时，请重新登录！");
        }

    }

    /**
     * 订单列表
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "订单列表", notes = "订单列表")
    @RequestMapping(value = {"orderList"}, method = {RequestMethod.POST, RequestMethod.GET})
    public RestResult orderList(@RequestParam("token") String token) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            Order param = new Order();
            param.setCustomer(customer);
            List<Order> result = orderService.findList(param);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, result);
        } else {
            return new RestResult(CODE_ERROR, "用户访问超时，请重新登录！");
        }
    }

    /**
     * 取消订单
     *
     * @param token
     * @param orderNo
     * @return
     */
    @ApiOperation(value = "取消订单", notes = "取消订单")
    @RequestMapping(value = {"cancelOrder"}, method = {RequestMethod.POST, RequestMethod.GET})
    public RestResult cancelOrder(@RequestParam("token") String token, @RequestParam("orderNo") String orderNo) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            orderService.cancelOrder(customer, orderNo);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_ERROR, "用户访问超时，请重新登录！");
        }
    }

    /**
     * 订单确认
     *
     * @param token
     * @param orderNo
     * @param addressId
     * @param invoiceType
     * @param invoiceTitle
     * @param shipmentTime
     * @param shipmentType
     * @return
     */
    @ApiOperation(value = "订单确认", notes = "订单确认")
    @RequestMapping(value = {"confirmOrder"}, method = {RequestMethod.POST})
    public RestResult confirmOrder(@RequestParam("token") String token, @RequestParam("orderNo") String orderNo,
                                   @RequestParam("addressId") String addressId,
                                   @RequestParam("invoiceType") String invoiceType,
                                   @RequestParam("invoiceTitle") String invoiceTitle,
                                   @RequestParam("shipmentTime") String shipmentTime,
                                   @RequestParam("shipmentType") String shipmentType) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            orderService.confirmOrder(customer, orderNo, addressId, invoiceType, invoiceTitle, shipmentTime, shipmentType);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_ERROR, "用户访问超时，请重新登录！");
        }
    }

    /**
     * 支付订单
     *
     * @param token
     * @param orderNo 订单号
     * @param payType 支付方式
     * @return
     */
    @ApiOperation(value = "支付订单", notes = "支付订单")
    @RequestMapping(value = {"orderList"}, method = {RequestMethod.POST})
    public RestResult orderPay(@RequestParam("token") String token, @RequestParam("orderNo") String orderNo,
                               @RequestParam("payType") String payType) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            // TODO 第三方支付
            orderService.payOrder(customer, orderNo, payType);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_ERROR, "用户访问超时，请重新登录！");
        }

    }

    /**
     * 运单信息
     *
     * @param token
     * @param orderNo
     * @return
     */
    @ApiOperation(value = "运单信息", notes = "运单信息")
    @RequestMapping(value = {"showWaybill"}, method = {RequestMethod.POST, RequestMethod.GET})
    public RestResult showWaybill(@RequestParam("token") String token, @RequestParam("orderNo") String orderNo) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            OrderWaybill param = new OrderWaybill();
            Order order = new Order();
            order.setOrderNo(orderNo);
            param.setOrder(order);
            List<OrderWaybill> result = waybillService.findList(param);
            if (CollectionUtils.isNotEmpty(result)) {
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS, result.get(0));
            } else {
                return new RestResult(CODE_ERROR, "没有找到运单信息！");
            }
        } else {
            return new RestResult(CODE_ERROR, "用户访问超时，请重新登录！");
        }
    }

}
