


package com.thinkgem.jeesite.modules.pay.dao;

import com.egzosn.pay.common.bean.MsgType;
import com.egzosn.pay.common.util.sign.SignUtils;
import com.thinkgem.jeesite.modules.pay.entity.ApyAccount;
import com.thinkgem.jeesite.modules.pay.entity.PayType;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/11/18 1:21
 */
//@Repository
public class ApyAccountRepository {

    // 这里简单模拟，引入orm等框架之后可自行删除
    public static   Map<Integer, ApyAccount> apyAccounts = new HashMap<>();

    /**
     * 这里简单初始化，引入orm等框架之后可自行删除
     */
    {
        ApyAccount apyAccount1 = new ApyAccount();
        apyAccount1.setPayId(2);
        apyAccount1.setPartner("2088102169916436");
        apyAccount1.setAppid("2016080400165436");
        // TODO 2017/2/9 16:20 author: egan  sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5的情况
        apyAccount1.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB");
        apyAccount1.setPrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKroe/8h5vC4L6T+B2WdXiVwGsMvUKgb2XsKix6VY3m2wcf6tyzpNRDCNykbIwGtaeo7FshN+qZxdXHLiIam9goYncBit/8ojfLGy2gLxO/PXfzGxYGs0KsDZ+ryVPPmE34ZZ8jiJpR0ygzCFl8pN3QJPJRGTJn5+FTT9EF/9zyZAgMBAAECgYAktngcYC35u7cQXDk+jMVyiVhWYU2ULxdSpPspgLGzrZyG1saOcTIi/XVX8Spd6+B6nmLQeF/FbU3rOeuD8U2clzul2Z2YMbJ0FYay9oVZFfp5gTEFpFRTVfzqUaZQBIjJe/xHL9kQVqc5xHlE/LVA27/Kx3dbC35Y7B4EVBDYAQJBAOhsX8ZreWLKPhXiXHTyLmNKhOHJc+0tFH7Ktise/0rNspojU7o9prOatKpNylp9v6kux7migcMRdVUWWiVe+4ECQQC8PqsuEz7B0yqirQchRg1DbHjh64bw9Kj82EN1/NzOUd53tP9tg+SO97EzsibK1F7tOcuwqsa7n2aY48mQ+y0ZAkBndA2xcRcnvOOjtAz5VO8G7R12rse181HjGfG6AeMadbKg30aeaGCyIxN1loiSfNR5xsPJwibGIBg81mUrqzqBAkB+K6rkaPXJR9XtzvdWb/N3235yPkDlw7Z4MiOVM3RzvR/VMDV7m8lXoeDde2zQyeMOMYy6ztwA6WgE1bhGOnQRAkEAouUBv1sVdSBlsexX15qphOmAevzYrpufKgJIRLFWQxroXMS7FTesj+f+FmGrpPCxIde1dqJ8lqYLTyJmbzMPYw==\n");
        apyAccount1.setNotifyUrl("http://pay.egan.in/payBack1.json");
        // 无需同步回调可不填
//        apyAccount1.setReturnUrl("");
        apyAccount1.setInputCharset("UTF-8");
        apyAccount1.setSeller("2088102169916436");
        apyAccount1.setSignType(SignUtils.RSA.name());
        apyAccount1.setPayType(PayType.aliPay);
        apyAccount1.setMsgType(MsgType.text);
        //设置测试环境
        apyAccount1.setTest(true);
        apyAccounts.put(apyAccount1.getPayId(), apyAccount1);




        ApyAccount apyAccount4 = new ApyAccount();
        apyAccount4.setPayId(1);
        apyAccount4.setPartner("1485022862");
        apyAccount4.setAppid("wx92c4e317193b4b0f");
        apyAccount4.setPublicKey("tjgbaoshuiquqiyedangweiapp201707");
        apyAccount4.setPrivateKey("tjgbaoshuiquqiyedangweiapp201707");
        apyAccount4.setNotifyUrl("http://pay.egan.in/payBack3.json");
        // 无需同步回调可不填  app填这个就可以
        apyAccount4.setReturnUrl("http://pay.egan.in/payBack3.json");
        apyAccount4.setSeller("12****601");
        apyAccount4.setInputCharset("UTF-8");
        apyAccount4.setPayType(PayType.wxPay);
        apyAccount4.setSignType(SignUtils.MD5.name());
        apyAccount4.setMsgType(MsgType.xml);
        apyAccounts.put(apyAccount4.getPayId(), apyAccount4);




    }
    //_____________________________________________________________


    /**
     * 根据id获取对应的账户信息
     * @param payId 账户id
     * @return
     */
    public ApyAccount findByPayId(Integer payId){
        // TODO 2016/11/18 1:23 author: egan  这里简单模拟 具体实现 略。。
        return apyAccounts.get(payId);
    }
}
