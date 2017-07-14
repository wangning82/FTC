package com.thinkgem.jeesite.modules.ftc.rest.customer;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.common.utils.EhCacheUtils;
import com.thinkgem.jeesite.common.utils.ImageUtils;
import com.thinkgem.jeesite.modules.ftc.constant.FlagEnum;
import com.thinkgem.jeesite.modules.ftc.constant.ImgSourceEnum;
import com.thinkgem.jeesite.modules.ftc.constant.PlatformTypeEnum;
import com.thinkgem.jeesite.modules.ftc.convert.customer.AddressConverter;
import com.thinkgem.jeesite.modules.ftc.convert.customer.CustomerConverter;
import com.thinkgem.jeesite.modules.ftc.convert.customer.ShopConverter;
import com.thinkgem.jeesite.modules.ftc.convert.customer.UserInfoConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.AddressDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.service.customer.AddressService;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerService;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by houyi on 2017/6/17 0017.
 */
@RestController
@RequestMapping(value = "/rest/ftc/customer/")
@Api(value = "用户登录", description = "用户登录")
public class CustomerRestController extends BaseRestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserInfoConverter userInfoConverter;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private ShopConverter shopConverter;

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = "发送短信验证码", notes = "用户登录首页和找回密码页面，发送短信验证码")
    @RequestMapping(value = {"sendShortMessage"}, method = {RequestMethod.POST})
    public RestResult sendShortMessage(@RequestParam("mobile") String mobile) {
//        String captcha = getShortMessageNumber();
        String captcha="1234";
        EhCacheUtils.put(CAPTCHA_CACHE, mobile, captcha);
        //TODO 调用短信接口发送验证码
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, captcha);
    }

    /**
     * 注册用户
     *
     * @param mobile
     * @param password
     * @param captcha
     * @return
     */
    @Deprecated
    @ApiOperation(value = "用户注册", notes = "用户注册，密码为密文")
    @RequestMapping(value = {"register"}, method = {RequestMethod.POST})
    public RestResult register(@RequestParam("mobile") String mobile, @RequestParam("password") String password, @RequestParam("captcha") String captcha) {
        String random = (String) EhCacheUtils.get(CAPTCHA_CACHE, mobile);
        if (captcha.equals(random)) {
            Customer param = new Customer();
            param.setTelephone(mobile);
            List<Customer> result = customerService.findList(param);
            if (CollectionUtils.isEmpty(result)) {
                Customer customer = new Customer();
                customer.setTelephone(mobile);
                customer.setLoginPassword(password);
                customerService.save(customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
            } else {
                return new RestResult(CODE_ERROR, "该手机号已被注册了");
            }
        } else {
            return new RestResult(CODE_ERROR, "手机验证码不正确！");
        }
    }

    /**
     * 重置密码
     *
     * @param mobile
     * @param password
     * @param captcha
     * @return
     */
    @Deprecated
    @ApiOperation(value = "重置密码", notes = "重置密码，密码为密文")
    @RequestMapping(value = {"resetPassword"}, method = {RequestMethod.POST})
    public RestResult resetPassword(@RequestParam("mobile") String mobile, @RequestParam("password") String password, @RequestParam("captcha") String captcha) {
        String random = (String) EhCacheUtils.get(CAPTCHA_CACHE, mobile);
        if (captcha.equals(random)) {
            Customer param = new Customer();
            param.setTelephone(mobile);
            List<Customer> result = customerService.findList(param);
            if (CollectionUtils.isNotEmpty(result)) {
                Customer customer = result.get(0);
                customer.setLoginPassword(password);
                customerService.save(customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
            } else {
                return new RestResult(CODE_ERROR, "该手机号没有注册用户");
            }
        } else {
            return new RestResult(CODE_ERROR, "手机验证码不正确！");
        }
    }

    /**
     * 用户登录
     *
     * @param platformType
     * @param userId
     * @param code
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "支持短信、微信和QQ登录")
    @RequestMapping(value = {"login"}, method = {RequestMethod.POST})
    public RestResult login(@RequestParam("pType") String platformType, @RequestParam("pUid") String userId,
                            @RequestParam("pCode") String code) throws Exception {
        if (PlatformTypeEnum.Phone.getValue().equals(platformType)) {
            return loginByShortMessage(userId, code);
        } else if (PlatformTypeEnum.WeChat.getValue().equals(platformType)) {
            return loginByWeChat(userId, code);
        } else if (PlatformTypeEnum.QQ.getValue().equals(platformType)) {
            return null; // TODO QQ登录
        } else {
            return new RestResult(CODE_ERROR, "不支持的登录方式");
        }
    }

    /**
     * 短信登录
     *
     * @param mobile
     * @param captcha
     * @return
     */
    private RestResult loginByShortMessage(String mobile, String captcha) {
        Customer param = new Customer();
        param.setTelephone(mobile);
        List<Customer> result = customerService.findList(param);
        if (CollectionUtils.isNotEmpty(result)) {
            Customer customer = result.get(0);
            String captchaCache = (String) EhCacheUtils.get(CAPTCHA_CACHE, mobile);
            if (captchaCache.equals(captcha)) {
                String token = UUID.randomUUID().toString();
                customer.setAccessToken(token);
                customer.setExpiresTime(new Date());
                EhCacheUtils.put(TOKEN_CACHE, token, customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS, userInfoConverter.convertModelToDto(customer));
            } else {
                return new RestResult(CODE_ERROR, "短信验证码不正确");
            }
        } else {
            return new RestResult(CODE_ERROR, "没有找到该用户信息");
        }
    }

    /**
     * 微信登录
     *
     * @param openid
     * @return
     */
    private RestResult loginByWeChat(String openid, String accessToken) throws Exception {
        Customer param = new Customer();
        param.setWechat(openid);
        List<Customer> result = customerService.findList(param);
        Customer customer = null;
        if (CollectionUtils.isNotEmpty(result)) {
            customer = result.get(0);
        } else {
            customer = ThirdPartyLoginHelper.getWxUserinfo(accessToken, openid);
            customerService.save(customer);
        }
        String token = UUID.randomUUID().toString();
        customer.setAccessToken(token);
        customer.setExpiresTime(new Date());
        EhCacheUtils.put(TOKEN_CACHE, token, customer);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, userInfoConverter.convertModelToDto(customer));
    }

    @ApiOperation(value = "使用令牌登录", notes = "使用令牌登录")
    @RequestMapping(value = {"loginByToken"}, method = {RequestMethod.POST})
    public RestResult loginByToken(@RequestParam("token") String token) {
        Customer customer = findCustomerByToken(token);
        if (customer != null) {
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, userInfoConverter.convertModelToDto(customer));
        } else {
            return new RestResult(CODE_ERROR, "没有找到用户信息");
        }
    }

    @ApiOperation(value = "绑定第三方平台用户", notes = "绑定第三方平台用户")
    @RequestMapping(value = {"bindUser"}, method = {RequestMethod.POST})
    public RestResult bindUser(@RequestParam("uid") String customerId, @RequestParam("pType") String platformType,
                               @RequestParam("pUid") String openid, @RequestParam("pCode") String code) {
        Customer customer = customerService.get(customerId);
        if (customer != null) {
            if (PlatformTypeEnum.WeChat.getValue().equals(platformType)) {
                customer.setWechat(openid);
                customerService.save(customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
            } else if (PlatformTypeEnum.QQ.getValue().equals(platformType)) {
                customer.setQq(openid);
                customerService.save(customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
            } else {
                return new RestResult(CODE_ERROR, "不支持的绑定方式");
            }
        } else {
            return new RestResult(CODE_ERROR, "没有找到用户信息");
        }

    }

    @ApiOperation(value = "解绑第三方平台用户", notes = "解绑第三方平台用户")
    @RequestMapping(value = {"unBindUser"}, method = {RequestMethod.POST})
    public RestResult unBindUser(@RequestParam("uid") String customerId, @RequestParam("pType") String platformType,
                                 @RequestParam("pUid") String openid, @RequestParam("pCode") String code) {
        Customer customer = customerService.get(customerId);
        if (customer != null) {
            if (PlatformTypeEnum.WeChat.getValue().equals(platformType)) {
                customer.setWechat(null);
                customerService.save(customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
            } else if (PlatformTypeEnum.QQ.getValue().equals(platformType)) {
                customer.setQq(null);
                customerService.save(customer);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
            } else {
                return new RestResult(CODE_ERROR, "不支持的解绑方式");
            }
        } else {
            return new RestResult(CODE_ERROR, "没有找到用户信息");
        }
    }

    /**
     * 更新用户信息
     *
     * @param token
     * @param customerDto
     * @return
     */
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @RequestMapping(value = {"updateCustomer"}, method = {RequestMethod.POST})
    public RestResult updateCustomer(@RequestParam("token") String token, CustomerDto customerDto) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            customer.setUserName(customerDto.getName());
            customer.setSignature(customerDto.getDesc());
            customer.setPicImg(ImageUtils.generateImg(customerDto.getImgUrl(), customer.getId(), ImgSourceEnum.IMG_SOURCE_TOUXIANG.getValue()));
            customerService.save(customer);
            EhCacheUtils.put(TOKEN_CACHE, token, userInfoConverter.convertModelToDto(customer));
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        }
    }

    @ApiOperation(value = "获取地区列表", notes = "用户编辑收货地址时的备选条件 ")
    @RequestMapping(value = {"findAreaList"}, method = {RequestMethod.GET})
    public RestResult findAreaList() {
        List<Area> areaList = areaService.findAll();
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, areaList);
    }

    @ApiOperation(value = "获取收货地址列表", notes = "用户选择收货地址时显示")
    @RequestMapping(value = {"findAddressList"}, method = {RequestMethod.GET})
    public RestResult findAddressList(@RequestParam("token") String token) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Address param = new Address();
            param.setCustomer(customer);
            List<Address> addressList = addressService.findList(param);
            List<AddressDto> addressDtoList = addressConverter.convertListFromModelToDto(addressList);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, addressDtoList);
        }
    }

    @ApiOperation(value = "获取默认收货地址", notes = "用户下单时默认的收货地址")
    @RequestMapping(value = {"findDefualtAddress"}, method = {RequestMethod.GET})
    public RestResult findDefualtAddress(@RequestParam("token") String token) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Address param = new Address();
            param.setCustomer(customer);
            param.setIsDefault(FlagEnum.Flag_YES.getValue());
            List<Address> addressList = addressService.findList(param);
            if (CollectionUtils.isNotEmpty(addressList)) {
                Address address = addressList.get(0);
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS, addressConverter.convertModelToDto(address));
            } else {
                return new RestResult(CODE_ERROR, "当前用户没有设置默认收货地址");
            }
        }
    }

    @ApiOperation(value = "保存收货地址", notes = "用户新建或修改收货地址时使用，修改时需要地址标识。")
    @RequestMapping(value = {"saveAddress"}, method = {RequestMethod.POST})
    public RestResult saveAddress(@RequestParam("token") String token, AddressDto address) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            addressService.save(addressConverter.convertDtoToModel(address));
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        }
    }

    @ApiOperation(value = "删除收货地址", notes = "删除收货地址")
    @RequestMapping(value = {"delAddress"}, method = {RequestMethod.POST})
    public RestResult delAddress(@RequestParam("token") String token, AddressDto address) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            addressService.delete(addressConverter.convertDtoToModel(address));
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
        }
    }

    @ApiOperation(value = "潮店推荐列表", notes = "根据店铺收藏量倒序排列")
    @RequestMapping(value = {"hotShopList"}, method = {RequestMethod.POST})
    public RestResult hotShopList(@RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Page<Customer> param = new Page<Customer>(request, response);
            param.setOrderBy("a.wishlist_number desc");
            Page<Customer> result = customerService.findPage(param, new Customer());
            List<Customer> shopList = result.getList();
            for (Customer obj : shopList) {
                // TODO 查询商品
            }
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, shopConverter.convertListFromModelToDto(shopList));
        }
    }

    @ApiOperation(value = "获取潮店信息", notes = "获取潮店信息")
    @RequestMapping(value = {"hotShop"}, method = {RequestMethod.POST})
    public RestResult hotShop(@RequestParam("token") String token, @RequestParam("sid") String customerId) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Customer result = customerService.get(customerId);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, shopConverter.convertModelToDto(result));
        }
    }

}

