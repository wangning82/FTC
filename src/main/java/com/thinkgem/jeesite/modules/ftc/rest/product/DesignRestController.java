package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.convert.product.DesignConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ModelConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.PositionConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.PositionDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.*;
import com.thinkgem.jeesite.modules.ftc.service.product.DesignService;
import com.thinkgem.jeesite.modules.ftc.service.product.PositionService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/8.
 */
@RestController
@RequestMapping(value = "/rest/ftc/design/")
@Api(value = "设计管理", description = "设计管理")
public class DesignRestController extends BaseRestController {
    @Autowired
    private DesignService designService;
    @Autowired
    private DesignConverter designConverter;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSpecService productSpecService;
    @Autowired
    private ModelConverter modelConverter;
    @Autowired
    private PositionService positionService;
    @Autowired
    private PositionConverter positionConverter;
    /**
     * 删除
     * @param
     * @return
     */
    @ApiOperation(value = "删除设计", notes = "删除设计")
    @RequestMapping(value = {"delete"},method = { RequestMethod.GET})
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
    @ApiOperation(value = "优秀设计", notes = "获取优秀设计列表")
    @RequestMapping(value = {"best"},method = { RequestMethod.GET})
    public RestResult best(Design design){
        //
        List<Design> bestDesign=designService.findList(design);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,bestDesign);
    }
    /**
     * 修改设计
     * @param
     * @return
     */
    @ApiOperation(value = "修改设计", notes = "对设计进行修改")
    @RequestMapping(value = {"modify"},method = { RequestMethod.POST})
    public RestResult modify(DesignDto  design){

        Design designModel=designService.get(design.getId());
        designModel.setName(design.getName());
        designModel.setPrice(design.getPrice());
        designModel.setDesignStatus(design.getStatus());
        designModel.setUpdateDate(new Date());
        designService.save(designModel);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,null);
    }


    /**
     * 设计明细
     * @param
     * @return
     */
    @ApiOperation(value = "设计明细", notes = "获取设计的详情")
    @RequestMapping(value = {"info"},method = { RequestMethod.GET})
    public RestResult info(String  id){

        Design design=designService.get(id);

        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,designConverter.convertModelToDto(design));
    }

    /**
     * 保存设计
     * @param
     * @return
     */
    @ApiOperation(value = "保存设计", notes = "获取设计的详情")
    @RequestMapping(value = {"save"},method = { RequestMethod.POST})
    public RestResult save(DesignDto design,@RequestParam("token") String token){

        Design model=designConverter.convertDtoToModel(design);
        designService.saveForRest(model);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,null);
    }

    /**
     * 我要设计
     * @param
     * @return
     */
    @ApiOperation(value = "我要设计", notes = "我要设计，获取模型信息")
    @RequestMapping(value = {"iwant"},method = { RequestMethod.GET})
    public RestResult iWant(){
        //我要设计，默认取第一个模型
        Product product=productService.get("c66595288aff4fee98533f9e949b9b1f");
//        if(list!=null&&list.size()>0){
//            product=list.get(0);
//        }
        product=productService.get(product.getId());
        List<ProductSpec> specs=productSpecService.findList(new ProductSpec(product));
        product.setSpecs(specs);
        ModelDto dto=modelConverter.convertModelToDto(product);
        //还要获取模型的分类包含的图片位置信息
        Category category=product.getCategory();
        List<Position> positionList =positionService.findList(new Position(category));
        List<PositionDto> positionDtoList=positionConverter.convertListFromModelToDto(positionList);
        dto.setSprites(positionDtoList);
        return new RestResult(CODE_SUCCESS,
                MSG_SUCCESS,dto);
    }

}
