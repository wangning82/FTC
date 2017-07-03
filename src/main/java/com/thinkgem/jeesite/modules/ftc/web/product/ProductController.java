/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.ProductNoGenerator;
import com.thinkgem.jeesite.modules.ftc.entity.product.*;
import com.thinkgem.jeesite.modules.ftc.service.product.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品Controller
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/product/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductSpecService productSpecService;

	@Autowired
	private SpecificationService specificationService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private DesignService designService;
	@Autowired
	private ImageService imageService;
	@ModelAttribute
	public Product get(@RequestParam(required=false) String id) {
		Product entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productService.get(id);
		}
		if (entity == null){
			entity = new Product();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:product:product:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product); 
		model.addAttribute("page", page);
		return "modules/ftc/product/productList";
	}

	@RequiresPermissions("ftc:product:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {


		if(StringUtils.isEmpty(product.getId())){
			product.setNumber(ProductNoGenerator.INSTANCE.nextId());
		}else{
			ProductSpec spec=new ProductSpec();
			spec.setProductId(product.getId());
			List<ProductSpec> specs=productSpecService.findList(spec);
			product.setSpecs(specs);
			Image image=new Image();
			image.setProduct(product);
			List<Image> images=imageService.findList(image);
			product.setImages(images);

		}
		model.addAttribute("product", product);
		return "modules/ftc/product/productForm";
	}

	@RequiresPermissions("ftc:product:product:edit")
	@RequestMapping(value = "save")
	public String save(Product product, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, product)){
			return form(product, model);
		}
		productService.save(product);
		addMessage(redirectAttributes, "保存商品成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/product/?repage";
	}
	
	@RequiresPermissions("ftc:product:product:edit")
	@RequestMapping(value = "delete")
	public String delete(Product product, RedirectAttributes redirectAttributes) {
		productService.delete(product);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/product/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Product> list = productService.findList(new Product());
		for (int i=0; i<list.size(); i++){
			Product e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getCategory().getId().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getCategory().getId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}