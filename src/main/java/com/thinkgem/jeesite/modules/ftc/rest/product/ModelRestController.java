package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.convert.ModelConverter;
import com.thinkgem.jeesite.modules.ftc.convert.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.product.ModelDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Category;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.product.PositionService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by bingbing on 2017/6/19.
 */
@RestController
@RequestMapping(value = "/rest/ftc/model/")
public class ModelRestController extends BaseRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSpecService productSpecService;
    @Autowired
    private ModelConverter modelConverter;
    @Autowired
    private PositionService positionService;
    /**
     * 模型列表
     * @param product
     * @return
     */
    @RequestMapping(value = {"list"})
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
        ModelDto dto=modelConverter.convertModelToDto(product);
        //还要获取模型的分类包含的图片位置信息
        Category category=product.getCategory();
        List<Position> positionList =positionService.findList(new Position(category));
        dto.setPositionList(positionList);
        return new RestResult(CODE_SUCCESS,
                MSG_SUCCESS,dto);
    }

}
