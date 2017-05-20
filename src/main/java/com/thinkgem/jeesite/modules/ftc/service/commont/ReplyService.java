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
import com.thinkgem.jeesite.modules.ftc.entity.commont.Reply;
import com.thinkgem.jeesite.modules.ftc.dao.commont.ReplyDao;

/**
 * 回复Service
 * @author wangqingxiang
 * @version 2017-05-20
 */
@Service
@Transactional(readOnly = true)
public class ReplyService extends CrudService<ReplyDao, Reply> {

	
	public Reply get(String id) {
		Reply reply = super.get(id);
		return reply;
	}
	
	public List<Reply> findList(Reply reply) {
		return super.findList(reply);
	}
	
	public Page<Reply> findPage(Page<Reply> page, Reply reply) {
		return super.findPage(page, reply);
	}
	
	@Transactional(readOnly = false)
	public void save(Reply reply) {
		super.save(reply);
	}
	
	@Transactional(readOnly = false)
	public void delete(Reply reply) {
		super.delete(reply);
	}
	
}