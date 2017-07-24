/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.ftc.entity.product.Category;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import com.thinkgem.jeesite.modules.ftc.service.product.PositionService;

/**
 * 位置Controller
 * @author wangqingxiang
 * @version 2017-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/product/position")
public class PositionController extends BaseController {

	@Autowired
	private PositionService positionService;
	
	@ModelAttribute
	public Position get(@RequestParam(required=false) String id) {
		Position entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = positionService.get(id);
		}
		if (entity == null){
			entity = new Position();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:product:position:view")
	@RequestMapping(value = {"list", ""})
	public String list(Position position, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Position> page = positionService.findPage(new Page<Position>(request, response), position);
		model.addAttribute("page", page);
		return "modules/ftc/product/positionList";
	}

	@RequiresPermissions("ftc:product:position:view")
	@RequestMapping(value = "form")
	public String form(Position position, Model model) {
		model.addAttribute("position", position);
		return "modules/ftc/product/positionForm";
	}

	@RequiresPermissions("ftc:product:position:edit")
	@RequestMapping(value = "save")
	public String save(Position position, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, position)){
			return form(position, model);
		}
		positionService.save(position);
		addMessage(redirectAttributes, "保存位置成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/position/?repage";
	}
	
	@RequiresPermissions("ftc:product:position:edit")
	@RequestMapping(value = "delete")
	public String delete(Position position, RedirectAttributes redirectAttributes) {
		positionService.delete(position);
		addMessage(redirectAttributes, "删除位置成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/position/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Position position=new Position();
		position.setProduct(new Product(extId));
		List<Position> list = positionService.findList(position);
		for (int i=0; i<list.size(); i++){
			Position e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) )){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getProduct().getId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}