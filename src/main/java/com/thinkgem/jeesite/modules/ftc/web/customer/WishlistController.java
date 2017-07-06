/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.ftc.constant.WishlistTypeEnum;
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
import com.thinkgem.jeesite.modules.ftc.entity.customer.Wishlist;
import com.thinkgem.jeesite.modules.ftc.service.customer.WishlistService;

/**
 * 收藏Controller
 * @author wangqingxiang
 * @version 2017-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/customer/wishlist")
public class WishlistController extends BaseController {

	@Autowired
	private WishlistService wishlistService;
	
	@ModelAttribute
	public Wishlist get(@RequestParam(required=false) String id) {
		Wishlist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wishlistService.get(id);
		}
		if (entity == null){
			entity = new Wishlist();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:customer:wishlist:view")
	@RequestMapping(value = {"list", ""})
	public String list(Wishlist wishlist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Wishlist> page = wishlistService.findPage(new Page<Wishlist>(request, response), wishlist); 
		model.addAttribute("page", page);
		if(WishlistTypeEnum.WISHLIST_SHOP.getValue().equals( wishlist.getType())){
			return "modules/ftc/customer/wishlistList2";
		}else{
			return "modules/ftc/customer/wishlistList";
		}
	}

	@RequiresPermissions("ftc:customer:wishlist:view")
	@RequestMapping(value = "form")
	public String form(Wishlist wishlist, Model model) {
		model.addAttribute("wishlist", wishlist);
		return "modules/ftc/customer/wishlistForm";
	}

	@RequiresPermissions("ftc:customer:wishlist:edit")
	@RequestMapping(value = "save")
	public String save(Wishlist wishlist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wishlist)){
			return form(wishlist, model);
		}
		wishlistService.save(wishlist);
		addMessage(redirectAttributes, "保存收藏成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/customer/wishlist/?repage";
	}
	
	@RequiresPermissions("ftc:customer:wishlist:edit")
	@RequestMapping(value = "delete")
	public String delete(Wishlist wishlist, RedirectAttributes redirectAttributes) {
		wishlistService.delete(wishlist);
		addMessage(redirectAttributes, "删除收藏成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/customer/wishlist/?repage";
	}

}