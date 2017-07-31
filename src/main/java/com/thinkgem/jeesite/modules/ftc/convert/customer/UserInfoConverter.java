package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.constant.PlatformTypeEnum;
import com.thinkgem.jeesite.modules.ftc.dto.customer.PlatformDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.UserInfoDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyi on 2017/7/13 0013.
 */
@Component
public class UserInfoConverter extends BaseConverter<Customer, UserInfoDto> {

    @Autowired
    private ShopConverter shopConverter;

    @Override
    public UserInfoDto convertModelToDto(Customer model) {
        UserInfoDto userInfoDto = new UserInfoDto();
//        userInfoDto.setToken(model.getAccessToken());
//        userInfoDto.setShop(shopConverter.convertModelToDto(model));

        List<PlatformDto> platformDtoList = new ArrayList<PlatformDto>();

        PlatformDto phone = new PlatformDto();
        phone.setpUid(model.getTelephone());
        phone.setType(PlatformTypeEnum.Phone.getValue());
        platformDtoList.add(phone);

        PlatformDto wechat = new PlatformDto();
        wechat.setpUid(model.getWechat());
        wechat.setType(PlatformTypeEnum.WeChat.getValue());
        platformDtoList.add(wechat);

        PlatformDto qq = new PlatformDto();
        qq.setpUid(model.getQq());
        qq.setType(PlatformTypeEnum.QQ.getValue());
        platformDtoList.add(qq);

        userInfoDto.setPlatforms(platformDtoList);

        return userInfoDto;
    }
}
