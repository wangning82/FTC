package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;

/**
 * Created by houyi on 2017/7/5 0005.
 */
public class AddressDto extends BaseDto<AddressDto> {
    private String id;
    private String name;
    private String phone;
    private String district;
    private String detail;
    private boolean isDefault;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
