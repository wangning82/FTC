package com.thinkgem.jeesite.modules.ftc.rest.order;

import com.egzosn.pay.common.api.PayConfigStorage;
import com.egzosn.pay.common.bean.PayMessage;
import com.egzosn.pay.common.bean.PayOrder;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.constant.FlagEnum;
import com.thinkgem.jeesite.modules.ftc.constant.OrderStatusEnum;
import com.thinkgem.jeesite.modules.ftc.convert.order.OrderConverter;
import com.thinkgem.jeesite.modules.ftc.convert.order.ShoppingCartConverter;
import com.thinkgem.jeesite.modules.ftc.dto.order.OrderConfirmDto;
import com.thinkgem.jeesite.modules.ftc.dto.order.OrderDto;
import com.thinkgem.jeesite.modules.ftc.dto.order.ShoppingCartDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.order.Order;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderProduct;
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderWaybill;
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderProductService;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderService;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderWaybillService;
import com.thinkgem.jeesite.modules.ftc.service.order.ShoppingCartService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import com.thinkgem.jeesite.modules.pay.entity.PayType;
import com.thinkgem.jeesite.modules.pay.service.ApyAccountService;
import com.thinkgem.jeesite.modules.pay.service.PayResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private ShoppingCartConverter shoppingCartConverter;

    @Autowired
    private ApyAccountService apyAccountService;

    @Autowired
    private ProductSpecService productSpecService;
    @Autowired
    private OrderProductService orderProductService;

    /**
     * 添加购物车
     *
     * @param token
     * @param productSpecNumber 商品规格编号
     * @param buyNumber
     * @return
     */
    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车")
    @RequestMapping(value = {"addToCart"}, method = {RequestMethod.POST})
    public RestResult addToCart(@RequestParam("token") String token,
                                @RequestParam("code") String productSpecNumber,
                                @RequestParam("count") int buyNumber) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            ProductSpec spec = new ProductSpec();
            spec.setProductSpecNumber(productSpecNumber);
            shoppingCart.setProductSpec(spec);
            shoppingCart.setCustomer(customer);
            shoppingCart.setBuyNumber(new BigDecimal(buyNumber));
            shoppingCart.setCheckStatus(FlagEnum.Flag_NO.getValue());
            cartService.save(shoppingCart);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    @ApiOperation(value = "从购物车移出商品", notes = "从购物车移出商品")
    @RequestMapping(value = {"removeFromCart"}, method = {RequestMethod.POST})
    public RestResult removeFromCart(@RequestParam("token") String token,
                                     @RequestParam("code") String productSpecNumber) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            ProductSpec spec = new ProductSpec();
            spec.setProductSpecNumber(productSpecNumber);
            shoppingCart.setCustomer(customer);
            cartService.delete(shoppingCart);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    /**
     * 查看购物车商品
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "查看购物车商品", notes = "查看购物车商品")
    @RequestMapping(value = {"productSpecInCart"}, method = {RequestMethod.POST})
    public RestResult productSpecInCart(@RequestParam("token") String token) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            ShoppingCart param = new ShoppingCart();
            param.setCustomer(customer);
            List<ShoppingCart> result = cartService.findList(param);
            if (CollectionUtils.isNotEmpty(result)) {
                for (int i = 0; i < result.size(); i++) {
                    ShoppingCart shoppingCart = result.get(i);
                    String productSpecId = shoppingCart.getProductSpec().getId();
                    if (StringUtils.isNotEmpty(productSpecId)) {
                        shoppingCart.setProductSpec(productSpecService.getWithImages(productSpecId));
                    }
                }
            }

            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, shoppingCartConverter.convertListFromModelToDto(result));
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }

    }

    /**
     * 生成订单
     *
     * @param token
     * @param shoppingCartDtoList 购物车
     * @return
     */
    @ApiOperation(value = "生成订单", notes = "生成订单")
    @RequestMapping(value = {"createOrder"}, method = {RequestMethod.POST})
    public RestResult createOrder(@RequestParam("token") String token, List<ShoppingCartDto> shoppingCartDtoList) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            Order order = orderService.createOrder(customer, shoppingCartConverter.convertListFromDtoToModel(shoppingCartDtoList));
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, orderConverter.convertModelToDto(order));
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }

    }

    /**
     * 订单列表
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "订单列表", notes = "订单列表")
    @RequestMapping(value = {"orderList"}, method = {RequestMethod.POST})
    public RestResult orderList(@RequestParam("token") String token, @RequestParam("type") String type,
                                HttpServletRequest request, HttpServletResponse response) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            Order param = new Order();
            param.setCustomer(customer);
            param.setOrderStatus(type);
            Page<Order> orderPage = orderService.findPage(new Page<Order>(request, response), param);

            List<Order> orderList=orderPage.getList();
            if(CollectionUtils.isNotEmpty(orderList)){
                for(Order order:orderList){
                    OrderProduct orderProduct=new OrderProduct();
                    orderProduct.setOrder(order);
                    List<OrderProduct> orderProducts=orderProductService.findList(orderProduct);
                    order.setOrderProductList(orderProducts);
                }
            }

            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, orderConverter.convertListFromModelToDto(orderList));
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    /**
     * 取消订单
     *
     * @param token
     * @param orderId
     * @return
     */
    @ApiOperation(value = "取消订单", notes = "取消订单，参数为订单号")
    @RequestMapping(value = {"cancelOrder"}, method = {RequestMethod.POST})
    public RestResult cancelOrder(@RequestParam("token") String token, @RequestParam("oid") String orderId) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            orderService.cancelOrder(customer, orderId);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    /**
     * 订单确认
     *
     * @param token
     * @param orderDto
     * @return
     */
    @ApiOperation(value = "订单确认", notes = "订单确认")
    @RequestMapping(value = {"confirmOrder"}, method = {RequestMethod.POST})
    public RestResult confirmOrder(@RequestParam("token") String token, OrderDto orderDto) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            Order order = orderConverter.convertDtoToModel(orderDto);
            Order confirmOrder = orderService.confirmOrder(customer, order.getOrderNo(), order.getAddress().getId(), order.getInvoiceTitle());
            OrderConfirmDto confirmDto = new OrderConfirmDto();
            confirmDto.setOrderId(confirmOrder.getId());
            confirmDto.setPayPrice(confirmOrder.getOrderAmount());
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, confirmDto);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    /**
     * 支付订单
     *
     * @param token
     * @param orderId 订单号
     * @param payType 支付方式
     * @return
     */
    @ApiOperation(value = "支付订单", notes = "支付订单")
    @RequestMapping(value = {"payOrder"}, method = {RequestMethod.POST})
    public RestResult orderPay(@RequestParam("token") String token, @RequestParam("oid") String orderId,
                               @RequestParam("pType") String payType) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            // 第三方支付

            //获取对应的支付账户操作工具（可根据账户id）
            PayResponse payResponse = apyAccountService.getPayResponse(1);
            Order order = orderService.get(orderId);
            order.setTradeNo(UUID.randomUUID().toString().replaceAll("-", ""));
            orderService.save(order);

            PayOrder payOrder = new PayOrder();
            payOrder.setOutTradeNo(order.getTradeNo());
            payOrder.setSubject("订单");
            payOrder.setPrice(order.getPayAmount());
            orderService.payOrder(order.getTradeNo(),payType);
//            payOrder.setTransactionType(PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType("APP"));

//            Map orderInfo = payResponse.getService().orderInfo(payOrder);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }

    }
    /**
     *
     *
     * @param token
     * @param orderId 订单号
     * @param payType 支付方式
     * @return
     */
    @ApiOperation(value = "支付完成", notes = "支付完成")
    @RequestMapping(value = {"finishPay"}, method = {RequestMethod.POST})
    public RestResult finishPay(@RequestParam("token") String token, @RequestParam("oid") String orderId,@RequestParam("pOid") String poid,
                               @RequestParam("pType") String payType) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            Order order=orderService.get(orderId);
            if(order==null){
                return new RestResult(CODE_NULL,  "没有找到订单");
            }
            if(order.getOrderStatus().equals(OrderStatusEnum.ORDER_STATUS_COMPLETE.getValue())){
                return new RestResult(CODE_SUCCESS, "支付完成");
            }else{
                PayResponse payResponse = apyAccountService.getPayResponse(1);
                Map<String, Object> result= payResponse.getService().query(order.getTradeNo(), order.getOrderNo());
                String status=(String)result.get("trade_state");
                if(!status.equals("SUCCESS")){
                    orderService.payOrder(order.getTradeNo(),payType);
                }
            }

            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }

    }

    /**
     * 支付回调地址
     *
     * @param request
     * @return 支付是否成功
     */
    @ApiOperation(value = "支付回调", notes = "回调接口")
    @RequestMapping(value = "payBack.json", method = {RequestMethod.POST})
    public String payBack(HttpServletRequest request) throws IOException {
        //根据账户id，获取对应的支付账户操作工具
        PayResponse payResponse = apyAccountService.getPayResponse(4);
        PayConfigStorage storage = payResponse.getStorage();
        //获取支付方返回的对应参数
        Map<String, Object> params = payResponse.getService().getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null == params) {
            return payResponse.getService().getPayOutMessage("fail", "失败").toMessage();
        }

        //校验
        if (payResponse.getService().verify(params)) {

            PayMessage message = new PayMessage(params, storage.getPayType(), storage.getMsgType().name());

            //交易状态
            if ("SUCCESS".equals(message.getPayMessage().get("result_code"))) {
                /////这里进行成功的处理
                orderService.payOrder((String) message.getPayMessage().get("out_trade_no"), "1");
                return payResponse.getService().getPayOutMessage("SUCCESS", "OK").toMessage();
            }
        }

        return payResponse.getService().getPayOutMessage("fail", "失败").toMessage();
    }

    /**
     * 运单信息
     *
     * @param token
     * @param orderNo
     * @return
     */
    @ApiOperation(value = "运单信息", notes = "运单信息")
    @RequestMapping(value = {"showWaybill"}, method = {RequestMethod.POST})
    public RestResult showWaybill(@RequestParam("token") String token, @RequestParam("order") String orderNo) {
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
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

}
