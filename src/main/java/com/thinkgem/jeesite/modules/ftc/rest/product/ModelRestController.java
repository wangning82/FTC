package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.convert.product.ModelConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.PositionConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.PositionDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Category;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.product.PositionService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by bingbing on 2017/6/19.
 */
@RestController
@RequestMapping(value = "/rest/ftc/model/")
@Api(value = "模型管理", description = "模型管理")
public class ModelRestController extends BaseRestController {
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
     * 模型列表
     * @param modelDto
     * @return
     */
    @ApiOperation(value = "模型列表", notes = "获取模型列表")
    @RequestMapping(value = {"list"},method = { RequestMethod.GET})
    public RestResult model(ModelDto modelDto, HttpServletRequest request, HttpServletResponse response) {
        //只查询是模型的数据
        Product product=modelConverter.convertDtoToModel(modelDto);
        product.setModelFlag("1");

        Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
        List<ModelDto> modelDtoList=modelConverter.convertListFromModelToDto(page.getList());

        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,modelDtoList);
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
