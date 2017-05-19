/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.commont;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.entity.commont.FtcComment;
import com.thinkgem.jeesite.modules.ftc.dao.commont.FtcCommentDao;

/**
 * 评论Service
 * @author wangqingxiang
 * @version 2017-05-19
 */
@Service
@Transactional(readOnly = true)
public class FtcCommentService extends CrudService<FtcCommentDao, FtcComment> {

	public FtcComment get(String id) {
		return super.get(id);
	}
	
	public List<FtcComment> findList(FtcComment ftcComment) {
		return super.findList(ftcComment);
	}
	
	public Page<FtcComment> findPage(Page<FtcComment> page, FtcComment ftcComment) {
		return super.findPage(page, ftcComment);
	}
	
	@Transactional(readOnly = false)
	public void save(FtcComment ftcComment) {
		super.save(ftcComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(FtcComment ftcComment) {
		super.delete(ftcComment);
	}
	
}