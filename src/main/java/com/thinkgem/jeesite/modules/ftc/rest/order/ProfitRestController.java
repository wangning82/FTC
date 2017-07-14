package com.thinkgem.jeesite.modules.ftc.rest.order;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.constant.BillStatusEnum;
import com.thinkgem.jeesite.modules.ftc.constant.BillTypeEnum;
import com.thinkgem.jeesite.modules.ftc.convert.customer.CustomerBillConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.customer.CustomerBill;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerBillService;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Created by houyi on 2017/7/12.
 */
@RestController
@RequestMapping(value = "/rest/ftc/profit/")
@Api(value = "收益", description = "收益")
public class ProfitRestController extends BaseRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerBillService customerBillService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConvert;

    @Autowired
    private CustomerBillConverter customerBillConverter;

    /**
     * 卖出产品列表
     *
     * @param token
     * @param type 产品类型:按时间或者商品
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "卖出产品列表", notes = "卖出产品列表")
    @RequestMapping(value = {"findSoldPage"}, method = {RequestMethod.POST})
    public RestResult findSoldPage(@RequestParam("token") String token,
                                   @RequestParam("type") String type,
                                   HttpServletRequest request, HttpServletResponse response) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            Page<Product> productPage = productService.findSoldPage(new Page<Product>(request, response), new Product());
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, productConvert.convertListFromModelToDto(productPage.getList()));
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    @ApiOperation(value = "设计师营收统计", notes = "设计师营收统计")
    @RequestMapping(value = {"findDesignerIncome"}, method = {RequestMethod.POST})
    public RestResult findDesignerIncome(@RequestParam("token") String token){
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, orderService.findIncomeByDesigner(customer));
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    @ApiOperation(value = "商品营收统计", notes = "商品营收统计")
    @RequestMapping(value = {"findSoldInfo"}, method = {RequestMethod.POST})
    public RestResult findSoldInfo(@RequestParam("token") String token, @RequestParam("id") String productId){
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, orderService.findSoldInfo(productId));
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    @ApiOperation(value = "提现记录列表", notes = "提现记录列表")
    @RequestMapping(value = {"findWithdrawList"}, method = {RequestMethod.POST})
    public RestResult findWithdrawList(@RequestParam("token") String token, @RequestParam("type") String type,
                                       HttpServletRequest request, HttpServletResponse response){
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            CustomerBill param = new CustomerBill();
            param.setType(BillTypeEnum.WITHDRAW.getValue());
            if("1".equals(type)){
                param.setStatus(BillStatusEnum.APPLY.getValue()); // 申请提现（提现中）
            }else if("2".equals(type)){
                param.setStatus(BillStatusEnum.ARRIVE.getValue()); // 提现完成
            }
            Page<CustomerBill> customerBillPage = customerBillService.findPage(new Page<CustomerBill>(request, response), param);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, customerBillConverter.convertListFromModelToDto(customerBillPage.getList()));
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

    @ApiOperation(value = "申请提现", notes = "申请提现")
    @RequestMapping(value = {"doWithdraw"}, method = {RequestMethod.POST})
    public RestResult doWithdraw(@RequestParam("token") String token, @RequestParam("withdraw") BigDecimal withdraw){
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setCustomer(customer);
            customerBill.setMoney(withdraw);
            customerBill.setType(BillTypeEnum.WITHDRAW.getValue());
            customerBill.setStatus(BillStatusEnum.APPLY.getValue());
            customerBillService.save(customerBill);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        } else {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
    }

}
