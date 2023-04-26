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

public class PersonDto2PersonTest {
  
  PersonDto2Person converter;

  @BeforeEach
  public void setup(){
    converter = new PersonDto2Person();
  }

  @Test
  @DisplayName("should return null for null arg")
  void shouldReturnNullForNullArg(){
    assertNull(converter.convert(null));
  }

  @Test
  @DisplayName("should return not null for emtpy object")
  void shouldReturnNotNullForNullArg(){
    assertNotNull(converter.convert(new PersonDto()));
  }

  @Test
  @DisplayName("should successfully convert object")
  void shouldConvertFullObjectSuccessfully(){
    
    Person expected = SampleObjects.JOHNV;

    PersonDto command = new PersonDto();
    command.setId(expected.getId());
    command.setFirstName(expected.getFirstName());
    command.setLastName(expected.getLastName());
    command.setSlackId(expected.getSlackId());
    command.setEmail(expected.getEmail());
    command.setPhoneNumber(expected.getPhoneNumber());

    Person actual = converter.convert(command);

    assertEquals(expected, actual);
  }
}
