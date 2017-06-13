package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.web.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.rest.entity.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bingbing on 2017/6/5.
 */
@RestController
@RequestMapping(value = "/rest/ftc/product/")
public class ProductRestController extends BaseRestController {



    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSpecService productSpecService;

    /**
     * 获取商品列表，传入参数
     * 可以根据分类获取商品或模型
     * @param product
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = {"list", ""})
    public RestResult list(Product product,int page,int rows) {
       List<Product> productList = productService.findList(product);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,productList,productList.size());
    }

    /**
     * 获取商品信息
     * @param id
     * @return
     */
    @RequestMapping(value = {"info"})
    public RestResult info(String  id){
        Product product=productService.get(id);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,product,1);
    }

    /**
     * 获取商品的规格列表
     * @param product
     * @return
     */
    @RequestMapping(value = {"spec"})
    public RestResult spec(Product  product){
       List<ProductSpec> specs=productSpecService.findList(new ProductSpec(product));
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,specs,specs.size());
    }




    /**
     * 保存
     * @param product
     * @return
     */
    @RequestMapping(value = {"save"})
    public RestResult save(Product  product){
        List<ProductSpec> specs=productSpecService.findList(new ProductSpec(product));
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,specs,specs.size());
    }

    /**
     * 删除
     * @param product
     * @return
     */
    @RequestMapping(value = {"delete"})
    public RestResult delete(Product  product){
        //删除设计，产品下架，系统定时检查已下架并且设计已删除的商品，检查是否有未结束的订单，删除商品
        productService.delete(product);

        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,null,0);
    }

}
