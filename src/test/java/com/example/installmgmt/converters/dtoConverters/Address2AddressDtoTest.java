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

public class Address2AddressDtoTest {
  
  Address2AddressDto converter;

  @BeforeEach
  public void setup(){
    converter = new Address2AddressDto();
  }

  @Test
  @DisplayName("should return null for null arg")
  void shouldReturnNullForNullArg(){
    assertNull(converter.convert(null));
  }

  @Test
  @DisplayName("should return not null for emtpy object")
  void shouldReturnNotNullForNullArg(){
    assertNotNull(converter.convert(new Address()));
  }

  @Test
  @DisplayName("should successfully convert object")
  void shouldConvertFullObjectSuccessfully(){
    
    Address source = SampleObjects.ADDRESS1;

    AddressDto expected = new AddressDto();
    expected.setId(source.getId());
    expected.setAptUnitNumber(source.getAptUnitNumber());
    expected.setStreetAddress(source.getStreetAddress());
    expected.setCity(source.getCity());
    expected.setState(source.getState());
    expected.setZipcode(source.getZipcode());


    AddressDto actual = converter.convert(source);

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getAptUnitNumber(), actual.getAptUnitNumber());
    assertEquals(expected.getStreetAddress(), actual.getStreetAddress());
    assertEquals(expected.getCity(), actual.getCity());
    assertEquals(expected.getState(), actual.getState());
    assertEquals(expected.getZipcode(), actual.getZipcode());
  }
}
