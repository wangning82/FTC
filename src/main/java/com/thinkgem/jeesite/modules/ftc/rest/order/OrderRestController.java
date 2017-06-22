package com.thinkgem.jeesite.modules.ftc.rest.order;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderService;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderShipmentService;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderWaybillService;
import com.thinkgem.jeesite.modules.ftc.service.order.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private OrderShipmentService shipmentService;

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
        ShoppingCart shoppingCart = new ShoppingCart();
        Customer customer = findCustomerByToken(token);
        ProductSpec spec = new ProductSpec();
        spec.setProductSpecNumber(productSpecNumber);
        shoppingCart.setCustomer(customer);
        shoppingCart.setProductSpec(spec);
        shoppingCart.setBuyNumber(new BigDecimal(buyNumber));
        cartService.save(shoppingCart);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
    }

    /**
     * 查看购物车商品
     * @param token
     * @return
     */
    @ApiOperation(value = "查看购物车商品", notes = "查看购物车商品")
    @RequestMapping(value = {"productSpecInCart"}, method = {RequestMethod.POST, RequestMethod.GET})
    public RestResult productSpecInCart(@RequestParam("token") String token) {
        Customer customer = findCustomerByToken(token);
        ShoppingCart param = new ShoppingCart();
        param.setCustomer(customer);
        List<ShoppingCart> result = cartService.findList(param);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, result);
    }

    /**
     * 生成订单
     * @param token
     * @param cartIds 购物车标识
     * @return
     */
    @ApiOperation(value = "生成订单", notes = "生成订单")
    @RequestMapping(value = {"createOrder"}, method = {RequestMethod.POST})
    public RestResult createOrder(@RequestParam("token") String token, String[] cartIds) {

        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
    }

    /**
     * 订单列表
     * @param token
     * @return
     */
    @ApiOperation(value = "订单列表", notes = "订单列表")
    @RequestMapping(value = {"orderList"}, method = {RequestMethod.POST, RequestMethod.GET})
    public RestResult orderList(@RequestParam("token") String token) {

        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
    }

    /**
     * 支付订单
     * @param token
     * @param orderNo 订单号
     * @param payType 支付方式
     * @return
     */
    @ApiOperation(value = "支付订单", notes = "支付订单")
    @RequestMapping(value = {"orderList"}, method = {RequestMethod.POST})
    public RestResult orderPay(@RequestParam("token") String token, @RequestParam("orderNo") String orderNo, @RequestParam("payType") String payType) {

        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
    }

}
