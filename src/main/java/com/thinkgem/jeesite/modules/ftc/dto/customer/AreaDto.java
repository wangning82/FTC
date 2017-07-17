package com.thinkgem.jeesite.modules.ftc.dto.customer;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.util.List;

/**
 * Created by houyi on 2017/7/17 0017.
 */
public class AreaDto extends BaseDto<AreaDto> {
    private String id;
    private String name;
    private List<AreaDto> children;

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

    public List<AreaDto> getChildren() {
        return children;
    }

    public void setChildren(List<AreaDto> children) {
        this.children = children;
    }
}
