package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.modules.ftc.convert.customer.CustomerConverter;
import com.thinkgem.jeesite.modules.ftc.convert.customer.ShopConverter;
import com.thinkgem.jeesite.modules.ftc.convert.product.ProductConverter;
import com.thinkgem.jeesite.modules.ftc.dto.customer.AddressDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.ShopDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductImageDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductSpecDto;
import com.thinkgem.jeesite.modules.ftc.entity.comment.Comment;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Wishlist;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.comment.CommentService;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerService;
import com.thinkgem.jeesite.modules.ftc.service.customer.WishlistService;
import com.thinkgem.jeesite.modules.ftc.service.product.PositionService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductImageService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductService;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private CustomerService customerService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private ShopConverter shopConverter;
    @Autowired
    private CommentService commentService;
    /**
     * 获取商品列表，传入参数
     * 可以根据分类获取商品或模型
     *
     * @return
     */
    @ApiOperation(value = "商品列表", notes = "获取商品列表")
    @RequestMapping(value = {"list", ""}, method = {RequestMethod.POST})
    public RestResult list(ProductDto good, HttpServletRequest request, HttpServletResponse response) {
        Page<Product> page = productService.
                findPage(new Page<Product>(request, response),
                        productConvert.convertDtoToModel(good));

        List<Product> productList = page.getList();
        List<ShopDto> shopDtoList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
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

            ShopDto shop = shopConverter.convertModelToDto(product.getDesignBy());
            ProductDto resultGood = productConvert.convertModelToDto(product);
            List<ProductDto> goods = new ArrayList<>();
            goods.add(resultGood);
            shop.setGoods(goods);
            shopDtoList.add(shop);
        }
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, shopDtoList);
    }

    /**
     * 获取商品列表，传入参数
     * 可以根据分类获取商品或模型
     *
     * @return
     */
    @ApiOperation(value = "推荐列表", notes = "获取推荐商品列表")
    @RequestMapping(value = {"recommend", ""}, method = {RequestMethod.POST})
    public RestResult recommend(ProductDto good, HttpServletRequest request, HttpServletResponse response) {
        Page<Product> page = productService.
                findPage(new Page<Product>(request, response),
                        productConvert.convertDtoToModel(good));

        List<Product> productList = page.getList();
        List<ShopDto> shopDtoList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
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

            ShopDto shop = shopConverter.convertModelToDto(product.getDesignBy());
            ProductDto resultGood = productConvert.convertModelToDto(product);
            List<ProductDto> goods = new ArrayList<>();
            goods.add(resultGood);
            shop.setGoods(goods);
            shopDtoList.add(shop);
        }
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, shopDtoList);
    }

    /**
     * 潮人馆商品列表
     *
     * @return
     */
    @ApiOperation(value = "潮人馆商品列表", notes = "获取设计者的商品")
    @RequestMapping(value = {"findByUser", ""}, method = {RequestMethod.POST})
    public RestResult findByUser(@RequestParam("token") String token,@RequestParam("uid") String userId, HttpServletRequest request, HttpServletResponse response) {

        Customer customer = customerService.get(userId);

        if(customer==null){
            return new RestResult(CODE_NULL, "没有找到用户信息");
        }
        Product product = new Product();
        product.setDesignBy(customer);
        Page<Product> productList=productService.findListWithSpec(new Page<Product>(request,response),product);


        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, productConvert.convertListFromModelToDto(productList.getList()));
    }


    /**
     * 获取商品信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"info"}, method = {RequestMethod.POST})
    @ApiOperation(value = "商品详情", notes = "获取商品详细信息")
    public RestResult info(@RequestParam("token") String token, @RequestParam("id") String id) {


        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            //获取商品信息
            Product product = productService.getWithSpec(id);
            if(product==null){
                return new RestResult(CODE_NULL, "您要找的商品不存在");
            }
            //更新热度，自动加一
            productService.addHot(product);


            //获取店铺信息
            Customer designer = customerService.get(product.getDesignBy().getId());
            ShopDto shop = shopConverter.convertModelToDto(designer);
            Product product1=new Product();
            product1.setDesignBy(designer);
            List<Product> productList=productService.findList(product);
            shop.setTotal((long)productList.size());

            ProductDto good = productConvert.convertModelToDto(product);
            product.setDesignBy(designer);

            //获取客户点赞和关注信息
            Wishlist wishlist = new Wishlist();
            wishlist.setCustomer(customer);
            wishlist.setProduct(product);
            List<Wishlist> wishlist1= wishlistService.findList(wishlist);
            if (wishlist1==null||wishlist1.size()==0) {
                good.setFavourited(false);
            } else {
                good.setFavourited(true);
            }
            //获取客户点赞信息
            Comment comment = new Comment();
            comment.setProduct(product);
            comment.setCustomer(customer);
            comment.setType("1");
            List<Comment> commentList= commentService.findList(comment);
            if (commentList == null||commentList.size()==0) {
                good.setPraised(false);
            } else {
                good.setPraised(true);
            }
            Map<String, Object> result = new HashMap<>();
            result.put("shop", shop);
            result.put("good", good);


            return new RestResult(CODE_SUCCESS,
                    MSG_SUCCESS, result);
        }


    }


    /**
     * 保存
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"save"}, method = {RequestMethod.POST})
    @ApiOperation(value = "保存商品", notes = "保存商品")
    public RestResult save(@RequestParam("good") String good,@RequestParam("token") String token) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            ProductDto productDto= objectMapper.readValue(good, ProductDto.class);
            //将dto转成model
            Product product=productConvert.convertDtoToModel(productDto);
            product.setDesignBy(customer);
            product.setPraiseCount(0);
            product.setFavouriteCount(0);
            product.setRemarks("客户生成");
            productService.saveForRest(product);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, product.getId());
        }catch (Exception e){
            e.printStackTrace();
            return new RestResult(CODE_ERROR, e.getMessage());

        }
    }

    /**
     * 删除
     *
     * @param productDto
     * @return
     */
    @RequestMapping(value = {"delete"}, method = {RequestMethod.POST})
    @ApiOperation(value = "删除商品", notes = "删除商品信息")
    public RestResult delete(ProductDto productDto) {
        //删除设计，产品下架，系统定时检查已下架并且设计已删除的商品，检查是否有未结束的订单，删除商品
        productService.downShelve(productConvert.convertDtoToModel(productDto));
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
    }


}
