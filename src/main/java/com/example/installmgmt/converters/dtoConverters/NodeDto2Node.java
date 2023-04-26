package com.example.installmgmt.converters.dtoConverters;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.domain.Address;
import com.example.installmgmt.domain.Hardware;
import com.example.installmgmt.domain.Node;
import com.example.installmgmt.dtos.NodeDto;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class NodeDto2Node implements Converter<NodeDto, Node>{

  private final AddressDto2Address addressDtoToAddress;

  private final HardwareDto2Hardware hardwareDto2Hardware;

  @Synchronized
  @Nullable
	@Override
	public Node convert(NodeDto source) {

    if(source == null){
      return null;
    }

    Address address = addressDtoToAddress.convert(source.getAddress());

    Set<Hardware> hardwares = source.getHardware().stream()
      .map(hardwareDto2Hardware::convert)
      .collect(Collectors.toSet());

		return new Node(
        source.getId(),
        source.getInstallRequestId(),
        address,
        hardwares,
        source.getStatus()
        );
	}
  
}
