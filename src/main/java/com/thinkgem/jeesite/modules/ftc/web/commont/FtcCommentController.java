/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.commont;

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
import com.thinkgem.jeesite.modules.ftc.entity.commont.FtcComment;
import com.thinkgem.jeesite.modules.ftc.service.commont.FtcCommentService;

/**
 * 评论Controller
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/commont/")
public class FtcCommentController extends BaseController {

	@Autowired
	private FtcCommentService ftcCommentService;
	
	@ModelAttribute
	public FtcComment get(@RequestParam(required=false) String id) {
		FtcComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ftcCommentService.get(id);
		}
		if (entity == null){
			entity = new FtcComment();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:commont:ftcComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(FtcComment ftcComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FtcComment> page = ftcCommentService.findPage(new Page<FtcComment>(request, response), ftcComment); 
		model.addAttribute("page", page);
		return "modules/ftc/commont/ftcCommentList";
	}

	@RequiresPermissions("ftc:commont:ftcComment:view")
	@RequestMapping(value = "form")
	public String form(FtcComment ftcComment, Model model) {
		model.addAttribute("ftcComment", ftcComment);
		return "modules/ftc/commont/ftcCommentForm";
	}

	@RequiresPermissions("ftc:commont:ftcComment:edit")
	@RequestMapping(value = "save")
	public String save(FtcComment ftcComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ftcComment)){
			return form(ftcComment, model);
		}
		ftcCommentService.save(ftcComment);
		addMessage(redirectAttributes, "保存评论成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/commont/ftcComment/?repage";
	}
	
	@RequiresPermissions("ftc:commont:ftcComment:edit")
	@RequestMapping(value = "delete")
	public String delete(FtcComment ftcComment, RedirectAttributes redirectAttributes) {
		ftcCommentService.delete(ftcComment);
		addMessage(redirectAttributes, "删除评论成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/commont/ftcComment/?repage";
	}

}