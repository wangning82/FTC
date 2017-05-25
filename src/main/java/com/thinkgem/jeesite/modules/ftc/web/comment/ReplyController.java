/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.web.comment;

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
import com.thinkgem.jeesite.modules.ftc.entity.comment.Reply;
import com.thinkgem.jeesite.modules.ftc.service.comment.ReplyService;

/**
 * 回复Controller
 * @author wangqingxiang
 * @version 2017-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/comment/reply")
public class ReplyController extends BaseController {

	@Autowired
	private ReplyService replyService;
	
	@ModelAttribute
	public Reply get(@RequestParam(required=false) String id) {
		Reply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = replyService.get(id);
		}
		if (entity == null){
			entity = new Reply();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:comment:reply:view")
	@RequestMapping(value = {"list", ""})
	public String list(Reply reply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Reply> page = replyService.findPage(new Page<Reply>(request, response), reply); 
		model.addAttribute("page", page);
		return "modules/ftc/comment/replyList";
	}

	@RequiresPermissions("ftc:comment:reply:view")
	@RequestMapping(value = "form")
	public String form(Reply reply, Model model) {
		model.addAttribute("reply", reply);
		return "modules/ftc/comment/replyForm";
	}

	@RequiresPermissions("ftc:comment:reply:edit")
	@RequestMapping(value = "save")
	public String save(Reply reply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reply)){
			return form(reply, model);
		}
		replyService.save(reply);
		addMessage(redirectAttributes, "保存回复成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/comment/reply/?repage";
	}
	
	@RequiresPermissions("ftc:comment:reply:edit")
	@RequestMapping(value = "delete")
	public String delete(Reply reply, RedirectAttributes redirectAttributes) {
		replyService.delete(reply);
		addMessage(redirectAttributes, "删除回复成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/comment/reply/?repage";
	}

}