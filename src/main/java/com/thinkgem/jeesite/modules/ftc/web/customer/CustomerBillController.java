/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.customer;

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
import com.thinkgem.jeesite.modules.ftc.entity.customer.CustomerBill;
import com.thinkgem.jeesite.modules.ftc.service.customer.CustomerBillService;

/**
 * 账单Controller
 * @author houyi
 * @version 2017-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/customer/customerBill")
public class CustomerBillController extends BaseController {

	@Autowired
	private CustomerBillService customerBillService;
	
	@ModelAttribute
	public CustomerBill get(@RequestParam(required=false) String id) {
		CustomerBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = customerBillService.get(id);
		}
		if (entity == null){
			entity = new CustomerBill();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:customer:customerBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(CustomerBill customerBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CustomerBill> page = customerBillService.findPage(new Page<CustomerBill>(request, response), customerBill); 
		model.addAttribute("page", page);
		return "modules/ftc/customer/customerBillList";
	}

	@RequiresPermissions("ftc:customer:customerBill:view")
	@RequestMapping(value = "form")
	public String form(CustomerBill customerBill, Model model) {
		model.addAttribute("customerBill", customerBill);
		return "modules/ftc/customer/customerBillForm";
	}

	@RequiresPermissions("ftc:customer:customerBill:edit")
	@RequestMapping(value = "save")
	public String save(CustomerBill customerBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, customerBill)){
			return form(customerBill, model);
		}
		customerBillService.save(customerBill);
		addMessage(redirectAttributes, "保存账单成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/customer/customerBill/?repage";
	}
	
	@RequiresPermissions("ftc:customer:customerBill:edit")
	@RequestMapping(value = "delete")
	public String delete(CustomerBill customerBill, RedirectAttributes redirectAttributes) {
		customerBillService.delete(customerBill);
		addMessage(redirectAttributes, "删除账单成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/customer/customerBill/?repage";
	}

}