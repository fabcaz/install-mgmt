package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.domain.Hardware;
import com.example.installmgmt.dtos.HardwareDto;

import lombok.Synchronized;

@Component
public class Hardware2HardwareDto implements Converter<Hardware, HardwareDto>{

  @Synchronized
  @Nullable
	@Override
	public HardwareDto convert(Hardware source) {

    if(source == null){
      return null;
    }

    HardwareDto dto = new HardwareDto();
    dto.setId(source.getId());
    dto.setName(source.getName());

    return dto;
	}
  
}

