/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.order;

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
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderWaybill;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderWaybillService;

/**
 * 运单Controller
 * @author houyi
 * @version 2017-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/order/orderWaybill")
public class OrderWaybillController extends BaseController {

	@Autowired
	private OrderWaybillService orderWaybillService;
	
	@ModelAttribute
	public OrderWaybill get(@RequestParam(required=false) String id) {
		OrderWaybill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderWaybillService.get(id);
		}
		if (entity == null){
			entity = new OrderWaybill();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:order:orderWaybill:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderWaybill orderWaybill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderWaybill> page = orderWaybillService.findPage(new Page<OrderWaybill>(request, response), orderWaybill); 
		model.addAttribute("page", page);
		return "modules/ftc/order/orderWaybillList";
	}

	@RequiresPermissions("ftc:order:orderWaybill:view")
	@RequestMapping(value = "form")
	public String form(OrderWaybill orderWaybill, Model model) {
		model.addAttribute("orderWaybill", orderWaybill);
		return "modules/ftc/order/orderWaybillForm";
	}

	@RequiresPermissions("ftc:order:orderWaybill:edit")
	@RequestMapping(value = "save")
	public String save(OrderWaybill orderWaybill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderWaybill)){
			return form(orderWaybill, model);
		}
		orderWaybillService.save(orderWaybill);
		addMessage(redirectAttributes, "保存运单成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/order/orderWaybill/?repage";
	}
	
	@RequiresPermissions("ftc:order:orderWaybill:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderWaybill orderWaybill, RedirectAttributes redirectAttributes) {
		orderWaybillService.delete(orderWaybill);
		addMessage(redirectAttributes, "删除运单成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/order/orderWaybill/?repage";
	}

}