/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.ftc.entity.product.FtcCategory;
import com.thinkgem.jeesite.modules.ftc.service.product.FtcCategoryService;

/**
 * 分类Controller
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/product/category")
public class FtcCategoryController extends BaseController {

	@Autowired
	private FtcCategoryService categoryService;
	
	@ModelAttribute
	public FtcCategory get(@RequestParam(required=false) String id) {
		FtcCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = categoryService.get(id);
		}
		if (entity == null){
			entity = new FtcCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:product:category:view")
	@RequestMapping(value = {"list", ""})
	public String list(FtcCategory ftcCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<FtcCategory> list = categoryService.findList(ftcCategory);
		model.addAttribute("list", list);
		model.addAttribute("category",ftcCategory);
		return "modules/ftc/product/categoryList";
	}

	@RequiresPermissions("ftc:product:category:view")
	@RequestMapping(value = "form")
	public String form(FtcCategory category, Model model) {
		if (category.getParent()!=null && StringUtils.isNotBlank(category.getParent().getId())){
			category.setParent(categoryService.get(category.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(category.getId())){
				FtcCategory categoryChild = new FtcCategory();
				categoryChild.setParent(new FtcCategory(category.getParent().getId()));
				List<FtcCategory> list = categoryService.findList(category);
				if (list.size() > 0){
					category.setSort(list.get(list.size()-1).getSort());
					if (category.getSort() != null){
						category.setSort(category.getSort() + 30);
					}
				}
			}
		}
		if (category.getSort() == null){
			category.setSort(30);
		}
		model.addAttribute("category", category);
		return "modules/ftc/product/categoryForm";
	}

	@RequiresPermissions("ftc:product:category:edit")
	@RequestMapping(value = "save")
	public String save(FtcCategory category, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, category)){
			return form(category, model);
		}
		categoryService.save(category);
		addMessage(redirectAttributes, "保存分类成功");
		return "redirect:"+Global.getAdminPath()+"/ftc.product/category/?repage";
	}
	
	@RequiresPermissions("ftc:product:category:edit")
	@RequestMapping(value = "delete")
	public String delete(FtcCategory category, RedirectAttributes redirectAttributes) {
		categoryService.delete(category);
		addMessage(redirectAttributes, "删除分类成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/product/category/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<FtcCategory> list = categoryService.findList(new FtcCategory());
		for (int i=0; i<list.size(); i++){
			FtcCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}