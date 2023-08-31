package com.example.installmgmt.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.installmgmt.converters.dtoConverters.Address2AddressDto;
import com.example.installmgmt.converters.dtoConverters.AddressDto2Address;
import com.example.installmgmt.converters.dtoConverters.Hardware2HardwareDto;
import com.example.installmgmt.converters.dtoConverters.HardwareDto2Hardware;
import com.example.installmgmt.converters.dtoConverters.InstallRequest2InstallRequestDto;
import com.example.installmgmt.converters.dtoConverters.InstallRequestAppointment2InstallRequestAppointmentDto;
import com.example.installmgmt.converters.dtoConverters.InstallRequestAppointmentDto2InstallRequestAppointment;
import com.example.installmgmt.converters.dtoConverters.InstallRequestDto2InstallRequest;
import com.example.installmgmt.converters.dtoConverters.Installer2InstallerDto;
import com.example.installmgmt.converters.dtoConverters.InstallerDto2Installer;
import com.example.installmgmt.converters.dtoConverters.NewInstallRequestDto2InstallRequest;
import com.example.installmgmt.converters.dtoConverters.Node2NodeDto;
import com.example.installmgmt.converters.dtoConverters.NodeDto2Node;
import com.example.installmgmt.converters.dtoConverters.Person2PersonDto;
import com.example.installmgmt.converters.dtoConverters.PersonDto2Person;
import com.example.installmgmt.domain.Address;
import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.InstallRequestAppointment;
import com.example.installmgmt.dtos.InstallRequestAppointmentDto;
import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.repositories.AddressRepository;
import com.example.installmgmt.repositories.InstallRequestAppointmentRepository;
import com.example.installmgmt.repositories.InstallRequestRepository;
import com.example.installmgmt.repositories.InstallerRepository;
import com.example.installmgmt.repositories.NodeRepository;
import com.example.installmgmt.repositories.PersonRepository;
import com.example.installmgmt.resources.SampleObjects;

  @DataJpaTest
  @AutoConfigureTestDatabase(replace = Replace.NONE)
  public class InstallRequestServiceImplIT {


    //repositories
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private InstallRequestAppointmentRepository installRequestAppointmentRepository;

    @Autowired
    private InstallRequestRepository installRequestRepository;

    @Autowired
    private InstallerRepository installerRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private NodeRepository nodeRepository;


    //converters
    Address2AddressDto address2AddressDto = new Address2AddressDto();
    AddressDto2Address addressDto2Address = new AddressDto2Address();

    private Hardware2HardwareDto hardware2HardwareDto = new Hardware2HardwareDto();
    private HardwareDto2Hardware hardwareDto2Hardware = new HardwareDto2Hardware();

    private Person2PersonDto person2PersonDto = new Person2PersonDto();
    private PersonDto2Person personDto2Person = new PersonDto2Person();

    private InstallRequestAppointment2InstallRequestAppointmentDto appointment2dto
      = new InstallRequestAppointment2InstallRequestAppointmentDto();

    private InstallRequestAppointmentDto2InstallRequestAppointment dto2appointment
      = new InstallRequestAppointmentDto2InstallRequestAppointment();

    private InstallerDto2Installer installerDto2Installer = 
      new InstallerDto2Installer(personDto2Person);
    
    private Installer2InstallerDto installer2InstallerDto =
      new Installer2InstallerDto(person2PersonDto);

    private Node2NodeDto node2NodeDto = 
      new Node2NodeDto(address2AddressDto, hardware2HardwareDto);

    private NodeDto2Node nodeDto2Node = 
      new NodeDto2Node(addressDto2Address, hardwareDto2Hardware);

    private InstallRequest2InstallRequestDto installRequest2InstallRequestDto
      = new InstallRequest2InstallRequestDto(address2AddressDto,
          hardware2HardwareDto,
          appointment2dto,
          node2NodeDto,
          person2PersonDto);

    //objects dependent on @Autowired repositories
    private InstallerService installerService;
    private PersonService personService;
    private NodeService nodeService;
    private InstallRequestService installRequestService;

    private InstallRequestDto2InstallRequest installRequestDto2InstallRequest;
    private NewInstallRequestDto2InstallRequest newInstallRequestDto2InstallRequest;

    @BeforeEach
    void setup(){

      
      installerService = new InstallerServiceImpl(
          installerRepository, 
          personRepository, 
          installer2InstallerDto, 
          installerDto2Installer);

      personService = new PersonServiceImpl(
          personRepository,
          person2PersonDto,
          personDto2Person);

      nodeService = new NodeServiceImpl(
          nodeRepository,
          node2NodeDto,
          nodeDto2Node);

      installRequestDto2InstallRequest = new InstallRequestDto2InstallRequest(
            installerService,
            addressDto2Address,
            hardwareDto2Hardware,
            dto2appointment,
            nodeDto2Node,
            personDto2Person);


      installRequest2InstallRequestDto = new InstallRequest2InstallRequestDto(
            address2AddressDto,
            hardware2HardwareDto,
            appointment2dto,
            node2NodeDto,
            person2PersonDto);

      newInstallRequestDto2InstallRequest 
        = new NewInstallRequestDto2InstallRequest(
            personService,
            nodeService, 
            personDto2Person, 
            addressDto2Address,
            dto2appointment, 
            nodeDto2Node);

      installRequestService = new InstallRequestServiceImpl(
            addressRepository,
            installRequestAppointmentRepository,
            installRequestRepository,
            installerRepository,
            installRequestDto2InstallRequest,
            installRequest2InstallRequestDto,
            newInstallRequestDto2InstallRequest);
    }

    @Test
    @DisplayName("Should persist NewInstallRequestDto and appointments")
    public void ShouldPersistNewInstallRequestDtoAndAppointments() {

      // InstallerServiceImpl#save(NewInstallRequestDto):
      // 1. converts the dto 
      // 2. finds/saves address 
      // 3. saves entity without appts 
      // 4. saves appts with InstallRequest.id 
      // 5. save complete InstallRequest
      //this test checks that the NewInstallRequestDto's Address and appts 
      //only exist after the service saves

      Optional<Address> rqstAddressBeforeSave = addressRepository.findAllByStreetAddressAndZipcode(   
          SampleObjects.NEW_INSTALL_REQUEST_DTO_1.getAddress().getStreetAddress(),                             
          SampleObjects.NEW_INSTALL_REQUEST_DTO_1.getAddress().getZipcode()                                    
          )                                                                       
        .stream()                                                                 
        .filter(a -> a.getAptUnitNumber().equals(SampleObjects.NEW_INSTALL_REQUEST_DTO_1.getAddress().getAptUnitNumber()))
        .findFirst();

      long apptCountBeforeSave = installRequestAppointmentRepository.count();

      InstallRequestDto actual = installRequestService.saveInstallRequest(
          SampleObjects.NEW_INSTALL_REQUEST_DTO_1
          );

      Address actualAddressDto2Entity = addressDto2Address.convert(actual.getAddress());

      Optional<Address> rqstAddressAfterSave = addressRepository.findAllByStreetAddressAndZipcode(   
          SampleObjects.NEW_INSTALL_REQUEST_DTO_1.getAddress().getStreetAddress(),                             
          SampleObjects.NEW_INSTALL_REQUEST_DTO_1.getAddress().getZipcode()                                    
          )                                                                       
        .stream()                                                                 
        .filter(a -> a.getAptUnitNumber().equals(SampleObjects.NEW_INSTALL_REQUEST_DTO_1.getAddress().getAptUnitNumber()))
        .findFirst();

      long apptCountAfterSave = installRequestAppointmentRepository.count();


      assertTrue(rqstAddressBeforeSave.isEmpty());
      assertTrue(rqstAddressAfterSave.isPresent());
      assertEquals(actualAddressDto2Entity, rqstAddressAfterSave.get());

      assertEquals(
          apptCountBeforeSave + SampleObjects.NEW_INSTALL_REQUEST_DTO_1.getPreferedAppointmentTimes().size(),
          apptCountAfterSave
          ); 
    }

    @Test
    @DisplayName("Should persist InstallRequestDto with new state")
    public void ShouldPersistInstallRequestDtoWithNewState() {

    InstallRequestAppointmentDto newAppt = new InstallRequestAppointmentDto();
    // create new state by modifying INSTALL_REQUEST_APPOINTMENT_3
    InstallRequestAppointment appt3 = SampleObjects.INSTALL_REQUEST_APPOINTMENT_3;
    newAppt.setId(appt3.getId());
    newAppt.setInstallRequestId(appt3.getInstallRequestId());
    newAppt.setSelected(SampleObjects.APPT3_ISSELECTED);
    newAppt.setAppointementStartTime(appt3.getAppointementStartTime().plusDays(1));
    newAppt.setAppointementEndTime(appt3.getAppointementEndTime().plusDays(1));


      InstallRequestDto source = SampleObjects.INSTALL_REQUEST_DTO_5;

      InstallRequestDto sourceNewState = new InstallRequestDto();
      Set<InstallRequestAppointmentDto> apptSet = new HashSet<>(source.getPreferedAppointmentTimes().size());
      apptSet.add(SampleObjects.INSTALL_REQUEST_APPOINTMENT_DBDTO_1);
      apptSet.add(SampleObjects.INSTALL_REQUEST_APPOINTMENT_DBDTO_2);
      apptSet.add(newAppt);

      sourceNewState.setId(source.getId());
      sourceNewState.setRequestType(source.getRequestType());
      sourceNewState.setRequesterPerson(source.getRequesterPerson());
      sourceNewState.setAddress(source.getAddress());
      sourceNewState.setLeadInstaller(source.getLeadInstaller());
      sourceNewState.setRequesterNotes(source.getRequesterNotes());
      sourceNewState.setLeadNotes(source.getLeadNotes());
      sourceNewState.setFloorNumber(source.getFloorNumber());
      sourceNewState.setRequestedDate(source.getRequestedDate());
      sourceNewState.setClaimedDate(source.getClaimedDate());
      //sourceNewState.setCompletedDate();
      sourceNewState.setPreferedAppointmentTimes(apptSet);
      //sourceNewState.setNodesLos();
      sourceNewState.setHardwares(Set.of(SampleObjects.HARDWARE_DTO_3, SampleObjects.HARDWARE_DTO_4));
      //sourceNewState.setAssistantInstallerSet();

      Optional<Address> rqstAddressBeforeSave = addressRepository.findAllByStreetAddressAndZipcode(   
          sourceNewState.getAddress().getStreetAddress(),                             
          sourceNewState.getAddress().getZipcode()                                    
          )                                                                       
        .stream()                                                                 
        .filter(a -> a.getAptUnitNumber().equals(sourceNewState.getAddress().getAptUnitNumber()))
        .findFirst();

      long apptCountBeforeSave = installRequestAppointmentRepository.count();

      InstallRequestDto actual = installRequestService.saveInstallRequest(
          sourceNewState
          );

      Address actualAddressDto2Entity = addressDto2Address.convert(actual.getAddress());

      Optional<Address> rqstAddressAfterSave = addressRepository.findAllByStreetAddressAndZipcode(   
          sourceNewState.getAddress().getStreetAddress(),                             
          sourceNewState.getAddress().getZipcode()                                    
          )                                                                       
        .stream()                                                                 
        .filter(a -> a.getAptUnitNumber().equals(sourceNewState.getAddress().getAptUnitNumber()))
        .findFirst();

      long apptCountAfterSave = installRequestAppointmentRepository.count();


      assertTrue(rqstAddressBeforeSave.isPresent());
      assertTrue(rqstAddressAfterSave.isPresent());
      assertEquals(actualAddressDto2Entity, rqstAddressAfterSave.get());

      assertEquals(
          apptCountBeforeSave,
          apptCountAfterSave
          ); 

      assertTrue(!actual.getPreferedAppointmentTimes().equals(
          source.getPreferedAppointmentTimes()
            ));

      assertTrue(actual.getPreferedAppointmentTimes().contains(newAppt));
    }
  }
