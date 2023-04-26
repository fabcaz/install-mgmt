package com.example.installmgmt.converters.dtoConverters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.installmgmt.dtos.AddressDto;
import com.example.installmgmt.domain.Address;
import com.example.installmgmt.resources.SampleObjects;

public class AddressDto2AddressTest {
  
  AddressDto2Address converter;

  @BeforeEach
  public void setup(){
    converter = new AddressDto2Address();
  }

  @Test
  @DisplayName("should return null for null arg")
  void shouldReturnNullForNullArg(){
    assertNull(converter.convert(null));
  }

  @Test
  @DisplayName("should return not null for emtpy object")
  void shouldReturnNotNullForNullArg(){
    assertNotNull(converter.convert(new AddressDto()));
  }

  @Test
  @DisplayName("should successfully convert object")
  void shouldConvertFullObjectSuccessfully(){
    
    Address expected = SampleObjects.ADDRESS1;

    AddressDto command = new AddressDto();
    command.setId(expected.getId());
    command.setAptUnitNumber(expected.getAptUnitNumber());
    command.setStreetAddress(expected.getStreetAddress());
    command.setCity(expected.getCity());
    command.setState(expected.getState());
    command.setZipcode(expected.getZipcode());


    Address actual = converter.convert(command);

    assertEquals(expected, actual);
  }
}
