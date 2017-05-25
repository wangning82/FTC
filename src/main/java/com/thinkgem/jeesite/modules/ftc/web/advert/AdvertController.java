/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.advert;

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
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.service.advert.AdvertService;

/**
 * 广告位Controller
 * @author wangqingxiang
 * @version 2017-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/advert/advert")
public class AdvertController extends BaseController {

	@Autowired
	private AdvertService advertService;
	
	@ModelAttribute
	public Advert get(@RequestParam(required=false) String id) {
		Advert entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = advertService.get(id);
		}
		if (entity == null){
			entity = new Advert();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:advert:advert:view")
	@RequestMapping(value = {"list", ""})
	public String list(Advert advert, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Advert> page = advertService.findPage(new Page<Advert>(request, response), advert); 
		model.addAttribute("page", page);
		return "modules/ftc/advert/advertList";
	}

	@RequiresPermissions("ftc:advert:advert:view")
	@RequestMapping(value = "form")
	public String form(Advert advert, Model model) {
		model.addAttribute("advert", advert);
		return "modules/ftc/advert/advertForm";
	}

	@RequiresPermissions("ftc:advert:advert:edit")
	@RequestMapping(value = "save")
	public String save(Advert advert, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, advert)){
			return form(advert, model);
		}
		advertService.save(advert);
		addMessage(redirectAttributes, "保存广告位成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/advert/advert/?repage";
	}
	
	@RequiresPermissions("ftc:advert:advert:edit")
	@RequestMapping(value = "delete")
	public String delete(Advert advert, RedirectAttributes redirectAttributes) {
		advertService.delete(advert);
		addMessage(redirectAttributes, "删除广告位成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/advert/advert/?repage";
	}

}