package com.example.installmgmt.converters.dtoConverters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.InstallRequestType;
import com.example.installmgmt.dtos.NewInstallRequestDto;
import com.example.installmgmt.repositories.NodeRepository;
import com.example.installmgmt.repositories.PersonRepository;
import com.example.installmgmt.resources.SampleObjects;
import com.example.installmgmt.services.NodeService;
import com.example.installmgmt.services.NodeServiceImpl;
import com.example.installmgmt.services.PersonService;
import com.example.installmgmt.services.PersonServiceImpl;

public class NewInstallRequestDto2InstallRequestIT {

  //repositories
  private static  PersonRepository personRepository;
  private static NodeRepository nodeRepository;

  //services
  private static PersonService personService;
  private static NodeService nodeService;

  //converters
  private static Address2AddressDto address2AddressDto;
  private static AddressDto2Address addressDto2Address;

  private static Hardware2HardwareDto hardware2HardwareDto;
  private static HardwareDto2Hardware hardwareDto2Hardware;

  private static InstallRequestAppointment2InstallRequestAppointmentDto appointment2dto;
  private static InstallRequestAppointmentDto2InstallRequestAppointment dto2appointment;

  private static Node2NodeDto node2NodeDto;
  private static NodeDto2Node nodeDto2Node;

  private static Person2PersonDto person2PersonDto;
  private static PersonDto2Person personDto2Person;

  private static NewInstallRequestDto2InstallRequest newInstallRequestDto2InstallRequest;

  @BeforeAll
  public static void setupSingletons(){

    //repositories
    personRepository = mock(PersonRepository.class);
    nodeRepository = mock(NodeRepository.class);

    //converters
    address2AddressDto = new Address2AddressDto();
    addressDto2Address = new AddressDto2Address();

    hardware2HardwareDto = new Hardware2HardwareDto();
    hardwareDto2Hardware = new HardwareDto2Hardware();

    appointment2dto = new InstallRequestAppointment2InstallRequestAppointmentDto();
    dto2appointment = new InstallRequestAppointmentDto2InstallRequestAppointment();

    node2NodeDto = new Node2NodeDto(address2AddressDto, hardware2HardwareDto);
    nodeDto2Node = new NodeDto2Node(addressDto2Address, hardwareDto2Hardware);

    person2PersonDto = new Person2PersonDto();
    personDto2Person = new PersonDto2Person();


    //services
    personService = new PersonServiceImpl(personRepository, person2PersonDto, personDto2Person);
    nodeService = new NodeServiceImpl(nodeRepository, node2NodeDto, nodeDto2Node);

    newInstallRequestDto2InstallRequest = 
      new NewInstallRequestDto2InstallRequest(personService,
          nodeService, 
          personDto2Person, 
          addressDto2Address,
          dto2appointment, 
          nodeDto2Node);
  }

  @Test
  @DisplayName("should convert valid object")
  void shouldConvertValidObject(){
    NewInstallRequestDto source = SampleObjects.NEW_INSTALL_REQUEST_DTO_1;
    int sourcePersonId = source.getRequesterPersonId();
    int sourceNodeId = source.getNodesLos().get(0);

    when(nodeRepository.findById(sourceNodeId)).thenReturn(Optional.of(SampleObjects.NODE1));
    when(personRepository.findById(sourcePersonId)).thenReturn(Optional.of(SampleObjects.JOHNV));


    InstallRequest expected = InstallRequest.builder()

      .requestType(InstallRequestType.CABLE_RUN)
      .requesterPerson(SampleObjects.JOHNV)
      .requesterNotes(source.getRequesterNotes())
      .floorNumber(source.getFloorNumber())
      .address(SampleObjects.ADDRESS_DTO2ENTITY_1)
      .preferedAppointmentTimes(Set.of(SampleObjects.INSTALL_REQUEST_APPOINTMENT_DTO2ENTITY_1, SampleObjects.INSTALL_REQUEST_APPOINTMENT_DTO2ENTITY_2))
      .nodesLosSet(Set.of(SampleObjects.NODE1))
      .build();

    InstallRequest actual = newInstallRequestDto2InstallRequest.convert(source);

    //the requested date is created by the Converter#convert using LocalDateTime.now()
    expected.setRequestedDate(actual.getRequestedDate());

    assertEquals(expected, actual);

    verify(personRepository, times(1)).findById(sourcePersonId);
    verify(nodeRepository, times(1)).findById(sourceNodeId);
  }

}
