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
import com.thinkgem.jeesite.modules.ftc.entity.order.OrderShipment;
import com.thinkgem.jeesite.modules.ftc.service.order.OrderShipmentService;

/**
 * 订单配送Controller
 * @author houyi
 * @version 2017-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/order/orderShipment")
public class OrderShipmentController extends BaseController {

	@Autowired
	private OrderShipmentService orderShipmentService;
	
	@ModelAttribute
	public OrderShipment get(@RequestParam(required=false) String id) {
		OrderShipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderShipmentService.get(id);
		}
		if (entity == null){
			entity = new OrderShipment();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:order:orderShipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderShipment orderShipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderShipment> page = orderShipmentService.findPage(new Page<OrderShipment>(request, response), orderShipment); 
		model.addAttribute("page", page);
		return "modules/ftc/order/orderShipmentList";
	}

	@RequiresPermissions("ftc:order:orderShipment:view")
	@RequestMapping(value = "form")
	public String form(OrderShipment orderShipment, Model model) {
		model.addAttribute("orderShipment", orderShipment);
		return "modules/ftc/order/orderShipmentForm";
	}

	@RequiresPermissions("ftc:order:orderShipment:edit")
	@RequestMapping(value = "save")
	public String save(OrderShipment orderShipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderShipment)){
			return form(orderShipment, model);
		}
		orderShipmentService.save(orderShipment);
		addMessage(redirectAttributes, "保存订单配送成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/order/orderShipment/?repage";
	}
	
	@RequiresPermissions("ftc:order:orderShipment:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderShipment orderShipment, RedirectAttributes redirectAttributes) {
		orderShipmentService.delete(orderShipment);
		addMessage(redirectAttributes, "删除订单配送成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/order/orderShipment/?repage";
	}

}