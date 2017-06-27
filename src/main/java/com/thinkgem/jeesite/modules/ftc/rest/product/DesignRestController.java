package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.ProductNoGenerator;
import com.thinkgem.jeesite.modules.ftc.convert.product.DesignConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ImageConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.DesignDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ImageDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductSpecDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.*;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.service.product.DesignService;
import com.thinkgem.jeesite.modules.ftc.service.product.ImageService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public RestResult save(DesignDto designDto,@RequestParam("token") String token){
        //作假数据测试
        Customer customer=new Customer("28");
        designDto.setName("aaaaa");
        designDto.setPrice(1000d);
        ModelDto modelDto=new ModelDto();
        modelDto.setId("a7f88d26ded141ca8c4e5ac2c2e72660");
        ProductSpecDto specDto=new ProductSpecDto();
        specDto.setImageUrl("aaaaaa");
        specDto.setId("bcd6e64e728a42b8a6df9d1ce42f748e");
        List<ProductSpecDto> specDtoList=new ArrayList<>();
        specDtoList.add(specDto);
        modelDto.setAttrs(specDtoList);

        designDto.setModelDto(modelDto);
        List<ImageDto> imageDtos=new ArrayList<>();

        ImageDto imageDto=new ImageDto();
        imageDto.setImgUrl("bbbbbb");
        imageDto.getMeshDto().setId("58cdc442b95d45f39c4ee1cfe62a1833");
        imageDto.getMeshDto().setRotation(100d);
        imageDtos.add(imageDto);
        imageDto=new ImageDto();
        imageDto.setImgUrl("cccccc");
        imageDto.getMeshDto().setId("d45641a293cb4f099308b940ad4e1b01");
        imageDto.getMeshDto().setRotation(30d);
        imageDtos.add(imageDto);
        designDto.setImageDtoList(imageDtos);






        Design design=designConverter.convertDtoToModel(designDto);
        design.setCustomer( customer);
        designService.saveForRest(design);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,null);
    }



}
