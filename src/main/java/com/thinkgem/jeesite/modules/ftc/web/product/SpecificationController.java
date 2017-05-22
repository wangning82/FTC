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
import com.thinkgem.jeesite.modules.ftc.entity.product.Specification;
import com.thinkgem.jeesite.modules.ftc.service.product.SpecificationService;

/**
 * 规格Controller
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/product/specification")
public class SpecificationController extends BaseController {

	@Autowired
	private SpecificationService specificationService;
	
	@ModelAttribute
	public Specification get(@RequestParam(required=false) String id) {
		Specification entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = specificationService.get(id);
		}
		if (entity == null){
			entity = new Specification();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:product:specification:view")
	@RequestMapping(value = {"list", ""})
	public String list(Specification specification, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Specification> page = specificationService.findPage(new Page<Specification>(request, response), specification); 
		model.addAttribute("page", page);
		return "modules/ftc/product/specificationList";
	}

	@RequiresPermissions("ftc:product:specification:view")
	@RequestMapping(value = "form")
	public String form(Specification specification, Model model) {
		model.addAttribute("specification", specification);
		return "modules/ftc/product/specificationForm";
	}

	@RequiresPermissions("ftc:product:specification:edit")
	@RequestMapping(value = "save")
	public String save(Specification specification, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, specification)){
			return form(specification, model);
		}
		specificationService.save(specification);
		addMessage(redirectAttributes, "保存规格成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/specification/?repage";
	}
	
	@RequiresPermissions("ftc:product:specification:edit")
	@RequestMapping(value = "delete")
	public String delete(Specification specification, RedirectAttributes redirectAttributes) {
		specificationService.delete(specification);
		addMessage(redirectAttributes, "删除规格成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/specification/?repage";
	}

}