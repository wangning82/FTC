package com.thinkgem.jeesite.modules.ftc.service.customer;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.thinkgem.jeesite.modules.ftc.config.ShortMessageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by houyi on 2017/4/5.
 */
@Service
public class ShortMessageService {
    private static final String SUCCESS_CODE = "000000";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShortMessageConfig shortMessageConfig;

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    public String sendMessage(String mobile) {
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init(shortMessageConfig.getServerUrl(), shortMessageConfig.getServerPort());
        restAPI.setAccount(shortMessageConfig.getSid(), shortMessageConfig.getToken());
        restAPI.setAppId(shortMessageConfig.getAppId());
        String captcha = getShortMessageNumber();
        logger.debug("短信验证码：" + captcha);
        result = restAPI.sendTemplateSMS(mobile, shortMessageConfig.getTempleteId(), new String[]{captcha, "5"});
        if (!SUCCESS_CODE.equals(result.get("statusCode"))) {
            logger.error("发送短信失败，错误码=" + result.get("statusCode") + ";错误信息=" + result.get("statusMsg"));
        }
        return captcha;
    }

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
    private String getShortMessageNumber(){
        return String.valueOf(getRandNum(1000, 9999));
    }

}
