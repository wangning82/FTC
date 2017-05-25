/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductSpecService;

/**
 * 商品规格Controller
 * @author wangqignxiang
 * @version 2017-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/product/productSpec")
public class ProductSpecController extends BaseController {

	@Autowired
	private ProductSpecService productSpecService;
	
	@ModelAttribute
	public ProductSpec get(@RequestParam(required=false) String id) {
		ProductSpec entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productSpecService.get(id);
		}
		if (entity == null){
			entity = new ProductSpec();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:product:productSpec:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductSpec productSpec, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductSpec> page = productSpecService.findPage(new Page<ProductSpec>(request, response), productSpec); 
		model.addAttribute("page", page);
		return "modules/ftc/product/productSpecList";
	}

	@RequiresPermissions("ftc:product:productSpec:view")
	@RequestMapping(value = "form")
	public String form(ProductSpec productSpec, Model model) {
		model.addAttribute("productSpec", productSpec);
		return "modules/ftc/product/productSpecForm";
	}

	@RequiresPermissions("ftc:product:productSpec:edit")
	@RequestMapping(value = "save")
	public String save(ProductSpec productSpec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productSpec)){
			return form(productSpec, model);
		}
		productSpecService.save(productSpec);
		addMessage(redirectAttributes, "保存商品规格成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/productSpec/?repage";
	}
	
	@RequiresPermissions("ftc:product:productSpec:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductSpec productSpec, RedirectAttributes redirectAttributes) {
		productSpecService.delete(productSpec);
		addMessage(redirectAttributes, "删除商品规格成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/productSpec/?repage";
	}

}