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
import com.thinkgem.jeesite.modules.ftc.entity.advert.AdvertDetail;
import com.thinkgem.jeesite.modules.ftc.service.advert.AdvertDetailService;

/**
 * 广告Controller
 * @author wangqingxiang
 * @version 2017-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/advert/detail")
public class AdvertDetailController extends BaseController {

	@Autowired
	private AdvertDetailService advertDetailService;
	
	@ModelAttribute
	public AdvertDetail get(@RequestParam(required=false) String id) {
		AdvertDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = advertDetailService.get(id);
		}
		if (entity == null){
			entity = new AdvertDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:advert:detail:view")
	@RequestMapping(value = {"list", ""})
	public String list(AdvertDetail advertDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AdvertDetail> page = advertDetailService.findPage(new Page<AdvertDetail>(request, response), advertDetail); 
		model.addAttribute("page", page);
		return "modules/ftc/advert/advertDetailList";
	}

	@RequiresPermissions("ftc:advert:detail:view")
	@RequestMapping(value = "form")
	public String form(AdvertDetail advertDetail, Model model) {
		model.addAttribute("advertDetail", advertDetail);
		return "modules/ftc/advert/advertDetailForm";
	}

	@RequiresPermissions("ftc:advert:detail:edit")
	@RequestMapping(value = "save")
	public String save(AdvertDetail advertDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, advertDetail)){
			return form(advertDetail, model);
		}
		advertDetailService.save(advertDetail);
		addMessage(redirectAttributes, "保存广告成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/advert/detail/?repage";
	}
	
	@RequiresPermissions("ftc:advert:detail:edit")
	@RequestMapping(value = "delete")
	public String delete(AdvertDetail advertDetail, RedirectAttributes redirectAttributes) {
		advertDetailService.delete(advertDetail);
		addMessage(redirectAttributes, "删除广告成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/advert/detail/?repage";
	}

}