package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/5.
 */
@RestController
@RequestMapping(value = "/rest/ftc/product/")
@Api(value = "商品管理", description = "商品管理")
public class ProductRestController extends BaseRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSpecService productSpecService;
    @Autowired
    private ProductConverter productConvert;
    @Autowired
    private PositionService positionService;

    /**
     * 获取商品列表，传入参数
     * 可以根据分类获取商品或模型
     * @return
     */
    @ApiOperation(value = "商品列表", notes = "获取商品列表")
    @RequestMapping(value = {"list", ""},method = { RequestMethod.GET})
    public RestResult list(ProductDto goods, HttpServletRequest request, HttpServletResponse response) {
        Page<Product> page = productService.
                findPage(new Page<Product>(request, response),
                        productConvert.convertDtoToModel(goods));
        List<ProductDto> productDtoList=
                productConvert.convertListFromModelToDto(page.getList());
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,productDtoList);
    }


    /**
     * 获取商品信息
     * @param id
     * @return
     */
    @RequestMapping(value = {"info"},method = { RequestMethod.GET})
    @ApiOperation(value = "商品详情", notes = "获取商品详细信息")
    public RestResult info(@RequestParam ("id")String  id){
        Product product=productService.get(id);
        List<ProductSpec> specs=productSpecService.findList(new ProductSpec(product));
        product.setSpecs(specs);
        //更新热度，自动加一
        productService.addHot(product);
        return new RestResult(CODE_SUCCESS,
                MSG_SUCCESS,productConvert.convertModelToDto(product));
    }

    /**
     * 保存
     * @param productDto
     * @return
     */
    @RequestMapping(value = {"save"},method = { RequestMethod.POST})
    @ApiOperation(value ="保存商品", notes = "保存商品")
    public RestResult save(ProductDto  productDto){
        productService.save(productConvert.convertDtoToModel(productDto));
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,null);
    }

    /**
     * 删除
     * @param productDto
     * @return
     */
    @RequestMapping(value = {"delete"},method = { RequestMethod.GET})
    @ApiOperation(value ="删除商品", notes = "删除商品信息")
    public RestResult delete(ProductDto  productDto){
        //删除设计，产品下架，系统定时检查已下架并且设计已删除的商品，检查是否有未结束的订单，删除商品
        productService.downShelve(productConvert.convertDtoToModel(productDto));
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS);
    }




}
