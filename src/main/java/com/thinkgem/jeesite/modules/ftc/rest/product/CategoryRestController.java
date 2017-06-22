package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.Category;
import com.thinkgem.jeesite.modules.ftc.service.product.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bingbing on 2017/6/8.
 */
@RestController
@RequestMapping(value = "/rest/ftc/category/")
@Api(value = "分类管理", description = "获取分类信息")
public class CategoryRestController extends BaseRestController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = {"list", ""})
    public RestResult list(Category category, int page, int rows) {
        List<Category> categoryList = categoryService.findList(category);
        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,categoryList);
    }
}
