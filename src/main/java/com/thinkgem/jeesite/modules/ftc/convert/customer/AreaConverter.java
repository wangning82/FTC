package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.AreaDto;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by houyi on 2017/7/17 0017.
 */
@Component
public class AreaConverter extends BaseConverter<Area, AreaDto> {

    @Autowired
    private AreaService areaService;

    @Override
    public AreaDto convertModelToDto(Area model) {
        AreaDto areaDto = new AreaDto();
        areaDto.setId(model.getId());
        areaDto.setName(model.getName());

        Area areaParam = new Area();
        areaParam.setParentIds(model.getParentIds() + model.getId() + ",");
        List<Area> children = areaService.findByParentIdsLike(areaParam);
        if(CollectionUtils.isNotEmpty(children)){
            areaDto.setChildren(convertListFromModelToDto(children));
        }

        return areaDto;
    }
}
