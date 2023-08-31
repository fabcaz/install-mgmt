package com.example.installmgmt.converters.dtoConverters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.dtos.InstallerDto;
import com.example.installmgmt.resources.SampleObjects;
import com.example.installmgmt.value.InstallerStatus;


public class Installer2InstallerDtoTest {
  
  private static Person2PersonDto person2PersonDto;

  private static Installer2InstallerDto converter;

  @BeforeAll
  public static void setup(){

    person2PersonDto = new Person2PersonDto();

    converter = new Installer2InstallerDto(person2PersonDto);
  }

  @Test
  @DisplayName("should return null for null arg")
  void shouldReturnNullForNullArg(){
    assertNull(converter.convert(null));
  }

  @Test
  @DisplayName("should return not null for emtpy object")
  void shouldReturnNotNullForNullArg(){
    
    Installer source = new Installer();
    source.setStatus(InstallerStatus.LEAD); // null InstallerType returns null

    assertNotNull(converter.convert(source));
  }

  @Test
  @DisplayName("should convert valid object")
  void shouldConvertValidObject(){

    Installer source = SampleObjects.INSTALLER_JOHNV;
    InstallerDto expected = SampleObjects.INSTALLERDTO_JOHNV;
    InstallerDto actual = converter.convert(source);

    assertEquals(expected, actual);

  }


}
