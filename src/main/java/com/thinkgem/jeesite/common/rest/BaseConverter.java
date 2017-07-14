package com.thinkgem.jeesite.common.rest;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.ftc.dto.product.ProductDto;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;

import java.util.ArrayList;
import java.util.List;

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
    public List<D> convertListFromModelToDto(List<T> modelList){
        List<D> dtoList=new ArrayList<D>();
        if(modelList!=null&&modelList.size()>0){
            for(T model:modelList){
                dtoList.add( convertModelToDto(model));
            }
        }
        return dtoList;
    }
    public List<T> convertListFromDtoToModel(List<D>dtoList){
        List<T> modelList=new ArrayList<T>();
        if(dtoList!=null&&dtoList.size()>0){
            for(D d:dtoList){
                modelList.add( convertDtoToModel(d));
            }
        }

        return modelList;
    }
}
