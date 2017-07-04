package com.thinkgem.jeesite.modules.ftc.service.product;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ftc.dao.product.DesignDetailDao;
import com.thinkgem.jeesite.modules.ftc.entity.product.DesignDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bingbing on 2017/7/4.
 */
@Service
@Transactional(readOnly = true)
public class DesignDetailService extends CrudService<DesignDetailDao, DesignDetail> {
}
