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
import com.thinkgem.jeesite.modules.ftc.entity.product.SpecAttribute;
import com.thinkgem.jeesite.modules.ftc.service.product.SpecAttributeService;

/**
 * 规格属性Controller
 * @author wangqingxiang
 * @version 2017-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/product/specAttribute")
public class SpecAttributeController extends BaseController {

	@Autowired
	private SpecAttributeService specAttributeService;
	
	@ModelAttribute
	public SpecAttribute get(@RequestParam(required=false) String id) {
		SpecAttribute entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = specAttributeService.get(id);
		}
		if (entity == null){
			entity = new SpecAttribute();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:product:specAttribute:view")
	@RequestMapping(value = {"list", ""})
	public String list(SpecAttribute specAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpecAttribute> page = specAttributeService.findPage(new Page<SpecAttribute>(request, response), specAttribute); 
		model.addAttribute("page", page);
		return "modules/ftc/product/specAttributeList";
	}

	@RequiresPermissions("ftc:product:specAttribute:view")
	@RequestMapping(value = "form")
	public String form(SpecAttribute specAttribute, Model model) {
		model.addAttribute("specAttribute", specAttribute);
		return "modules/ftc/product/specAttributeForm";
	}

	@RequiresPermissions("ftc:product:specAttribute:edit")
	@RequestMapping(value = "save")
	public String save(SpecAttribute specAttribute, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, specAttribute)){
			return form(specAttribute, model);
		}
		specAttributeService.save(specAttribute);
		addMessage(redirectAttributes, "保存规格属性成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/specAttribute/?repage";
	}
	
	@RequiresPermissions("ftc:product:specAttribute:edit")
	@RequestMapping(value = "delete")
	public String delete(SpecAttribute specAttribute, RedirectAttributes redirectAttributes) {
		specAttributeService.delete(specAttribute);
		addMessage(redirectAttributes, "删除规格属性成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/specAttribute/?repage";
	}

}