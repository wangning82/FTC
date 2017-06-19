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
import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;
import com.thinkgem.jeesite.modules.ftc.service.customer.AddressService;

/**
 * 收货地址Controller
 * @author houyi
 * @version 2017-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/customer/address")
public class AddressController extends BaseController {

	@Autowired
	private AddressService addressService;
	
	@ModelAttribute
	public Address get(@RequestParam(required=false) String id) {
		Address entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = addressService.get(id);
		}
		if (entity == null){
			entity = new Address();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:customer:address:view")
	@RequestMapping(value = {"list", ""})
	public String list(Address address, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Address> page = addressService.findPage(new Page<Address>(request, response), address); 
		model.addAttribute("page", page);
		return "modules/ftc/customer/addressList";
	}

	@RequiresPermissions("ftc:customer:address:view")
	@RequestMapping(value = "form")
	public String form(Address address, Model model) {
		model.addAttribute("address", address);
		return "modules/ftc/customer/addressForm";
	}

	@RequiresPermissions("ftc:customer:address:edit")
	@RequestMapping(value = "save")
	public String save(Address address, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, address)){
			return form(address, model);
		}
		addressService.save(address);
		addMessage(redirectAttributes, "保存收货地址成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/customer/address/?repage";
	}
	
	@RequiresPermissions("ftc:customer:address:edit")
	@RequestMapping(value = "delete")
	public String delete(Address address, RedirectAttributes redirectAttributes) {
		addressService.delete(address);
		addMessage(redirectAttributes, "删除收货地址成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/customer/address/?repage";
	}

}