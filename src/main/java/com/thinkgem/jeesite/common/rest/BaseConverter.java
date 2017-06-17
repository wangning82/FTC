package com.thinkgem.jeesite.common.rest;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Created by wangqingxiang on 2017/6/17.
 */
public class BaseConverter <T extends DataEntity<T>,D extends BaseDto>{
    public T convertDtoToModel(D dto){
        return null;
    }
    public D convertModelToDto(T model){
        return null;
    }
}
