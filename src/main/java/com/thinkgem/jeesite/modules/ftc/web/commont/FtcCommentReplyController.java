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
import com.thinkgem.jeesite.modules.ftc.entity.commont.FtcCommentReply;
import com.thinkgem.jeesite.modules.ftc.service.commont.FtcCommentReplyService;

/**
 * 回复Controller
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/commont/reply")
public class FtcCommentReplyController extends BaseController {

	@Autowired
	private FtcCommentReplyService ftcCommentReplyService;
	
	@ModelAttribute
	public FtcCommentReply get(@RequestParam(required=false) String id) {
		FtcCommentReply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ftcCommentReplyService.get(id);
		}
		if (entity == null){
			entity = new FtcCommentReply();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:commont:ftcCommentReply:view")
	@RequestMapping(value = {"list", ""})
	public String list(FtcCommentReply ftcCommentReply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FtcCommentReply> page = ftcCommentReplyService.findPage(new Page<FtcCommentReply>(request, response), ftcCommentReply); 
		model.addAttribute("page", page);
		return "modules/ftc/commont/ftcCommentReplyList";
	}

	@RequiresPermissions("ftc:commont:ftcCommentReply:view")
	@RequestMapping(value = "form")
	public String form(FtcCommentReply ftcCommentReply, Model model) {
		model.addAttribute("ftcCommentReply", ftcCommentReply);
		return "modules/ftc/commont/ftcCommentReplyForm";
	}

	@RequiresPermissions("ftc:commont:ftcCommentReply:edit")
	@RequestMapping(value = "save")
	public String save(FtcCommentReply ftcCommentReply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ftcCommentReply)){
			return form(ftcCommentReply, model);
		}
		ftcCommentReplyService.save(ftcCommentReply);
		addMessage(redirectAttributes, "保存回复成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/commont/ftcCommentReply/?repage";
	}
	
	@RequiresPermissions("ftc:commont:ftcCommentReply:edit")
	@RequestMapping(value = "delete")
	public String delete(FtcCommentReply ftcCommentReply, RedirectAttributes redirectAttributes) {
		ftcCommentReplyService.delete(ftcCommentReply);
		addMessage(redirectAttributes, "删除回复成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/commont/ftcCommentReply/?repage";
	}

}