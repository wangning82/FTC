package com.thinkgem.jeesite.modules.ftc.rest.advert;

import com.thinkgem.jeesite.common.web.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import com.thinkgem.jeesite.modules.ftc.rest.entity.RestResult;
import com.thinkgem.jeesite.modules.ftc.service.advert.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bingbing on 2017/6/8.
 */
@RestController
@RequestMapping(value = "/rest/ftc/advert/")
public class AdvertRestController extends BaseRestController{

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
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,advert,1);
    }
}
