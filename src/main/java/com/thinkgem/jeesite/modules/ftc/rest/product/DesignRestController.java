package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.convert.product.DesignConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.product.DesignService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        //删除设计要将商品下架
        Product p=design.getProduct();
        p.setShowInShelve("1");
        productService.save(p);
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
        //删除设计
        List<Design> bestDesign=designService.findList(design);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,bestDesign);
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
        if(design.getProduct()!=null){
           Product p=productService.get(design.getProduct().getId());
           design.setProduct(p);
           design.setImages(p.getImages());
        }
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,designConverter.convertModelToDto(design));
    }

    /**
     * 保存设计
     * @param
     * @return
     */
    @ApiOperation(value = "保存设计", notes = "获取设计的详情")
    @RequestMapping(value = {"save"},method = { RequestMethod.POST})
    public RestResult save(DesignDto designDto){

        //获取modelid,复制model信息为新的商品
        ModelDto modelDto=designDto.getModelDto();
        Product product=productService.get(modelDto.getId());
//        List<>
        //复制product
        product.setId("");
//        product.
        //保存图片到商品大图，
        //保存图片到规格小图
        //保存设计信息

        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,null);
    }



}
