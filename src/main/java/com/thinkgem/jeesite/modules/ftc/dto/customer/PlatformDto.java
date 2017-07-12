package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;

/**
 * Created by houyi on 2017/7/13 0013.
 */
public class PlatformDto extends BaseDto<PlatformDto> {
    private String type;
    private String pUid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getpUid() {
        return pUid;
    }

    public void setpUid(String pUid) {
        this.pUid = pUid;
    }
}
