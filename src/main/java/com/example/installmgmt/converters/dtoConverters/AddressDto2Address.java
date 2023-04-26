package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.dtos.AddressDto;
import com.example.installmgmt.domain.Address;

import lombok.Synchronized;

@Component
public class AddressDto2Address implements Converter<AddressDto, Address>{

  @Synchronized
  @Nullable
	@Override
	public Address convert(AddressDto arg0) {
    if(arg0 == null){
      return null;
    }
    return new Address(
        arg0.getId(),
        arg0.getAptUnitNumber(),
        arg0.getStreetAddress(),
        arg0.getCity(),
        arg0.getState(),
        arg0.getZipcode()
        );
	}
  
}
