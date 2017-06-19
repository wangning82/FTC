package com.thinkgem.jeesite.modules.ftc.convert;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.springframework.stereotype.Component;

/**
 * Created by bingbing on 2017/6/19.
 */
@Component
public class ModelConverter extends BaseConverter<Product, ModelDto> {
    @Override
    public ModelDto convertModelToDto(Product model) {
        ModelDto d = new ModelDto();
        d.setId(model.getId());
        d.setSpecList(model.getSpecs());
        return d;
    }
}
