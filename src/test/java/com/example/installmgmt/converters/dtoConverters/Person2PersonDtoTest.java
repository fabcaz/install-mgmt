package com.example.installmgmt.converters.dtoConverters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.installmgmt.dtos.PersonDto;
import com.example.installmgmt.domain.Person;
import com.example.installmgmt.resources.SampleObjects;

public class Person2PersonDtoTest {
  
  Person2PersonDto converter;

  @BeforeEach
  public void setup(){
    converter = new Person2PersonDto();
  }

  @Test
  @DisplayName("should return null for null arg")
  void shouldReturnNullForNullArg(){
    assertNull(converter.convert(null));
  }

  @Test
  @DisplayName("should return not null for emtpy object")
  void shouldReturnNotNullForNullArg(){
    assertNotNull(converter.convert(new Person()));
  }

  @Test
  @DisplayName("should successfully convert object")
  void shouldConvertFullObjectSuccessfully(){
    
    Person source = SampleObjects.JOHNV;

    PersonDto expected = new PersonDto();
    expected.setId(source.getId());
    expected.setFirstName(source.getFirstName());
    expected.setLastName(source.getLastName());
    expected.setSlackId(source.getSlackId());
    expected.setEmail(source.getEmail());
    expected.setPhoneNumber(source.getPhoneNumber());

    PersonDto actual = converter.convert(source);

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getFirstName(), actual.getFirstName());
    assertEquals(expected.getLastName(), actual.getLastName());
    assertEquals(expected.getSlackId(), actual.getSlackId());
    assertEquals(expected.getEmail(), actual.getEmail());
    assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
  }
}
