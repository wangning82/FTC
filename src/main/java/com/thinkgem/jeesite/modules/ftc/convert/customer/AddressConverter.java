package com.thinkgem.jeesite.modules.ftc.convert.customer;

import com.thinkgem.jeesite.common.rest.BaseConverter;
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
        address.setProvince(new Area(dto.getProvince()));
        address.setCity(new Area(dto.getCity()));
        address.setDistrict(new Area(dto.getDistrict()));
        address.setIsDefault(dto.getIsDefault());
        return address;
    }

    @Override
    public AddressDto convertModelToDto(Address model) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(model.getId());
        addressDto.setProvince(model.getProvince().getId());
        addressDto.setCity(model.getCity().getId());
        addressDto.setDistrict(model.getDistrict().getId());
        addressDto.setName(model.getUserName());
        addressDto.setPhone(model.getUserPhone());
        addressDto.setDetail(model.getUserAdress());
        addressDto.setIsDefault(model.getIsDefault());
        return addressDto;
    }
}
