package com.example.installmgmt.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NodeDto {
  
  private Integer id;

  private Integer installRequestId;

  private AddressDto address;

  private List<HardwareDto> hardware;

  private String status;
}
