package com.thinkgem.jeesite.modules.ftc.rest.order;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderService;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderShipmentService;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderWaybillService;
import com.thinkgem.jeesite.modules.ftc.service.order.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by houyi on 2017/6/17 0017.
 */
@RestController
@RequestMapping(value = "/rest/ftc/order/")
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
     * @param token
     * @param productSpecNumber
     * @param buyNumber
     * @return
     */
    @RequestMapping(value = {"addToCart"})
    public RestResult addToCart(String token, String productSpecNumber, int buyNumber) {
        ShoppingCart shoppingCart = new ShoppingCart();
        cartService.save(shoppingCart);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
    }
}
