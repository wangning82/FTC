package com.thinkgem.jeesite.modules.ftc.convert.advert;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.modules.ftc.dto.advert.AdvertDetailDto;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.entity.advert.AdvertDetail;
import org.springframework.stereotype.Component;

/**
 * Created by wagnqingxiang on 2017/6/21.
 */
@Component
public class AdvertConverter extends BaseConverter<AdvertDetail,AdvertDetailDto>{
    PropertiesLoader loader=new PropertiesLoader("jeesite.properties");
    String serverName=loader.getProperty("serverName");
    @Override
    public AdvertDetailDto convertModelToDto(AdvertDetail model) {
        AdvertDetailDto dto=new AdvertDetailDto();
        dto.setGid(model.getId());
        dto.setImageUrl(serverName+model.getPicImg());
        dto.setLinkUrl(model.getHref());
        dto.setTitle(model.getTitle());
        return dto;
    }


}
