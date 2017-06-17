package com.thinkgem.jeesite.common.rest;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;

/**
 * Created by wangqingxiang on 2017/6/6.
 */
public class BaseRestController<T extends DataEntity<T>,D extends ProductDto > {
    public static String CODE_SUCCESS="1";
    public static String CODE_ERROR="2";
    public static String CODE_NULL="3";
    public static String MSG_SUCCESS="成功";

}
