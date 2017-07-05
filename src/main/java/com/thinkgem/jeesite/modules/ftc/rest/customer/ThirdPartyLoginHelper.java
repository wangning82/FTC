package com.thinkgem.jeesite.modules.ftc.rest.customer;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.HttpUtils;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 第三方登录辅助类
 */
public final class ThirdPartyLoginHelper {
    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyLoginHelper.class);

    @Value("${wechat_url_getUserInfo}")
    private static String WECHAT_URL_USER_INFO;

    /**
     * 获取微信用户信息
     */
    public static final Customer getWxUserinfo(String token, String openid) throws Exception {
        Customer customer = new Customer();
        customer.setWechat(openid);
        String url = WECHAT_URL_USER_INFO;
        url = url + "?access_token=" + token + "&openid=" + openid;
        String res = HttpUtils.httpClientPost(url);
        JSONObject json = JSONObject.parseObject(res);
        if (json.getString("errcode") == null) {
            customer.setUserName(json.getString("nickname"));
            String img = json.getString("headimgurl");
            if (img != null && !"".equals(img)) {
                // TODO 保存图片
                customer.setPicImg(img);
            }
            String sex = json.getString("sex");
            if ("0".equals(sex)) {
                customer.setSex(0);
            } else {
                customer.setSex(1);
            }
        } else {
            throw new IllegalArgumentException(json.getString("errmsg"));
        }
        return customer;
    }

}
