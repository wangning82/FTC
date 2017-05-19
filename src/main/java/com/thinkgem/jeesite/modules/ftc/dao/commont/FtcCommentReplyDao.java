/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.dao.commont;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ftc.entity.commont.FtcCommentReply;

/**
 * 回复DAO接口
 * @author wangqingxiang
 * @version 2017-05-19
 */
@MyBatisDao
public interface FtcCommentReplyDao extends CrudDao<FtcCommentReply> {
	
}