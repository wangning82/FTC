/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.comment;

import java.util.List;

import com.thinkgem.jeesite.modules.ftc.dao.product.ProductDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
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
	private ProductDao productDao;
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

	/**
	 * 更新评价的回复数量
	 */
	private void updateCommentCommentCount(){

	}

	/**
	 * 更新商品的评价数量
	 * @param comment
     */
	public void updateProductCommentCount(Comment comment){
		dao.updateProductCommentCount(comment.getProduct().getId());
	}
	@Transactional(readOnly = false)
	public int zan(Comment comment){
		this.save(comment);
		Product product=productDao.get(comment.getProduct().getId());
		product.setPraiseCount(1+(product.getPraiseCount()==null?0:product.getPraiseCount()));
		productDao.update(product);
		return product.getPraiseCount();
	}
	@Transactional(readOnly = false)
	public int cancelZan(Comment comment){
		Product product=productDao.get(comment.getProduct().getId());
		product.setPraiseCount(product.getPraiseCount()-1);
		productDao.update(product);
		this.delete(comment);
		return product.getPraiseCount();
	}
}