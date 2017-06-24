package com.thinkgem.jeesite.modules.ftc.constant;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by houyi on 2017/6/20 0020.
 */
public enum ShipmentAmountEnum {
    INSTANCE;

    private String type;

    private BigDecimal amount;

    private HashMap<String, BigDecimal> map;

    ShipmentAmountEnum(){
        if(map == null){
            map = new HashMap<>();
            map.put("0", new BigDecimal(0));
            map.put("1", new BigDecimal(30));
        }
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getAmount(String type){
        return map.get(type);
    }
}
