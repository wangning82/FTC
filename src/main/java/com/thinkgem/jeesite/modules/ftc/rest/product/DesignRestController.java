package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.service.product.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bingbing on 2017/6/8.
 */
@RestController
@RequestMapping(value = "/rest/ftc/design/")
public class DesignRestController extends BaseRestController {
    @Autowired
    private DesignService designService;
    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping(value = {"delete"})
    public RestResult delete(Design design){
        //删除设计
        designService.delete(design);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,null);
    }
    /**
     * 优秀设计
     * @param
     * @return
     */
    @RequestMapping(value = {"best"})
    public RestResult best(Design design){
        //删除设计
        List<Design> bestDesign=designService.findList(design);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,bestDesign);
    }
    /**
     * 我的设计
     * @param
     * @return
     */
    @RequestMapping(value = {"mylist"})
    public RestResult mylist(Design design){
        //删除设计
        List<Design> bestDesign=designService.findList(design);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,bestDesign);
    }

    /**
     * 设计明细
     * @param
     * @return
     */
    @RequestMapping(value = {"info"})
    public RestResult info(String  id){
        //删除设计
        Design design=designService.get(id);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,design);
    }





}
