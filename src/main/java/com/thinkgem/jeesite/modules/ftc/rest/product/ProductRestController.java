package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.convert.customer.CustomerConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.ShopDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductImageDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductSpecDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductImageService productImageService;

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
     * 获取商品列表，传入参数
     * 可以根据分类获取商品或模型
     * @return
     */
    @ApiOperation(value = "推荐列表", notes = "获取推荐商品列表")
    @RequestMapping(value = {"recommend", ""},method = { RequestMethod.GET})
    public RestResult  recommend(ProductDto goods, HttpServletRequest request, HttpServletResponse response) {
        Page<Product> page = productService.
                findPage(new Page<Product>(request, response),
                        productConvert.convertDtoToModel(goods));
        List<ProductDto> productDtoList=
                productConvert.convertListFromModelToDto(page.getList());

        return new RestResult(CODE_SUCCESS,MSG_SUCCESS,productDtoList);
    }
    /**
     * 潮人馆商品列表
     *
     * @return
     */
    @ApiOperation(value = "潮人馆商品列表", notes = "获取设计者的商品")
    @RequestMapping(value = {"findByUser", ""},method = { RequestMethod.GET})
    public RestResult findByUser(CustomerDto user, HttpServletRequest request, HttpServletResponse response) {

        Customer customer=customerService.get(user.getId());
        Product product=new Product();
        product.setDesignBy(customer);

        Page<Product> page = productService.
                findPage(new Page<Product>(request, response),
                        product);
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
    public RestResult info(@RequestParam("token") String token,@RequestParam ("id")String  id){


        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            //获取商品信息
            Product product = productService.get(id);
            //更新热度，自动加一
            productService.addHot(product);

            //获取规格信息
            List<ProductSpec> specs = productSpecService.findList(new ProductSpec(product));
            Map<String, Object> productDetail = new HashMap<String, Object>();

            List<ProductDto> goods = new ArrayList<>();
            for (ProductSpec spec : specs) {
                ProductDto dto = new ProductDto();
                dto.setId(product.getId());
                dto.setName(product.getName());
                dto.setDesc(product.getIntroduce());
                dto.setOpen(true);
                dto.setPicImg(product.getPicImg());
                dto.setPrice(spec.getPrice());
                dto.setDesignPrice(product.getDesignPrice());
                dto.setCategoryId(product.getCategory().getId());
                dto.setPriaseCount(product.getPriaseCount());
                dto.setFavouritCount(product.getFavouritCount());
                String s = spec.getSpec().getName();
                String[] attrs = s.split(",");
                List<ProductSpecDto> att = new ArrayList<>();
                for (String a : attrs) {
                    String[] as = a.split(":");
                    ProductSpecDto specDto = new ProductSpecDto(as[0], as[1]);
                    att.add(specDto);
                }
                dto.setAttrs(att);
                List<ProductImageDto> imageDtos = new ArrayList<>();
                String[] images = spec.getPicImg().split(",");
                for (int i = 0; i < images.length; i++) {
                    ProductImageDto imageDto = new ProductImageDto();
                    imageDto.setId("");
                    imageDto.setImgNailUrl(images[i]);
                    imageDto.setImgUrl(images[i]);
                    imageDtos.add(imageDto);
                }
                dto.setTextures(imageDtos);
                goods.add(dto);
            }

            Customer designer=customerService.get(product.getDesignBy().getId());
            ShopDto shop = new ShopDto();
            shop.setId(designer.getId());

            shop.setCustomerDto(customerConverter.convertModelToDto(designer));
            shop.setName(designer.getShopName());
            shop.setDesc(designer.getSignature());
            shop.setProductDtoList(goods);
            productDetail.put("good",goods.get(0));
            productDetail.put("shop",shop);
            return new RestResult(CODE_SUCCESS,
                    MSG_SUCCESS,productDetail);
        }


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
