package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.dtos.AddressDto;
import com.example.installmgmt.domain.Address;

import lombok.Synchronized;

@Component
public class Address2AddressDto implements Converter<Address, AddressDto>{

  @Synchronized
  @Nullable
	@Override
	public AddressDto convert(Address arg0) {
    if(arg0 == null){
      return null;
    }
    AddressDto dto = new AddressDto();
    dto.setId(arg0.getId());
    dto.setAptUnitNumber(arg0.getAptUnitNumber());
    dto.setStreetAddress(arg0.getStreetAddress());
    dto.setCity(arg0.getCity());
    dto.setState(arg0.getState());
    dto.setZipcode(arg0.getZipcode());

    return dto;
	}
  
}

