package com.example.installmgmt.converters.dtoConverters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.repositories.InstallerRepository;
import com.example.installmgmt.repositories.NodeRepository;
import com.example.installmgmt.repositories.PersonRepository;
import com.example.installmgmt.resources.SampleObjects;
import com.example.installmgmt.services.InstallerService;
import com.example.installmgmt.services.InstallerServiceImpl;

public class InstallRequestDto2InstallRequestTest {
  
  //repositories
  private static InstallerRepository installerRepository;
  private static PersonRepository personRepository;

  //services
  private static InstallerService installerService;

  //converters
  private static Address2AddressDto address2AddressDto;
  private static AddressDto2Address addressDto2Address;

  private static Hardware2HardwareDto hardware2HardwareDto;
  private static HardwareDto2Hardware hardwareDto2Hardware;

  private static Installer2InstallerDto installer2InstallerDto;
  private static InstallerDto2Installer installerDto2Installer;

  private static InstallRequestAppointmentDto2InstallRequestAppointment dto2appointment;

  private static NodeDto2Node nodeDto2Node;

  private static PersonDto2Person personDto2Person;


  private static InstallRequestDto2InstallRequest converter;

  @BeforeAll
  public static void setup(){

    //repositories
    installerRepository = mock(InstallerRepository.class);
    personRepository = mock(PersonRepository.class);

    //converters
    address2AddressDto = new Address2AddressDto();
    addressDto2Address = new AddressDto2Address();

    hardware2HardwareDto = new Hardware2HardwareDto();
    hardwareDto2Hardware = new HardwareDto2Hardware();

    dto2appointment = new InstallRequestAppointmentDto2InstallRequestAppointment();

    nodeDto2Node = new NodeDto2Node(addressDto2Address, hardwareDto2Hardware);

    personDto2Person = new PersonDto2Person();

    installerService = new InstallerServiceImpl(
        installerRepository, 
        personRepository, 
        installer2InstallerDto, 
        installerDto2Installer);

    converter = new InstallRequestDto2InstallRequest(
        installerService,
        addressDto2Address,
        hardwareDto2Hardware,
        dto2appointment,
        nodeDto2Node,
        personDto2Person
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

    InstallRequestDto source = new InstallRequestDto();
    source.setRequestType("DIY_INSTALL");

    assertNotNull(converter.convert(source));
  }

  @Test
  @DisplayName("should return null for emtpy object with invalid InstallRequestType string")
  void shouldReturnNullForInvalidRequestTypeArg(){

    InstallRequestDto source = new InstallRequestDto();
    source.setRequestType("DIY_INSTA");

    assertNull(converter.convert(source));
  }

  @Test
  @DisplayName("should convert valid object")
  void shouldConvertValidObject(){

    // want to verify that installerRepository gets called
    Installer installRqst5Installer = SampleObjects.INSTALL_REQUEST_5.getLeadInstaller();
    Set<Integer> installerIds = new HashSet<>(1);
    installerIds.add(installRqst5Installer.getId());
    when(installerRepository.findAllById(installerIds)).thenReturn(List.of(installRqst5Installer));

    InstallRequestDto source = SampleObjects.INSTALL_REQUEST_DTO_5;

    InstallRequest expected = SampleObjects.INSTALL_REQUEST_5;

    InstallRequest actual = converter.convert(source);

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
    assertEquals(expected.getNodesLosSet(), actual.getNodesLosSet());
    assertEquals(expected.getHardwareSet(), actual.getHardwareSet());
    assertEquals(expected.getAssistantInstallerSet(), actual.getAssistantInstallerSet());

    verify(installerRepository, times(1)).findAllById(installerIds);

  }
}
