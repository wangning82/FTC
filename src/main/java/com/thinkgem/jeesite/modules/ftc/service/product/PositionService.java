/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.service.product;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.dao.product.PositionDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.Position;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 位置Service
 * @author wangqingxiang
 * @version 2017-05-31
 */
@Service
@Transactional(readOnly = true)
public class PositionService extends CrudService<PositionDao, Position> {

	public Position get(String id) {
		return super.get(id);
	}
	
	public List<Position> findList(Position position) {

		return super.findList(position);
	}
	
	@Transactional(readOnly = false)
	public void save(Position position) {
		super.save(position);
	}
	
	@Transactional(readOnly = false)
	public void delete(Position position) {
		super.delete(position);
	}
	
}