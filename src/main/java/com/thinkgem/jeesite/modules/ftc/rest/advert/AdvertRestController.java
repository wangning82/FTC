package com.thinkgem.jeesite.modules.ftc.rest.advert;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.convert.advert.AdvertConverter;
import com.thinkgem.jeesite.modules.ftc.dto.advert.AdvertDetailDto;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.service.advert.AdvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = {"info"},method = { RequestMethod.POST})
    @ApiOperation(value = "广告列表", notes = "根据广告位编号获取广告列表")
    public RestResult info(@RequestParam("code") String code){
        Advert advert=advertService.getByCode(code);
        List<AdvertDetailDto> detailDtos=advertConverter.convertListFromModelToDto(advert.getAdvertDetailList());
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,detailDtos);}
    /**
     * 轮播图列表
     * @param
     * @return
     */
    @RequestMapping(value = {"banner"},method = { RequestMethod.POST})
    @ApiOperation(value = "轮播图列表", notes = "获取轮播广告列表")
    public RestResult banner(){
        Advert advert=advertService.getByCode(BANNER_ADVERT_CODE);
        List<AdvertDetailDto> detailDtos=advertConverter.convertListFromModelToDto(advert.getAdvertDetailList());
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,detailDtos);
    }
    /**
     * 轮播图列表
     * @param
     * @return
     */
    @RequestMapping(value = {"aboutus"},method = { RequestMethod.POST})
    @ApiOperation(value = "关于我们", notes = "获取客服和app信息")
    public RestResult aboutUs(){
        Map<String,String> us=new HashMap();
        us.put("imgUrl","http://p2.so.qhmsg.com/bdr/_240_/t01201a2ba7a2fc5787.png");
        us.put("phone","022-68432376");
        us.put("content","aaaaaaaaaaaaaaaaaaaaaa");
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,us);
    }



}
