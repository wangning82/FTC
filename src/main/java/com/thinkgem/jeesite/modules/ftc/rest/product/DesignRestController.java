package com.thinkgem.jeesite.modules.ftc.rest.product;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.common.utils.ImageUtils;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.modules.ftc.constant.ImgSourceEnum;
import com.thinkgem.jeesite.modules.ftc.convert.product.*;
import com.thinkgem.jeesite.modules.ftc.dto.customer.ShopDto;
import com.thinkgem.jeesite.modules.ftc.dto.product.*;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.*;
import com.thinkgem.jeesite.modules.ftc.service.product.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

import static com.ckfinder.connector.ServletContextFactory.getServletContext;

/**
 * Created by wangqingxiang on 2017/6/8.
 */
@RestController
@RequestMapping(value = "/rest/ftc/design/")
@Api(value = "设计管理", description = "设计管理")
public class DesignRestController extends BaseRestController {
    @Autowired
    private DesignService designService;
    @Autowired
    private DesignConverter designConverter;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSpecService productSpecService;
    @Autowired
    private ModelConverter modelConverter;
    @Autowired
    private PositionService positionService;
    @Autowired
    private PositionConverter positionConverter;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private DesignSeedConverter designSeedConverter;
    @Autowired
    private DesignDetailService designDetailService;
    PropertiesLoader loader=new PropertiesLoader("jeesite.properties");
    String serverName=loader.getProperty("serverName");

    /**
     * 删除
     *
     * @param
     * @return
     */
    @ApiOperation(value = "删除设计", notes = "删除设计")
    @RequestMapping(value = {"delete"}, method = {RequestMethod.POST})
    public RestResult delete(Design design) {
        //删除设计
        designService.delete(design);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
    }


    /**
     * 我的设计
     *
     * @param
     * @return
     */
    @ApiOperation(value = "我的设计", notes = "获取我的设计列表")
    @RequestMapping(value = {"list"}, method = {RequestMethod.POST})
    public RestResult mylist(@RequestParam("token") String token, HttpServletRequest request,HttpServletResponse response) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Design design=new Design();
            design.setCustomer(customer);
            Page<Design> page = designService.findPage(new Page<Design>(request, response), design);
            List<Design> designList=page.getList();
            List<DesignDto> designDtoList=designConverter.convertListFromModelToDto(designList);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, designDtoList);
        }
    }
    /**
     * 优秀设计
     *
     * @param
     * @return
     */
    @ApiOperation(value = "素材列表", notes = "获取素材列表")
    @RequestMapping(value = {"sucai"}, method = {RequestMethod.POST})
    public RestResult sucai(HttpServletRequest request) {


        int pageSize = 10;
        int pageNo = 1;
        String size = request.getParameter("pageSize");
        if (size != null && size.length() > 0) {
            pageSize = Integer.parseInt(size);
        }
        String no = request.getParameter("pageNo");
        if (no != null && no.length() > 0) {
            pageNo = Integer.parseInt(no);
        }
        int startNum = pageSize * (pageNo - 1);
        int endNum = pageSize * pageNo - 1;
        try {
            List<String> imageFiles = getImageList(getServletContext().getRealPath("/upload"));
            if (imageFiles.size() < (pageSize * (pageNo - 1))) {
                return new RestResult(CODE_NULL, "图片数量不足", null);
            } else {
                if (imageFiles.size() < pageSize * pageNo) {
                    endNum = pageSize * (pageNo - 1) + imageFiles.size() % pageSize;
                }
            }
            List<ProductImageDto> images=new ArrayList<>();
            for (int i = startNum; i < endNum; i++) {
                ProductImageDto image=new ProductImageDto();

                String imgUrl = imageFiles.get(i);
                image.setImgUrl( imgUrl);
                images.add(image);
            }
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, images);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return  new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    private List<String> getImageList(String realPath) {

        File file = new File(realPath);
        List<String> imageFiles = new ArrayList<>();
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    File[] files2 = file1.listFiles();
                    for (File file2 : files2) {
                        //只取素材文件夹的图
                        if (file2.isDirectory() && file2.getName().equals("sucai")) {
                            File[] files3 = file2.listFiles();
                            for (File file3 : files3) {
                                String fileUrl = serverName+"/upload/"+ file1.getName()
                                        + "/" + file2.getName() + "/" +
                                        file3.getName();
                                imageFiles.add(fileUrl);
                            }
                        }
                    }
                }

            }

        }
        return imageFiles;
    }

    /**
     * @param
     * @return
     */
    @ApiOperation(value = "我的素材", notes = "我的素材")
    @RequestMapping(value = {"myImage"}, method = {RequestMethod.POST})
    public RestResult myImage(@RequestParam("token") String token, HttpServletRequest request) {

        Customer customer = findCustomerByToken(token);
        if (customer == null)
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");

        int pageSize = 10;
        int pageNo = 1;
        String size = request.getParameter("pageSize");
        if (size != null && size.length() > 0) {
            pageSize = Integer.parseInt(size);
        }
        String no = request.getParameter("pageNo");
        if (no != null && no.length() > 0) {
            pageNo = Integer.parseInt(no);
        }
        int startNum = pageSize * (pageNo - 1);
        int endNum = pageSize * pageNo - 1;


        try {
            String realPath = getServletContext().getRealPath("/upload") + "/"
                    + customer.getId() + "/sucai";
            File file = new File(realPath);
            if (file.exists()) {
                File[] files = file.listFiles();
                List<Map<String, String>> images = new ArrayList<>();
                if (files.length < (pageSize * (pageNo - 1))) {
                    return new RestResult(CODE_NULL, "图片数量不足", null);
                } else {
                    if (files.length < pageSize * pageNo) {
                        endNum = pageSize * (pageNo - 1) + files.length % pageSize;
                    }
                }
                for (int i = startNum; i < endNum; i++) {
                    Map<String, String> image = new HashMap<>();
                    File file2 = files[i];
                    if (!file2.isDirectory()) {
                        image.put("imgUrl", serverName+"/upload/" + customer.getId() + "/material/" + file2.getName());
                    }
                    images.add(image);
                }
                return new RestResult(CODE_SUCCESS, MSG_SUCCESS, images);
            } else {
                return new RestResult(CODE_ERROR, "个人目录下没有素材");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResult(CODE_SUCCESS, "没有找到素材");
    }

    /**
     * 修改设计
     *
     * @param
     * @return
     */
    @ApiOperation(value = "修改设计", notes = "对设计进行修改")
    @RequestMapping(value = {"modify"}, method = {RequestMethod.POST})
    public RestResult modify(DesignDto design) {

        Design designModel = designService.get(design.getId());
        designModel.setName(design.getName());
        designModel.setUpdateDate(new Date());
        designService.save(designModel);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
    }


    /**
     * 设计明细
     *
     * @param
     * @return
     */

    public RestResult info(String id) {

        Design design = designService.get(id);
        if(design==null){
            return null;
        }
        DesignDetail designDetail=new DesignDetail();
        designDetail.setDesign(design);
        List<DesignDetail> designDetails=designDetailService.findList(designDetail);
        design.setDetails(designDetails);

        List<DesignSeed> seeds=designService.findSeeds(design);
        design.setSeeds(seeds);
        DesignDto dto=designConverter.convertModelToDto(design);
        //获取模型信息
        Product product = productService.get(design.getModel().getId());

        //获取规格信息
        List<ProductSpec> specs = productSpecService.findList(new ProductSpec(product));
        //获取规格图片信息
        for (int i = 0; i < specs.size(); i++) {
            ProductSpec spec=specs.get(i);
            if("1".equals(spec.getDefaultStatus())){
                ProductImage image = new ProductImage();
                image.setProductSpec(spec);
                List<ProductImage> images = productImageService.findList(image);
                spec.setImages(images);
            }

        }
        product.setSpecs(specs);
        ModelDto good = modelConverter.convertModelToDto(product);
        dto.setModel(good);




        return new RestResult(CODE_SUCCESS, MSG_SUCCESS,dto );
    }


    /**
     * 保存设计
     *
     * @param
     * @return
     */
    @ApiOperation(value = "上传图片", notes = "上传图片")
    @RequestMapping(value = {"uploadImg"}, method = {RequestMethod.POST})
    public RestResult uploadImg(@RequestParam("img") String img, @RequestParam("token") String
            token, HttpServletRequest request) {


//        img = "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";
        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + contextPath + "/upload/";
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            img = basePath + ImageUtils.generateImg(img, customer.getId(), ImgSourceEnum.IMG_SOURCE_SUCAI.getValue());
            ProductImageDto image=new ProductImageDto();
            image.setImgUrl(img);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, image);
        }
    }

    /**
     * 保存设计
     *
     * @param
     * @return
     */
    @ApiOperation(value = "保存设计", notes = "获取设计的详情")
    @RequestMapping(value = {"save"}, method = {RequestMethod.POST})
    public RestResult save(@RequestParam String design, @RequestParam("token") String token,HttpServletRequest request) {

        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            DesignDto design1= objectMapper.readValue(design, DesignDto.class);
            Design model = designConverter.convertDtoToModel(design1);
            model.setDesignStatus("0");
            model.setCustomer(customer);
            designService.saveForRest(model);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, model.getId());
        }catch (Exception e){
            e.printStackTrace();
        }

        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);

    }

    /**
     * 我要设计
     *
     * @param
     * @return
     */
    @ApiOperation(value = "我要设计", notes = "我要设计，获取模型信息")
    @RequestMapping(value = {"iwant"}, method = {RequestMethod.POST})
    public RestResult iWant(@RequestParam("token") String token,@RequestParam("id") String id,  HttpServletRequest request) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        }

        if (id == null || id.equals("")||id.equals("0")) {
            return iWant(request);
        } else {
            return info(id);
        }

    }

    public RestResult iWant(HttpServletRequest request) {

        //我要设计，默认取第一个模型
        Product product=new Product();
        product.setModelFlag("1");
        List<Product> modelList=productService.findList(product);
        if(modelList==null||modelList.size()==0){
            return new RestResult(CODE_ERROR,"没有找到模型，请联系管理员");
        }
        product = productService.get(modelList.get(0));

        DesignDto dto=new DesignDto();
        product = productService.get(product.getId());
        ProductSpec spec=new ProductSpec();
        spec.setDefaultStatus("1");
        spec.setProduct(product);
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
        ModelDto modelDto = modelConverter.convertModelToDto(product);

        dto.setModel(modelDto);

        try{

            List<String> images=getImageList( getServletContext().getRealPath("/upload"));

            List<DesignSeedDto> seeds=new ArrayList<>();
            int size=4;
            if(images.size()<4){
                size=images.size();
            }
            for(int i=0;i<size;i++){
                DesignSeedDto seed=new DesignSeedDto();
                seed.setImgUrl(images.get(i));
                seed.setImgNailUrl(images.get(i));
                seeds.add(seed);
            }
            dto.setSeeds(seeds);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new RestResult(CODE_SUCCESS,
                MSG_SUCCESS, dto);
    }

}
