package com.thinkgem.jeesite.modules.ftc.rest.order;

import com.egzosn.pay.common.api.PayConfigStorage;
import com.egzosn.pay.common.bean.PayMessage;
import com.egzosn.pay.common.bean.PayOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.order.*;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.service.order.*;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import com.thinkgem.jeesite.modules.pay.service.ApyAccountService;
import com.thinkgem.jeesite.modules.pay.service.PayResponse;
import com.thinkgem.jeesite.modules.sys.entity.Area;
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
    @Autowired
    private OrderShipmentService orderShipmentService;

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
     * @param orderJson 购物车
     * @return
     */
    @ApiOperation(value = "生成订单", notes = "生成订单")
    @RequestMapping(value = {"createOrder"}, method = {RequestMethod.POST})
    public RestResult createOrder(@RequestParam("token") String token, @RequestParam("order") String orderJson) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                OrderDto orderDto = objectMapper.readValue(orderJson, OrderDto.class);

                BigDecimal freight = orderService.createOrder(customer, shoppingCartConverter.convertListFromDtoToModel(orderDto.getBags()));
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS, freight);
            } catch (Exception e) {
                e.printStackTrace();
                return new RestResult(CODE_ERROR, e.getMessage());
            }

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
            if(type!=null&&!type.equals("0")){
                param.setOrderStatus(type);
            }

            param.setCustomer(customer);
            Page<Order> orderPage = orderService.findPage(new Page<Order>(request, response), param);

            List<Order> orderList = orderPage.getList();
            if (CollectionUtils.isNotEmpty(orderList)) {
                for (Order order : orderList) {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(order);
                    List<OrderProduct> orderProducts = orderProductService.findList(orderProduct);
                    order.setOrderProductList(orderProducts);
                    OrderShipment shipment=new OrderShipment();
                    shipment.setOrder(order);
                    List<OrderShipment> orderShipment=orderShipmentService.findList(shipment);
                    if (CollectionUtils.isNotEmpty(orderShipment)) {

                        Address address=new Address();
                        address.setCustomer(customer);
                        address.setUserAdress(orderShipment.get(0).getUserAdress());
                        address.setDistrict(new Area(null,orderShipment.get(0).getDistrictName()));
                        order.setAddress(address);
                    }
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
     * @param orderNo
     * @return
     */
    @ApiOperation(value = "取消订单", notes = "取消订单，参数为订单号")
    @RequestMapping(value = {"cancelOrder"}, method = {RequestMethod.POST})
    public RestResult cancelOrder(@RequestParam("token") String token, @RequestParam("oid") String orderNo) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            orderService.cancelOrder(customer, orderNo);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    /**
     * 订单确认
     *
     * @param token
     * @param orderJson
     * @return
     */
    @ApiOperation(value = "订单确认", notes = "订单确认")
    @RequestMapping(value = {"confirmOrder"}, method = {RequestMethod.POST})
    public RestResult confirmOrder(@RequestParam("token") String token, @RequestParam("order") String orderJson) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                OrderDto orderDto = objectMapper.readValue(orderJson, OrderDto.class);
                Order order = orderConverter.convertDtoToModel(orderDto);
                Order confirmOrder = orderService.confirmOrder(customer, order.getOrderNo(), order.getAddress().getId(), order.getInvoiceTitle());

                return new RestResult(CODE_SUCCESS, MSG_SUCCESS, confirmOrder.getId());
            } catch (Exception e) {
                e.printStackTrace();
                return new RestResult(CODE_ERROR, e.getMessage());
            }
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
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
    @RequestMapping(value = {"payOrder"}, method = {RequestMethod.POST})
    public RestResult orderPay(@RequestParam("token") String token, @RequestParam("oid") String orderNo,
                               @RequestParam("pType") String payType) {
        Customer customer = findCustomerByToken(token);
        PayResult result2=null;
        if (customer != null) {
            // 第三方支付

            //获取对应的支付账户操作工具（可根据账户id）
            PayResponse payResponse = apyAccountService.getPayResponse(1);

            Order param = new Order();
            param.setCustomer(customer);
            param.setId(orderNo);
            List<Order> result = orderService.findList(param);
            if (CollectionUtils.isNotEmpty(result)) {
                Order order = result.get(0);
                order.setTradeNo(UUID.randomUUID().toString().replaceAll("-", ""));
                orderService.save(order);

                PayOrder payOrder = new PayOrder();
                payOrder.setOutTradeNo(order.getTradeNo());
                payOrder.setSubject("订单");
                payOrder.setPrice(order.getPayAmount());
                orderService.payOrder(order.getTradeNo(), payType);
                 result2=new PayResult();
                result2.setOid(order.getOrderNo());
                result2.setpOid("ddddddd");
                result2.setPrice(order.getPayAmount());
                result2.setpType(payType);
//            payOrder.setTransactionType(PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType("APP"));
//            Map orderInfo = payResponse.getService().orderInfo(payOrder);
            }

            return new RestResult(CODE_SUCCESS, MSG_SUCCESS,result2);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }

    }
    class PayResult{
        private String oid;
        private String pType;
        private String pOid;
        private BigDecimal price;

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getpType() {
            return pType;
        }

        public void setpType(String pType) {
            this.pType = pType;
        }

        public String getpOid() {
            return pOid;
        }

        public void setpOid(String pOid) {
            this.pOid = pOid;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

    /**
     * @param token
     * @param orderNo 订单号
     * @param payType 支付方式
     * @return
     */
    @ApiOperation(value = "支付完成", notes = "支付完成")
    @RequestMapping(value = {"finishPay"}, method = {RequestMethod.POST})
    public RestResult finishPay(@RequestParam("token") String token, @RequestParam("oid") String orderNo, @RequestParam("pOid") String poid,
                                @RequestParam("pType") String payType) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            Order param = new Order();
            param.setCustomer(customer);
            param.setOrderNo(orderNo);
            List<Order> result = orderService.findList(param);
            if (CollectionUtils.isNotEmpty(result)) {
                Order order = result.get(0);
                if (order.getOrderStatus().equals(OrderStatusEnum.ORDER_STATUS_COMPLETE.getValue())) {
                    return new RestResult(CODE_SUCCESS, "支付完成");
                } else {
//                PayResponse payResponse = apyAccountService.getPayResponse(1);
//                Map<String, Object> result= payResponse.getService().query(order.getTradeNo(), order.getOrderNo());
//                String status=(String)result.get("trade_state");
//                if(!status.equals("SUCCESS")){
//                    orderService.payOrder(order.getTradeNo(),payType);
//                }
                }
            } else {
                return new RestResult(CODE_NULL, "没有找到订单");
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
    public RestResult showWaybill(@RequestParam("token") String token, @RequestParam("oid") String orderNo) {
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
