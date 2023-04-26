package com.example.installmgmt.converters.dtoConverters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.domain.Node;
import com.example.installmgmt.dtos.AddressDto;
import com.example.installmgmt.dtos.HardwareDto;
import com.example.installmgmt.dtos.NodeDto;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class Node2NodeDto implements Converter<Node, NodeDto>{

  private final Address2AddressDto addressToAddressDto;

  private final Hardware2HardwareDto hardware2HardwareDto;

  @Synchronized
  @Nullable
	@Override
	public NodeDto convert(Node source) {

    if(source == null){
      return null;
    }

    AddressDto addressDto = addressToAddressDto.convert(source.getAddress());

    List<HardwareDto> hardwareDtos = source.getHardwareSet().stream()
      .map(hardware2HardwareDto::convert)
      .collect(Collectors.toList());

    NodeDto dto = new NodeDto();

    dto.setId(source.getId());
    dto.setInstallRequestId(source.getInstallRequestId());
    dto.setAddress(addressDto);
    dto.setHardware(hardwareDtos);
    dto.setStatus(source.getStatus());

    return dto;
	}
  
}
