package com.thinkgem.jeesite.common.rest;

import com.thinkgem.jeesite.common.utils.EhCacheUtils;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;

/**
 * Created by wangqingxiang on 2017/6/6.
 */
public class BaseRestController {
    public static String CODE_SUCCESS = "1";
    public static String CODE_ERROR = "2";
    public static String CODE_NULL = "3";
    public static String MSG_SUCCESS = "成功";

    protected static final String CAPTCHA_CACHE = "captchaCache"; // 短信验证码缓存
    protected static final String TOKEN_CACHE = "tokenCache"; // 接口令牌缓存

    /**
     * 生成随机数
     * @param min
     * @param max
     * @return
     */
    private int getRandNum(int min, int max){
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum;
    }

    /**
     * 手机短信验证码
     * @return
     */
    protected String getShortMessageNumber(){
        return String.valueOf(getRandNum(1000, 9999));
    }

    /**
     * 根据令牌获取用户
     * @param token
     * @return
     */
    protected Customer findCustomerByToken(String token){
        return (Customer)EhCacheUtils.get(TOKEN_CACHE, token);
    }

}
