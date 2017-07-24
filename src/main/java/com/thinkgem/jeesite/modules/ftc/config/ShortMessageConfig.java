package com.thinkgem.jeesite.modules.ftc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 短信配置
 * Created by houyi on 2017/4/5.
 */
@Component
public class ShortMessageConfig implements Serializable {

    @Value("${sms.sid}")
    private String sid;

    @Value("${sms.token}")
    private String token;

    @Value("${sms.appId}")
    private String appId;

    @Value("${sms.templeteId}")
    private String templeteId;

    @Value("${sms.server.url}")
    private String serverUrl;

    @Value("${sms.server.port}")
    private String serverPort;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTempleteId() {
        return templeteId;
    }

    public void setTempleteId(String templeteId) {
        this.templeteId = templeteId;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }
}
