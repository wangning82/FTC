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
import com.thinkgem.jeesite.modules.ftc.entity.order.ShoppingCart;
import com.thinkgem.jeesite.modules.ftc.service.order.ShoppingCartService;

/**
 * 购物车Controller
 * @author houyi
 * @version 2017-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/order/shoppingCart")
public class ShoppingCartController extends BaseController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@ModelAttribute
	public ShoppingCart get(@RequestParam(required=false) String id) {
		ShoppingCart entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shoppingCartService.get(id);
		}
		if (entity == null){
			entity = new ShoppingCart();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:order:shoppingCart:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShoppingCart shoppingCart, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShoppingCart> page = shoppingCartService.findPage(new Page<ShoppingCart>(request, response), shoppingCart); 
		model.addAttribute("page", page);
		return "modules/ftc/order/shoppingCartList";
	}

	@RequiresPermissions("ftc:order:shoppingCart:view")
	@RequestMapping(value = "form")
	public String form(ShoppingCart shoppingCart, Model model) {
		model.addAttribute("shoppingCart", shoppingCart);
		return "modules/ftc/order/shoppingCartForm";
	}

	@RequiresPermissions("ftc:order:shoppingCart:edit")
	@RequestMapping(value = "save")
	public String save(ShoppingCart shoppingCart, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shoppingCart)){
			return form(shoppingCart, model);
		}
		shoppingCartService.save(shoppingCart);
		addMessage(redirectAttributes, "保存购物车成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/order/shoppingCart/?repage";
	}
	
	@RequiresPermissions("ftc:order:shoppingCart:edit")
	@RequestMapping(value = "delete")
	public String delete(ShoppingCart shoppingCart, RedirectAttributes redirectAttributes) {
		shoppingCartService.delete(shoppingCart);
		addMessage(redirectAttributes, "删除购物车成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/order/shoppingCart/?repage";
	}

}