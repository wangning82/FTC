package com.thinkgem.jeesite.modules.ftc.rest.advert;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.convert.advert.AdvertConverter;
import com.thinkgem.jeesite.modules.ftc.dto.advert.AdvertDetailDto;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.entity.advert.AdvertDetail;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.service.advert.AdvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingbing on 2017/6/8.
 */
@RestController
@RequestMapping(value = "/rest/ftc/advert/")
@Api(value = "广告管理", description = "获取app广告")
public class AdvertRestController extends BaseRestController{

    private static String BANNER_ADVERT_CODE="indexCarousel";
    @Autowired
    private AdvertService advertService;
    @Autowired
    private AdvertConverter advertConverter;
    /**
     * 取广告位明细
     * @param
     * @return
     */
    @RequestMapping(value = {"info"},method = { RequestMethod.GET})
    @ApiOperation(value = "广告列表", notes = "根据广告位编号获取广告列表")
    public RestResult info(String  code){
        Advert advert=advertService.getByCode(code);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,advert.getAdvertDetailList());
    }
    /**
     * 轮播图列表
     * @param
     * @return
     */
    @RequestMapping(value = {"banner"},method = { RequestMethod.GET})
    @ApiOperation(value = "轮播图列表", notes = "获取轮播广告列表")
    public RestResult banner(){
        Advert advert=advertService.getByCode(BANNER_ADVERT_CODE);
        List<AdvertDetailDto> detailDtos=advertConverter.convertListFromModelToDto(advert.getAdvertDetailList());
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,detailDtos);
    }




}
