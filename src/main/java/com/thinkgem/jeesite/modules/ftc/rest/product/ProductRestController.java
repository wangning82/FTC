package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.convert.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/5.
 */
@RestController
@RequestMapping(value = "/rest/ftc/product/")
public class ProductRestController extends BaseRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSpecService productSpecService;
    @Autowired
    private ProductConverter productConvert;

    /**
     * 获取商品列表，传入参数
     * 可以根据分类获取商品或模型
     * @return
     */
    @RequestMapping(value = {"list", ""})
    public RestResult list(ProductDto productDto, HttpServletRequest request, HttpServletResponse response) {
        Page<Product> page = productService.
                findPage(new Page<Product>(request, response),
                        productConvert.convertDtoToModel(productDto));
        List<ProductDto> productDtoList=
                productConvert.convertListFromModelToDto(page.getList());
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,productDtoList);
    }

    /**
     * 获取商品信息
     * @param id
     * @return
     */
    @RequestMapping(value = {"info"})
    public RestResult info(String  id){
        Product product=productService.get(id);
        List<ProductSpec> specs=productSpecService.findList(new ProductSpec(product));
        product.setSpecs(specs);
        return new RestResult(CODE_SUCCESS,
                MSG_SUCCESS,productConvert.convertModelToDto(product));
    }

    /**
     * 保存
     * @param productDto
     * @return
     */
    @RequestMapping(value = {"save"})
    public RestResult save(ProductDto  productDto){
        productService.save(productConvert.convertDtoToModel(productDto));
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,null);
    }

    /**
     * 删除
     * @param productDto
     * @return
     */
    @RequestMapping(value = {"delete"})
    public RestResult delete(ProductDto  productDto){
        //删除设计，产品下架，系统定时检查已下架并且设计已删除的商品，检查是否有未结束的订单，删除商品

        productService.delete(productConvert.convertDtoToModel(productDto));
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS);
    }

    /**
     * 模型列表
     * @param product
     * @return
     */
    @RequestMapping(value = {"model"})
    public RestResult model(Product product, HttpServletRequest request, HttpServletResponse response) {
        //只查询是模型的数据
        product.setModelFlag("1");
        Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,page);
    }
    /**
     * 我要设计
     * @param
     * @return
     */
    @RequestMapping(value = {"iwant"})
    public RestResult iWant(){
        //我要设计，默认取第一个模型
        Product product=new Product();
        product.setModelFlag("1");
        List<Product> list=productService.findList(product);
        if(list!=null&&list.size()>0){
            product=list.get(0);
        }
        return new RestResult(CODE_SUCCESS,
                MSG_SUCCESS,productConvert.convertModelToDto(product));
    }


}
