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
import com.thinkgem.jeesite.modules.ftc.entity.comment.Comment;
import com.thinkgem.jeesite.modules.ftc.service.comment.CommentService;

/**
 * 评论Controller
 * @author wangqingxiang
 * @version 2017-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/ftc/comment/comment")
public class CommentController extends BaseController {

	@Autowired
	private CommentService commentService;
	
	@ModelAttribute
	public Comment get(@RequestParam(required=false) String id) {
		Comment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commentService.get(id);
		}
		if (entity == null){
			entity = new Comment();
		}
		return entity;
	}
	
	@RequiresPermissions("ftc:comment:comment:view")
	@RequestMapping(value = {"list", ""})
	public String list(Comment comment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Comment> page = commentService.findPage(new Page<Comment>(request, response), comment); 
		model.addAttribute("page", page);
		return "modules/ftc/comment/commentList";
	}

	@RequiresPermissions("ftc:comment:comment:view")
	@RequestMapping(value = "form")
	public String form(Comment comment, Model model) {
		model.addAttribute("comment", comment);
		return "modules/ftc/comment/commentForm";
	}

	@RequiresPermissions("ftc:comment:comment:edit")
	@RequestMapping(value = "save")
	public String save(Comment comment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comment)){
			return form(comment, model);
		}
		commentService.save(comment);
		addMessage(redirectAttributes, "保存评论成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/comment/comment/?repage";
	}
	
	@RequiresPermissions("ftc:comment:comment:edit")
	@RequestMapping(value = "delete")
	public String delete(Comment comment, RedirectAttributes redirectAttributes) {
		commentService.delete(comment);
		addMessage(redirectAttributes, "删除评论成功");
		return "redirect:"+Global.getAdminPath()+"/ftc/comment/comment/?repage";
	}

}