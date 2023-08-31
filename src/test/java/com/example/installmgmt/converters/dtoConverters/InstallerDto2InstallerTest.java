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

public class InstallerDto2InstallerTest {

  private static PersonDto2Person personDto2Person;

  private static InstallerDto2Installer converter;

  @BeforeAll
  public static void setup(){
    personDto2Person = new PersonDto2Person();

    converter = new InstallerDto2Installer(personDto2Person);
  }

  @Test
  @DisplayName("should return null for null arg")
  void shouldReturnNullForNullArg(){
    assertNull(converter.convert(null));
  }

  @Test
  @DisplayName("should return not null for emtpy object")
  void shouldReturnNotNullForNullArg(){

    InstallerDto source = new InstallerDto();
    source.setStatus("LEAD"); // null InstallerType returns null

    assertNotNull(converter.convert(source));
  }

  @Test
  @DisplayName("should return null for emtpy object with invalid status string")
  void shouldReturnNullForInvalidStatus(){

    InstallerDto source = new InstallerDto();
    source.setStatus("LAD"); // null InstallerType returns null

    assertNull(converter.convert(source));
  }

  @Test
  @DisplayName("should convert valid object")
  void shouldConvertValidObject(){

    InstallerDto source = SampleObjects.INSTALLERDTO_JOHNV;
    Installer expected = SampleObjects.INSTALLER_JOHNV;
    Installer actual = converter.convert(source);

    assertEquals(expected, actual);

  }

}
