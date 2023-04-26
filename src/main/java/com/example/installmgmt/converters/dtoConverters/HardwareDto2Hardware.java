package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.domain.Hardware;
import com.example.installmgmt.dtos.HardwareDto;

import lombok.Synchronized;

@Component
public class HardwareDto2Hardware implements Converter<HardwareDto, Hardware>{

  @Synchronized
  @Nullable
	@Override
	public Hardware convert(HardwareDto source) {

    if(source == null){
      return null;
    }

    return new Hardware(source.getId(), source.getName());
	}
  
}

