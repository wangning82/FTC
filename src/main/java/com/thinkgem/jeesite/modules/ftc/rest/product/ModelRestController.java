package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.convert.product.ModelConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.PositionConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.ShopDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.PositionDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.*;
import com.thinkgem.jeesite.modules.ftc.service.product.PositionService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductImageService;
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
import java.util.ArrayList;
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
    private ModelConverter modelConverter;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private ProductSpecService productSpecService;
    @Autowired
    private ProductConverter productConverter;
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
    @RequestMapping(value = {"list"},method = { RequestMethod.POST})
    public RestResult model(ModelDto modelDto, HttpServletRequest request, HttpServletResponse response) {
        //只查询是模型的数据
        Product product=modelConverter.convertDtoToModel(modelDto);
        product.setModelFlag("1");
        Page<Product> page = productService.
                findPage(new Page<Product>(request, response),
                        product);
        List<Product> productList = page.getList();
        List<ModelDto> models = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
             product = productList.get(i);
            List<ProductSpec> specs = productSpecService.findList(new ProductSpec(product));
            for (int j = 0; j < specs.size(); j++) {
                ProductSpec spec = specs.get(j);
                if ("1".equals(spec.getDefaultStatus())) {
                    ProductImage image = new ProductImage();
                    image.setProductSpec(spec);
                    List<ProductImage> images = productImageService.findList(image);
                    spec.setImages(images);
                }
            }
            product.setSpecs(specs);

            ModelDto model = modelConverter.convertModelToDto(product);
            models.add(model);

        }
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,models);
    }
    /**
     * 模型列表
     * @param id
     * @return
     */
    @ApiOperation(value = "模型明细", notes = "获取单个模型")
    @RequestMapping(value = {"info"},method = { RequestMethod.POST})
    public RestResult info(@RequestParam("id") String id) {
        //只查询是模型的数据
        //获取商品信息
        Product product = productService.get(id);
        //获取规格信息
        List<ProductSpec> specs = productSpecService.findList(new ProductSpec(product));
        //获取规格图片信息
        for (int i = 0; i < specs.size(); i++) {
            ProductImage image = new ProductImage();
            image.setProductSpec(specs.get(i));
            List<ProductImage> images = productImageService.findList(image);
            specs.get(i).setImages(images);
        }

        product.setSpecs(specs);
        ModelDto model = modelConverter.convertModelToDto(product);


        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,model);
    }

}
