/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.product;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ftc.entity.product.Image;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductImage;
import com.thinkgem.jeesite.modules.ftc.service.product.ProductImageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 图片Controller
 * @author wangqingxiang
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/product/image")
public class ProductImageController extends BaseController {

	@Autowired
	private ProductImageService productImageService;
	
	@ModelAttribute
	public ProductImage get(@RequestParam(required=false) String id) {
		ProductImage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productImageService.get(id);
		}
		if (entity == null){
			entity = new ProductImage();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:product:image:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductImage image, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductImage> page = productImageService.findPage(new Page<ProductImage>(request, response), image);
		model.addAttribute("page", page);
		return "modules/ftc/product/imageList";
	}

	@RequiresPermissions("ftc:product:image:view")
	@RequestMapping(value = "form")
	public String form(ProductImage image, Model model) {
		model.addAttribute("image", image);
		return "modules/ftc/product/imageForm";
	}

	@RequiresPermissions("ftc:product:image:edit")
	@RequestMapping(value = "save")
	public String save(ProductImage image, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, image)){
			return form(image, model);
		}
		productImageService.save(image);
		addMessage(redirectAttributes, "保存图片成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/image/?repage";
	}
	
	@RequiresPermissions("ftc:product:image:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductImage image, RedirectAttributes redirectAttributes) {
		productImageService.delete(image);
		addMessage(redirectAttributes, "删除图片成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/image/?repage";
	}

}