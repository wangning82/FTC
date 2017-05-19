/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.commont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.entity.commont.FtcCommentReply;
import com.thinkgem.jeesite.modules.ftc.dao.commont.FtcCommentReplyDao;

/**
 * 回复Service
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Service
@Transactional(readOnly = true)
public class FtcCommentReplyService extends CrudService<FtcCommentReplyDao, FtcCommentReply> {

	
	public FtcCommentReply get(String id) {
		FtcCommentReply ftcCommentReply = super.get(id);
		return ftcCommentReply;
	}
	
	public List<FtcCommentReply> findList(FtcCommentReply ftcCommentReply) {
		return super.findList(ftcCommentReply);
	}
	
	public Page<FtcCommentReply> findPage(Page<FtcCommentReply> page, FtcCommentReply ftcCommentReply) {
		return super.findPage(page, ftcCommentReply);
	}
	
	@Transactional(readOnly = false)
	public void save(FtcCommentReply ftcCommentReply) {
		super.save(ftcCommentReply);
	}
	
	@Transactional(readOnly = false)
	public void delete(FtcCommentReply ftcCommentReply) {
		super.delete(ftcCommentReply);
	}
	
}