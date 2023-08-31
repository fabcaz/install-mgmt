package com.example.installmgmt.converters.dtoConverters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.InstallRequestType;
import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.resources.SampleObjects;

public class InstallRequest2InstallRequestDtoTest {
  
  //converters
  private static Address2AddressDto address2AddressDto;

  private static Hardware2HardwareDto hardware2HardwareDto;

  private static InstallRequestAppointment2InstallRequestAppointmentDto appointment2dto;

  private static Node2NodeDto node2NodeDto;

  private static Person2PersonDto person2PersonDto;

  private static InstallRequest2InstallRequestDto converter;

  @BeforeAll
  public static void setup(){

    //converters
    address2AddressDto = new Address2AddressDto();

    hardware2HardwareDto = new Hardware2HardwareDto();

    appointment2dto = new InstallRequestAppointment2InstallRequestAppointmentDto();

    node2NodeDto = new Node2NodeDto(address2AddressDto, hardware2HardwareDto);

    person2PersonDto = new Person2PersonDto();


    converter = new InstallRequest2InstallRequestDto(
          address2AddressDto,
          hardware2HardwareDto,
          appointment2dto, 
          node2NodeDto,
          person2PersonDto
          );
  }

  @Test
  @DisplayName("should return null for null arg")
  void shouldReturnNullForNullArg(){
    assertNull(converter.convert(null));
  }

  @Test
  @DisplayName("should return not null for emtpy object")
  void shouldReturnNotNullForNullArg(){
    
    InstallRequest source = new InstallRequest();
    source.setRequestType(InstallRequestType.DIY);

    assertNotNull(converter.convert(source));
  }

  @Test
  @DisplayName("should convert valid object")
  void shouldConvertValidObject(){
    InstallRequest source = SampleObjects.INSTALL_REQUEST_5;

    InstallRequestDto expected = SampleObjects.INSTALL_REQUEST_DTO_5;

    InstallRequestDto actual = converter.convert(source);

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getRequestType(), actual.getRequestType());
    assertEquals(expected.getRequesterPerson(), actual.getRequesterPerson());
    assertEquals(expected.getAddress(), actual.getAddress());
    assertEquals(expected.getLeadInstaller(), actual.getLeadInstaller());
    assertEquals(expected.getRequesterNotes(), actual.getRequesterNotes());
    assertEquals(expected.getLeadNotes(), actual.getLeadNotes());
    assertEquals(expected.getFloorNumber(), actual.getFloorNumber());
    assertEquals(expected.getRequestedDate(), actual.getRequestedDate());
    assertEquals(expected.getClaimedDate(), actual.getClaimedDate());
    assertEquals(expected.getCompletedDate(), actual.getCompletedDate());
    assertEquals(expected.getPreferedAppointmentTimes(), actual.getPreferedAppointmentTimes());
    assertEquals(expected.getNodesLos(), actual.getNodesLos());
    assertEquals(expected.getHardwares(), actual.getHardwares());
  }

}
