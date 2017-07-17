/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

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
import com.thinkgem.jeesite.modules.oa.entity.Salary;
import com.thinkgem.jeesite.modules.oa.service.SalaryService;

/**
 * 工资Controller
 * @author wangqignxiang
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/salary")
public class SalaryController extends BaseController {

	@Autowired
	private SalaryService salaryService;
	
	@ModelAttribute
	public Salary get(@RequestParam(required=false) String id) {
		Salary entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = salaryService.get(id);
		}
		if (entity == null){
			entity = new Salary();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:salary:view")
	@RequestMapping(value = {"list", ""})
	public String list(Salary salary, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Salary> page = salaryService.findPage(new Page<Salary>(request, response), salary); 
		model.addAttribute("page", page);
		return "modules/oa/salaryList";
	}

	@RequiresPermissions("oa:salary:view")
	@RequestMapping(value = "form")
	public String form(Salary salary, Model model) {
		model.addAttribute("salary", salary);
		return "modules/oa/salaryForm";
	}

	@RequiresPermissions("oa:salary:edit")
	@RequestMapping(value = "save")
	public String save(Salary salary, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, salary)){
			return form(salary, model);
		}
		salaryService.save(salary);
		addMessage(redirectAttributes, "保存工资成功");
		return "redirect:"+Global.getAdminPath()+"/oa/salary/?repage";
	}
	
	@RequiresPermissions("oa:salary:edit")
	@RequestMapping(value = "delete")
	public String delete(Salary salary, RedirectAttributes redirectAttributes) {
		salaryService.delete(salary);
		addMessage(redirectAttributes, "删除工资成功");
		return "redirect:"+Global.getAdminPath()+"/oa/salary/?repage";
	}

}