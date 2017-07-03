package com.thinkgem.jeesite.common.pay.entity;

import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.BasePayType;
import com.egzosn.pay.common.bean.TransactionType;
import com.thinkgem.jeesite.common.pay.service.handler.FuiouPayMessageHandler;

import com.egzosn.pay.wx.api.WxPayConfigStorage;
import com.egzosn.pay.wx.api.WxPayService;
import com.egzosn.pay.wx.bean.WxTransactionType;


/**
 * 支付类型
 *
 * @author egan
 *         email egzosn@gmail.com
 *         date 2016/11/20 0:30
 */
public enum PayType implements BasePayType {


    aliPay {
        /**
         *  @see com.egzosn.pay.ali.api.AliPayService 17年更新的版本,旧版本请自行切换
         * @param apyAccount
         * @return
         */
        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
            aliPayConfigStorage.setPid(apyAccount.getPartner());
            aliPayConfigStorage.setAppId(apyAccount.getAppid());
            aliPayConfigStorage.setAliPublicKey(apyAccount.getPublicKey());
            aliPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            aliPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            aliPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            aliPayConfigStorage.setSignType(apyAccount.getSignType());
            aliPayConfigStorage.setSeller(apyAccount.getSeller());
            aliPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            aliPayConfigStorage.setMsgType(apyAccount.getMsgType());
            aliPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            aliPayConfigStorage.setTest(apyAccount.isTest());
            return new AliPayService(aliPayConfigStorage);
        }

        @Override
        public TransactionType getTransactionType(String transactionType) {
            // com.egzosn.pay.ali.before.bean.AliTransactionType 17年更新的版本,旧版本请自行切换

            // AliTransactionType 17年更新的版本,旧版本请自行切换
            return AliTransactionType.valueOf(transactionType);
        }


    }, wxPay {
        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
            wxPayConfigStorage.setMchId(apyAccount.getPartner());
            wxPayConfigStorage.setAppSecret(apyAccount.getPublicKey());
            wxPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
            wxPayConfigStorage.setAppid(apyAccount.getAppid());
            wxPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            wxPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            wxPayConfigStorage.setSignType(apyAccount.getSignType());
            wxPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            wxPayConfigStorage.setMsgType(apyAccount.getMsgType());
            wxPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            wxPayConfigStorage.setTest(apyAccount.isTest());
            return new WxPayService(wxPayConfigStorage);
        }

        /**
         * 根据支付类型获取交易类型
         * @param transactionType 类型值
         * @see WxTransactionType
         * @return
         */
        @Override
        public TransactionType getTransactionType(String transactionType) {

            return WxTransactionType.valueOf(transactionType);
        }


    };

    public abstract PayService getPayService(ApyAccount apyAccount);


}
