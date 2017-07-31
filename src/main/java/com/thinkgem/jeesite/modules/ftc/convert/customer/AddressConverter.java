package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.constant.FlagEnum;
import com.thinkgem.jeesite.modules.ftc.dto.customer.AddressDto;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Address;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import org.springframework.stereotype.Component;

/**
 * Created by houyi on 2017/7/5 0005.
 */
@Component
public class AddressConverter extends BaseConverter<Address, AddressDto> {

    @Override
    public Address convertDtoToModel(AddressDto dto) {
        Address address = new Address();
        address.setUserName(dto.getName());
        address.setUserPhone(dto.getPhone());
        address.setUserAdress(dto.getDetail());
        address.setDistrict(new Area(null,dto.getDistrict()));
        if(dto.isDefault()){
            address.setIsDefault(FlagEnum.Flag_YES.getValue());
        }else {
            address.setIsDefault(FlagEnum.Flag_NO.getValue());
        }
        return address;
    }

    @Override
    public AddressDto convertModelToDto(Address model) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(model.getId());
        addressDto.setDistrict(model.getDistrict().getName());
        addressDto.setName(model.getUserName());
        addressDto.setPhone(model.getUserPhone());
        addressDto.setDetail(model.getUserAdress());
        if(FlagEnum.Flag_YES.getValue().equals(model.getIsDefault())){
            addressDto.setDefault(true);
        }else if(FlagEnum.Flag_NO.getValue().equals(model.getIsDefault())){
            addressDto.setDefault(false);
        }
        return addressDto;
    }
}
