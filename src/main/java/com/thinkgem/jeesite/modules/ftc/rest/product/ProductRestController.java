package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.entity.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.List;

/**
 * Created by bingbing on 2017/6/5.
 */
@RestController
@RequestMapping(value = "/rest/ftc/product/")
public class ProductRestController extends BaseRestController {

    @Autowired
    private ProductService productService;
    @RequestMapping(value = {"list", ""})
    public RestResult list(Product product,int page,int rows) {
       List<Product> productList = productService.findList(product);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,productList,productList.size());
    }
    @RequestMapping(value = {"info"})
    public RestResult info(String  id){
        Product product=productService.get(id);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,product,0);
    }
}
