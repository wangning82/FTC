package com.thinkgem.jeesite.modules.ftc.rest.advert;

import com.thinkgem.jeesite.common.web.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.dto.AdvertDetailDto;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.entity.advert.AdvertDetail;
import com.thinkgem.jeesite.modules.ftc.rest.entity.RestResult;
import com.thinkgem.jeesite.modules.ftc.service.advert.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingbing on 2017/6/8.
 */
@RestController
@RequestMapping(value = "/rest/ftc/advert/")
public class AdvertRestController extends BaseRestController{

    private static String BANNER_ADVERT_CODE="indexCarousel";
    @Autowired
    private AdvertService advertService;
    /**
     * 取广告位明细
     * @param
     * @return
     */
    @RequestMapping(value = {"info"})
    public RestResult info(String  code){
        Advert advert=advertService.getByCode(code);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,advert.getAdvertDetailList());
    }
    /**
     * 取广告位明细
     * @param
     * @return
     */
    @RequestMapping(value = {"banner"})
    public RestResult banner(){
        Advert advert=advertService.getByCode(BANNER_ADVERT_CODE);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,convertList(advert.getAdvertDetailList()));
    }

    public AdvertDetailDto convertDetail(AdvertDetail advertDetail){
        AdvertDetailDto dto=new AdvertDetailDto();
        dto.setGid(advertDetail.getId());
        dto.setImageUrl(advertDetail.getPicImg());
        dto.setLinkUrl(advertDetail.getHref());
        dto.setTitle(advertDetail.getTitle());
        return dto;
    }
    private List<AdvertDetailDto> convertList(List<AdvertDetail> advertDetailList){
        List<AdvertDetailDto>advertDetailDtoList=new ArrayList<AdvertDetailDto>();
        for(AdvertDetail detail:advertDetailList){
           advertDetailDtoList.add(convertDetail(detail));
        }
        return advertDetailDtoList;
    }
}
