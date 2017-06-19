package com.thinkgem.jeesite.modules.ftc.rest.customer;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.utils.EhCacheUtils;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by houyi on 2017/6/17 0017.
 */
@RestController
@RequestMapping(value = "/rest/ftc/customer/")
public class CustomerRestController extends BaseRestController {

    @Autowired
    private CustomerService customerService;

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = {"sendShortMessage"})
    public RestResult sendShortMessage(String mobile) {
        String captcha = getShortMessageNumber();
        EhCacheUtils.put(CAPTCHA_CACHE, mobile, captcha);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, captcha);
    }

    /**
     * 注册用户
     * @param mobile
     * @param password
     * @param captcha
     * @return
     */
    @RequestMapping(value = {"register"})
    public RestResult register(String mobile, String password, String captcha){
        String random = (String)EhCacheUtils.get(CAPTCHA_CACHE, mobile);
        if(captcha.equals(random)){
            Customer param = new Customer();
            param.setTelephone(mobile);
            List<Customer> result = customerService.findList(param);
            if(CollectionUtils.isEmpty(result)){
                Customer customer = new Customer();
                customer.setTelephone(mobile);
                customer.setLoginPassword(password);
                customerService.save(customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
            }else {
                return new RestResult(CODE_ERROR, "该手机号已被注册了");
            }
        }else {
            return new RestResult(CODE_ERROR, "手机验证码不正确！");
        }
    }

    /**
     * 用户登录
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value = {"login"})
    public RestResult login(String mobile, String password){
        Customer param = new Customer();
        param.setTelephone(mobile);
        List<Customer> result = customerService.findList(param);
        if(CollectionUtils.isNotEmpty(result)){
            Customer customer = result.get(0);
            if(password.equals(customer.getLoginPassword())){
                EhCacheUtils.put(TOKEN_CACHE, UUID.randomUUID().toString(), customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS, customer);
            }else {
                return new RestResult(CODE_ERROR, "用户密码不正确");
            }
        }else {
            return new RestResult(CODE_ERROR, "该手机号没有注册用户");
        }
    }

    /**
     * 重置密码
     * @param mobile
     * @param password
     * @param captcha
     * @return
     */
    @RequestMapping(value = {"resetPassword"})
    public RestResult resetPassword(String mobile, String password, String captcha){
        String random = (String)EhCacheUtils.get(CAPTCHA_CACHE, mobile);
        if(captcha.equals(random)){
            Customer param = new Customer();
            param.setTelephone(mobile);
            List<Customer> result = customerService.findList(param);
            if(CollectionUtils.isNotEmpty(result)){
                Customer customer = result.get(0);
                customer.setLoginPassword(password);
                customerService.save(customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
            }else {
                return new RestResult(CODE_ERROR, "该手机号没有注册用户");
            }
        }else {
            return new RestResult(CODE_ERROR, "手机验证码不正确！");
        }
    }

    /**
     * 更新用户信息
     * @param token
     * @param customer
     * @return
     */
    @RequestMapping(value = {"updateCustomer"})
    public RestResult updateCustomer(String token, Customer customer){
        Customer loginCustomer = findCustomerByToken(token);
        if(loginCustomer == null){
            return new RestResult(CODE_ERROR, "令牌无效，请重新登录！");
        }else {
            customerService.save(customer);
            EhCacheUtils.put(TOKEN_CACHE, token, customer);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        }
    }
}

