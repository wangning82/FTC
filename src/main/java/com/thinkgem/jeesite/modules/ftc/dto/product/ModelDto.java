package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;

import java.util.List;

/**
 * Created by bingbing on 2017/6/19.
 */
public class ModelDto extends BaseDto<ModelDto> {
    private String id;
    private List<Position> positionList;
    private List<ProductSpec>  specList;

    public List<ProductSpec> getSpecList() {
        return specList;
    }

    public void setSpecList(List<ProductSpec> specList) {
        this.specList = specList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

}
