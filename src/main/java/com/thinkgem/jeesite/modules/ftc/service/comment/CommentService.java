/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.comment.Comment;
import com.thinkgem.jeesite.modules.ftc.dao.comment.CommentDao;
import com.thinkgem.jeesite.modules.ftc.entity.comment.Reply;
import com.thinkgem.jeesite.modules.ftc.dao.comment.ReplyDao;

/**
 * 评论Service
 * @author wangqingxiang
 * @version 2017-06-04
 */
@Service
@Transactional(readOnly = true)
public class CommentService extends CrudService<CommentDao, Comment> {

	@Autowired
	private ReplyDao replyDao;
	
	public Comment get(String id) {
		Comment comment = super.get(id);
		comment.setReplyList(replyDao.findList(new Reply(comment)));
		return comment;
	}
	
	public List<Comment> findList(Comment comment) {
		return super.findList(comment);
	}
	
	public Page<Comment> findPage(Page<Comment> page, Comment comment) {
		return super.findPage(page, comment);
	}
	
	@Transactional(readOnly = false)
	public void save(Comment comment) {
		super.save(comment);
		for (Reply reply : comment.getReplyList()){
			if (reply.getId() == null){
				continue;
			}
			if (Reply.DEL_FLAG_NORMAL.equals(reply.getDelFlag())){
				if (StringUtils.isBlank(reply.getId())){
					reply.setComment(comment);
					reply.preInsert();
					replyDao.insert(reply);
				}else{
					reply.preUpdate();
					replyDao.update(reply);
				}
			}else{
				replyDao.delete(reply);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Comment comment) {
		super.delete(comment);
		replyDao.delete(new Reply(comment));
	}
	
}