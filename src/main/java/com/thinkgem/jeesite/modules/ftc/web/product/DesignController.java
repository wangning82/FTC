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
import com.thinkgem.jeesite.modules.ftc.entity.product.Design;
import com.thinkgem.jeesite.modules.ftc.service.product.DesignService;

/**
 * 设计Controller
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/product/design")
public class DesignController extends BaseController {

	@Autowired
	private DesignService designService;
	
	@ModelAttribute
	public Design get(@RequestParam(required=false) String id) {
		Design entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = designService.get(id);
		}
		if (entity == null){
			entity = new Design();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:product:design:view")
	@RequestMapping(value = {"list", ""})
	public String list(Design design, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Design> page = designService.findPage(new Page<Design>(request, response), design); 
		model.addAttribute("page", page);
		return "modules/ftc/product/designList";
	}

	@RequiresPermissions("ftc:product:design:view")
	@RequestMapping(value = "form")
	public String form(Design design, Model model) {
		model.addAttribute("design", design);
		return "modules/ftc/product/designForm";
	}

	@RequiresPermissions("ftc:product:design:edit")
	@RequestMapping(value = "save")
	public String save(Design design, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, design)){
			return form(design, model);
		}
		designService.save(design);
		addMessage(redirectAttributes, "保存设计成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/design/?repage";
	}
	
	@RequiresPermissions("ftc:product:design:edit")
	@RequestMapping(value = "delete")
	public String delete(Design design, RedirectAttributes redirectAttributes) {
		designService.delete(design);
		addMessage(redirectAttributes, "删除设计成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/design/?repage";
	}

}