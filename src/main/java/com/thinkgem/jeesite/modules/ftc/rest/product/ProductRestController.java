package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bingbing on 2017/6/5.
 */
@RestController
@RequestMapping(value = "/rest/ftc/product/")
public class ProductRestController {

    @RequestMapping(value = {"list", ""})
    public String list(Product product) {

        return "modules/ftc/product/productList";
    }
}
