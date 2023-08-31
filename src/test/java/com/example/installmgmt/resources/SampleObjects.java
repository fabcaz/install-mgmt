package com.example.installmgmt.resources;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.installmgmt.dtos.AddressDto;
import com.example.installmgmt.dtos.HardwareDto;
import com.example.installmgmt.dtos.InstallParticipantDto;
import com.example.installmgmt.dtos.InstallRequestAppointmentDto;
import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.dtos.InstallerDto;
import com.example.installmgmt.dtos.NewInstallRequestDto;
import com.example.installmgmt.dtos.NodeDto;
import com.example.installmgmt.dtos.PersonDto;
import com.example.installmgmt.domain.Address;
import com.example.installmgmt.domain.Hardware;
import com.example.installmgmt.domain.Install;
import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.InstallRequestAppointment;
import com.example.installmgmt.domain.InstallRequestType;
import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.domain.Member;
import com.example.installmgmt.domain.Node;
import com.example.installmgmt.domain.Person;
import com.example.installmgmt.value.InstallerStatus;
import com.example.installmgmt.value.MemberStatus;

public class SampleObjects {
  
  // ___ADDRESS___

  public static final String CITY = "New York";
  public static final String STATE = "NY";

  public static final String SAMPLE_MAINST_STREETADDRESS = "123 main Street";
  public static final int SAMPLE_MAINST_ZIPCODE = 10022;

  public static final String SAMPLE_BRIDGEST_STREETADDRESS = "456 bridge Street";
  public static final int SAMPLE_BRIDGEST_ZIPCODE = 11211;

  public static final String SAMPLE_ROOSST_STREETADDRESS = "789 Roos Street";
  public static final int SAMPLE_ROOSST_ZIPCODE = 10044;

  public static final String ADDRESS1_APT = "apt 2h";
  public static final String ADDRESS2_APT = "apt 5b";
  public static final String ADDRESS3_APT = "apt 5c";
  public static final String ADDRESS4_APT = "apt 1102";
  public static final String ADDRESS5_APT = "apt 3a";
  public static final String ADDRESS6_APT = "apt 3b";

  public static final Address ADDRESS1 = Address.builder().id(1)
    .aptUnitNumber(ADDRESS1_APT).streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  public static final Address ADDRESS2 = Address.builder().id(2)
    .aptUnitNumber(ADDRESS2_APT).streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  public static final Address ADDRESS3 = Address.builder().id(3)
    .aptUnitNumber(ADDRESS3_APT).streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  public static final Address ADDRESS4 = Address.builder().id(4)
    .aptUnitNumber(ADDRESS4_APT).streetAddress(SAMPLE_BRIDGEST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_BRIDGEST_ZIPCODE)
    .build();

  public static final Address ADDRESS5 = Address.builder().id(5)
    .aptUnitNumber(ADDRESS5_APT).streetAddress(SAMPLE_ROOSST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_ROOSST_ZIPCODE)
    .build();

  // to verify convertion - represents ADDRESS6 (new, not persisted)
  public static final Address ADDRESS_DTO2ENTITY_6 = Address.builder().id(null)
    .aptUnitNumber(ADDRESS6_APT).streetAddress(SAMPLE_ROOSST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_ROOSST_ZIPCODE)
    .build();

  //__ADDRESS_DTO__
  public static final AddressDto ADDRESS_DTO_6;
  public static final AddressDto ADDRESS_DTO_3;
  public static final AddressDto ADDRESS_DTO_5;

  static{
    ADDRESS_DTO_6 = new AddressDto();
    ADDRESS_DTO_6.setId(null);
    ADDRESS_DTO_6.setAptUnitNumber(ADDRESS6_APT);
    ADDRESS_DTO_6.setStreetAddress(SAMPLE_ROOSST_STREETADDRESS);
    ADDRESS_DTO_6.setCity(CITY);
    ADDRESS_DTO_6.setState(STATE);
    ADDRESS_DTO_6.setZipcode(SAMPLE_ROOSST_ZIPCODE);

    ADDRESS_DTO_5 = new AddressDto();
    ADDRESS_DTO_5.setId(5);
    ADDRESS_DTO_5.setAptUnitNumber(ADDRESS5_APT);
    ADDRESS_DTO_5.setStreetAddress(SAMPLE_ROOSST_STREETADDRESS);
    ADDRESS_DTO_5.setCity(CITY);
    ADDRESS_DTO_5.setState(STATE);
    ADDRESS_DTO_5.setZipcode(SAMPLE_ROOSST_ZIPCODE);

    ADDRESS_DTO_3 = new AddressDto();
    ADDRESS_DTO_3.setId(3);
    ADDRESS_DTO_3.setAptUnitNumber(ADDRESS3_APT);
    ADDRESS_DTO_3.setStreetAddress(SAMPLE_MAINST_STREETADDRESS);
    ADDRESS_DTO_3.setCity(CITY);
    ADDRESS_DTO_3.setState(STATE);
    ADDRESS_DTO_3.setZipcode(SAMPLE_MAINST_ZIPCODE);
  }

  //__HARDWARE__
  public static final String ANTENNA         = "antenna";
  public static final String OUTDOORROUTER   = "outdoor-router";
  public static final String INDOORROUTER    = "indoor-router";
  public static final String RJ45            = "RJ45";
  public static final String OUTDOORETHCABLE = "outdoor-ethernet-cable";

  
  public static final Hardware HARDWARE1 = Hardware.builder().id(1).name(ANTENNA).build();
  public static final Hardware HARDWARE2 = Hardware.builder().id(2).name(OUTDOORROUTER).build();
  public static final Hardware HARDWARE3 = Hardware.builder().id(3).name(INDOORROUTER).build();
  public static final Hardware HARDWARE4 = Hardware.builder().id(4).name(RJ45).build();
  public static final Hardware HARDWARE5 = Hardware.builder().id(5).name(OUTDOORETHCABLE).build();

  //__HARDWARE_DTO__
  public static final HardwareDto HARDWARE_DTO_1;
  public static final HardwareDto HARDWARE_DTO_2;
  public static final HardwareDto HARDWARE_DTO_3;
  public static final HardwareDto HARDWARE_DTO_4;
  public static final HardwareDto HARDWARE_DTO_5;

  static{
    HARDWARE_DTO_1 = new HardwareDto();
    HARDWARE_DTO_2 = new HardwareDto();
    HARDWARE_DTO_3 = new HardwareDto();
    HARDWARE_DTO_4 = new HardwareDto();
    HARDWARE_DTO_5 = new HardwareDto();

    HARDWARE_DTO_1.setId(1);
    HARDWARE_DTO_1.setName(ANTENNA);

    HARDWARE_DTO_2.setId(2);
    HARDWARE_DTO_2.setName(OUTDOORROUTER);
    
    HARDWARE_DTO_3.setId(3);
    HARDWARE_DTO_3.setName(INDOORROUTER);
    
    HARDWARE_DTO_4.setId(4);
    HARDWARE_DTO_4.setName(RJ45);
    
    HARDWARE_DTO_5.setId(5);
    HARDWARE_DTO_5.setName(OUTDOORETHCABLE);
  }

  // ___PERSON___
  public static final String EMAIL_DOMAIN = "@domain.com";

  public static final Person JOHNV  = new Person(1, "John", "Valjohn", "ASD", "JValjohn"+EMAIL_DOMAIN, "1234567890");
  public static final Person TOMJ   = new Person(2, "Tom", "Jerry", "FGH", "TJerry"+EMAIL_DOMAIN, "");
  public static final Person PAULE  = new Person(3, "Paul", "Eadee", "", "PEadee"+EMAIL_DOMAIN, "4591267893");
  public static final Person JULIEA = new Person(4, "Julie", "Ah", "QWE", "JAh"+EMAIL_DOMAIN, "2583691743");
  public static final Person ANTONY = new Person(5, "Anton", "Yo", "RTY", "AYo"+EMAIL_DOMAIN, "1597534682");

  // __PERSON_COMMAND__

  public static final PersonDto JOHNV_COMMAND;
  public static final PersonDto TOMJ_COMMAND;
  public static final PersonDto PAULE_COMMAND;
  public static final PersonDto JULIEA_COMMAND;
  public static final PersonDto ANTONY_COMMAND;
  public static final PersonDto VALID_UNREGISTERED_USER_COMMAND;
  public static final PersonDto INVALID_UNREGISTERED_USER_COMMAND;

  static {
    JOHNV_COMMAND = new PersonDto();
    TOMJ_COMMAND = new PersonDto();
    PAULE_COMMAND = new PersonDto();
    JULIEA_COMMAND = new PersonDto();
    ANTONY_COMMAND = new PersonDto();
    VALID_UNREGISTERED_USER_COMMAND = new PersonDto();
    INVALID_UNREGISTERED_USER_COMMAND = new PersonDto();

    JOHNV_COMMAND.setId(JOHNV.getId());
    JOHNV_COMMAND.setFirstName(JOHNV.getFirstName());
    JOHNV_COMMAND.setLastName(JOHNV.getLastName());
    JOHNV_COMMAND.setSlackId(JOHNV.getSlackId());
    JOHNV_COMMAND.setEmail(JOHNV.getEmail());
    JOHNV_COMMAND.setPhoneNumber(JOHNV.getPhoneNumber());

    TOMJ_COMMAND.setId(TOMJ.getId());
    TOMJ_COMMAND.setFirstName(TOMJ.getFirstName());
    TOMJ_COMMAND.setLastName(TOMJ.getLastName());
    TOMJ_COMMAND.setSlackId(TOMJ.getSlackId());
    TOMJ_COMMAND.setEmail(TOMJ.getEmail());
    TOMJ_COMMAND.setPhoneNumber(TOMJ.getPhoneNumber());

    PAULE_COMMAND.setId(PAULE.getId());
    PAULE_COMMAND.setFirstName(PAULE.getFirstName());
    PAULE_COMMAND.setLastName(PAULE.getLastName());
    PAULE_COMMAND.setSlackId(PAULE.getSlackId());
    PAULE_COMMAND.setEmail(PAULE.getEmail());
    PAULE_COMMAND.setPhoneNumber(PAULE.getPhoneNumber());

    JULIEA_COMMAND.setId(JULIEA.getId());
    JULIEA_COMMAND.setFirstName(JULIEA.getFirstName());
    JULIEA_COMMAND.setLastName(JULIEA.getLastName());
    JULIEA_COMMAND.setSlackId(JULIEA.getSlackId());
    JULIEA_COMMAND.setEmail(JULIEA.getEmail());
    JULIEA_COMMAND.setPhoneNumber(JULIEA.getPhoneNumber());

    ANTONY_COMMAND.setId(ANTONY.getId());
    ANTONY_COMMAND.setFirstName(ANTONY.getFirstName());
    ANTONY_COMMAND.setLastName(ANTONY.getLastName());
    ANTONY_COMMAND.setSlackId(ANTONY.getSlackId());
    ANTONY_COMMAND.setEmail(ANTONY.getEmail());
    ANTONY_COMMAND.setPhoneNumber(ANTONY.getPhoneNumber());

    VALID_UNREGISTERED_USER_COMMAND.setId(777); // no validation
    VALID_UNREGISTERED_USER_COMMAND.setFirstName("Daniel");
    VALID_UNREGISTERED_USER_COMMAND.setLastName("Seven");
    VALID_UNREGISTERED_USER_COMMAND.setSlackId(""); // no validation
    VALID_UNREGISTERED_USER_COMMAND.setEmail("luxury@cars.com");
    VALID_UNREGISTERED_USER_COMMAND.setPhoneNumber("2127777777");

    INVALID_UNREGISTERED_USER_COMMAND.setId(999); // no validation
    INVALID_UNREGISTERED_USER_COMMAND.setFirstName("E");
    INVALID_UNREGISTERED_USER_COMMAND.setLastName("2DAYSSSSSSSSSSSSSSSSSSSS");
    INVALID_UNREGISTERED_USER_COMMAND.setSlackId(""); // no validation
    INVALID_UNREGISTERED_USER_COMMAND.setEmail("badEmail.ew");
    INVALID_UNREGISTERED_USER_COMMAND.setPhoneNumber("1800588230");
  }
  //__INSTALL_PARTICIPANT_DTO__
  public static final InstallParticipantDto JOHNV_PARTICIPANT;
  public static final InstallParticipantDto TOMJ_PARTICIPANT;
  public static final InstallParticipantDto PAULE_PARTICIPANT;
  public static final InstallParticipantDto JULIEA_PARTICIPANT;
  public static final InstallParticipantDto ANTONY_PARTICIPANT;

  static {
    JOHNV_PARTICIPANT = new InstallParticipantDto();
    TOMJ_PARTICIPANT = new InstallParticipantDto();
    PAULE_PARTICIPANT = new InstallParticipantDto();
    JULIEA_PARTICIPANT = new InstallParticipantDto();
    ANTONY_PARTICIPANT = new InstallParticipantDto();

    JOHNV_PARTICIPANT.setInstallerId(JOHNV.getId());
    JOHNV_PARTICIPANT.setName(String.join(" ", JOHNV.getFirstName(), JOHNV.getLastName()));
    JOHNV_PARTICIPANT.setSlackId(JOHNV.getSlackId());

    TOMJ_PARTICIPANT.setInstallerId(TOMJ.getId());
    TOMJ_PARTICIPANT.setName(String.join(" ", TOMJ.getFirstName(), TOMJ.getLastName()));
    TOMJ_PARTICIPANT.setSlackId(TOMJ.getSlackId());

    PAULE_PARTICIPANT.setInstallerId(PAULE.getId());
    PAULE_PARTICIPANT.setName(String.join(" ", PAULE.getFirstName(), PAULE.getLastName()));
    PAULE_PARTICIPANT.setSlackId(PAULE.getSlackId());

    JULIEA_PARTICIPANT.setInstallerId(JULIEA.getId());
    JULIEA_PARTICIPANT.setName(String.join(" ", JULIEA.getFirstName(), JULIEA.getLastName()));
    JULIEA_PARTICIPANT.setSlackId(JULIEA.getSlackId());

    ANTONY_PARTICIPANT.setInstallerId(ANTONY.getId());
    ANTONY_PARTICIPANT.setName(String.join(" ", ANTONY.getFirstName(), ANTONY.getLastName()));
    ANTONY_PARTICIPANT.setSlackId(ANTONY.getSlackId());
  }
  

  // ___MEMBER___
  public static final Member MEMBER_JOHNV =
    Member.builder()
    .id(1)
    .person(JOHNV)
    .roleGivenDate(LocalDate.parse("2015-03-10"))
    .status(MemberStatus.ACTIVE)
    .build();
  
  public static final Member MEMBER_PAULE  =
    Member.builder()
    .id(2)
    .person(PAULE)
    .roleGivenDate(LocalDate.parse("2015-11-22"))
    .status(MemberStatus.ACTIVE)
    .build();
  
  public static final Member MEMBER_JULIEA =
    Member.builder()
    .id(3)
    .person(JULIEA)
    .roleGivenDate(LocalDate.parse("2014-11-19"))
    .status(MemberStatus.ACTIVE)
    .build();

  public static final Member MEMBER_ANTONY =
    Member.builder()
    .id(4)
    .person(ANTONY)
    .roleGivenDate(LocalDate.parse("2017-07-12"))
    .status(MemberStatus.ACTIVE)
    .build();

  // ___INSTALLER___
  public static final Installer INSTALLER_JOHNV =
    Installer.builder()
    .id(1)
    .person(JOHNV)
    .roleGivenDate(LocalDate.parse("2015-11-22"))
    .status(InstallerStatus.LEAD)
    .build();
  
  public static final Installer INSTALLER_TOMJ  =
    Installer.builder()
    .id(2)
    .person(TOMJ)
    .roleGivenDate(LocalDate.parse("2014-03-10"))
    .status(InstallerStatus.ASSISTANT)
    .build();
  
  public static final Installer INSTALLER_JULIEA =
    Installer.builder()
    .id(3)
    .person(JULIEA)
    .roleGivenDate(LocalDate.parse("2013-11-19"))
    .status(InstallerStatus.LEAD)
    .build();

  //__INSTALLERDTO__
  public static final InstallerDto INSTALLERDTO_JOHNV;
  public static final InstallerDto INSTALLERDTO_TOMJ;
  public static final InstallerDto INSTALLERDTO_JULIEA;

  static{
    INSTALLERDTO_JOHNV = new InstallerDto();
    INSTALLERDTO_TOMJ = new InstallerDto();
    INSTALLERDTO_JULIEA = new InstallerDto();

    INSTALLERDTO_JOHNV.setId(INSTALLER_JOHNV.getId());
    INSTALLERDTO_JOHNV.setPerson(JOHNV_COMMAND);
    INSTALLERDTO_JOHNV.setRoleGivenDate(INSTALLER_JOHNV.getRoleGivenDate());
    INSTALLERDTO_JOHNV.setStatus(INSTALLER_JOHNV.getStatus().installerStatusName());

    INSTALLERDTO_TOMJ.setId(INSTALLER_TOMJ.getId());
    INSTALLERDTO_TOMJ.setPerson(TOMJ_COMMAND);
    INSTALLERDTO_TOMJ.setRoleGivenDate(INSTALLER_TOMJ.getRoleGivenDate());
    INSTALLERDTO_TOMJ.setStatus(INSTALLER_TOMJ.getStatus().installerStatusName());

    INSTALLERDTO_JULIEA.setId(INSTALLER_JULIEA.getId());
    INSTALLERDTO_JULIEA.setPerson(JULIEA_COMMAND);
    INSTALLERDTO_JULIEA.setRoleGivenDate(INSTALLER_JULIEA.getRoleGivenDate());
    INSTALLERDTO_JULIEA.setStatus(INSTALLER_JULIEA.getStatus().installerStatusName());

  }

  // ___NODE___

  public static final Node NODE1 = Node.builder()
    .id(1)
    .installRequestId(1)
    .status("ACTIVE")
    .address(ADDRESS5)
    .hardwareSet(Set.of(HARDWARE1, HARDWARE2))
    .build();
  
  public static final Node NODE2 = Node.builder()
    .id(2)
    .installRequestId(2)
    .status("ACTIVE")
    .address(ADDRESS4)
    .hardwareSet(Set.of(HARDWARE2))
    .build();
  
  public static final Node NODE3 = Node.builder()
    .id(3)
    .installRequestId(3)
    .status("ACTIVE")
    .address(ADDRESS2)
    .hardwareSet(Set.of(HARDWARE1, HARDWARE2))
    .build();

  public static final Node LAZY_NODE1 = Node.builder()
    .id(1)
    .installRequestId(1)
    .status("ACTIVE")
    .address(ADDRESS5)
    .build();
  
  public static final Node LAZY_NODE2 = Node.builder()
    .id(2)
    .installRequestId(2)
    .status("ACTIVE")
    .address(ADDRESS4)
    .build();
  
  public static final Node LAZY_NODE3 = Node.builder()
    .id(3)
    .installRequestId(3)
    .status("ACTIVE")
    .address(ADDRESS2)
    .build();

  //__NODE_DTO__
  public static final NodeDto NODE_DTO_1;

  static{
    NODE_DTO_1 = new NodeDto();
    NODE_DTO_1.setId(1);
    NODE_DTO_1.setInstallRequestId(1);
    NODE_DTO_1.setStatus("ACTIVE");
    NODE_DTO_1.setAddress(ADDRESS_DTO_5);
    NODE_DTO_1.setHardware(List.of(HARDWARE_DTO_1, HARDWARE_DTO_2));
  }

  // ___INSTALL___
  public static final Install INSTALL1 = Install.builder()
    .id(1)
    .installRequestId(1)
    .status("ACTIVE")
    .address(ADDRESS5)
    .hardwareSet(Set.of(HARDWARE3, HARDWARE4))
    .node(NODE1)
    .build();
  
  public static final Install INSTALL2 = Install.builder()
    .id(2)
    .installRequestId(2)
    .status("ACTIVE")
    .address(ADDRESS4)
    .hardwareSet(Set.of(HARDWARE3, HARDWARE4))
    .node(NODE2)
    .build();
  
  public static final Install INSTALL3 = Install.builder()
    .id(3)
    .installRequestId(3)
    .status("ACTIVE")
    .address(ADDRESS1)
    .hardwareSet(Set.of(HARDWARE3, HARDWARE4))
    .node(NODE3)
    .build();
  
  public static final Install INSTALL4 = Install.builder()
    .id(4)
    .installRequestId(4)
    .status("ACTIVE")
    .address(ADDRESS2)
    .hardwareSet(Set.of(HARDWARE3, HARDWARE4))
    .node(NODE3)
    .build();
  
  //__INSTALL_REQUEST_APPOINTMENT__

  public static final String[] APPT1_TIMES = {"2017-08-17T12:30:00", "2017-08-17T14:30:00"};
  public static final String[] APPT2_TIMES = {"2017-08-18T08:10:00", "2017-08-18T10:10:00"};
  public static final String[] APPT3_TIMES = {"2017-08-20T17:45:00", "2017-08-20T20:45:00"};

  public static final boolean APPT1_ISSELECTED = true;
  public static final boolean APPT2_ISSELECTED = false;
  public static final boolean APPT3_ISSELECTED = false;
  
  public static final InstallRequestAppointment INSTALL_REQUEST_APPOINTMENT_1 = InstallRequestAppointment.builder()
    .id(1)
    .installRequestId(5)
    .selected(APPT1_ISSELECTED)
    .appointementStartTime(LocalDateTime.parse(APPT1_TIMES[0]))
    .appointementEndTime(LocalDateTime.parse(APPT1_TIMES[1]))
    .build();

  public static final InstallRequestAppointment INSTALL_REQUEST_APPOINTMENT_2 = InstallRequestAppointment.builder()
    .id(2)
    .installRequestId(5)
    .selected(APPT2_ISSELECTED)
    .appointementStartTime(LocalDateTime.parse(APPT2_TIMES[0]))
    .appointementEndTime(LocalDateTime.parse(APPT2_TIMES[1]))
    .build();

  public static final InstallRequestAppointment INSTALL_REQUEST_APPOINTMENT_3 = InstallRequestAppointment.builder()
    .id(3)
    .installRequestId(5)
    .selected(APPT3_ISSELECTED)
    .appointementStartTime(LocalDateTime.parse(APPT3_TIMES[0]))
    .appointementEndTime(LocalDateTime.parse(APPT3_TIMES[1]))
    .build();

  // __DTO2ENTITY__
  // SampleObjects containing expected values for NewInstallRequestDto2InstallRequestIT
  // appt objects of NewInstallRequestDto are new appts; not yet persisted -> no ids
  public static final InstallRequestAppointment INSTALL_REQUEST_APPOINTMENT_DTO2ENTITY_1 
    = InstallRequestAppointment.builder()
    .id(null)
    .installRequestId(null)
    .selected(APPT1_ISSELECTED)
    .appointementStartTime(LocalDateTime.parse(APPT1_TIMES[0]))
    .appointementEndTime(LocalDateTime.parse(APPT1_TIMES[1]))
    .build();

  public static final InstallRequestAppointment INSTALL_REQUEST_APPOINTMENT_DTO2ENTITY_2 
    = InstallRequestAppointment.builder()
    .id(null)
    .installRequestId(null)
    .selected(APPT2_ISSELECTED)
    .appointementStartTime(LocalDateTime.parse(APPT2_TIMES[0]))
    .appointementEndTime(LocalDateTime.parse(APPT2_TIMES[1]))
    .build();

  //__INSTALL_REQUEST_APPOINTMENT_DTO__ used for new request (NewInstallRequestDto to be converted to InstallRequest) 
  public static final InstallRequestAppointmentDto INSTALL_REQUEST_APPOINTMENT_DTO_1;
  public static final InstallRequestAppointmentDto INSTALL_REQUEST_APPOINTMENT_DTO_2;
  public static final InstallRequestAppointmentDto INSTALL_REQUEST_APPOINTMENT_DTO_3;

  static{

    INSTALL_REQUEST_APPOINTMENT_DTO_1 = new InstallRequestAppointmentDto();
    INSTALL_REQUEST_APPOINTMENT_DTO_1.setId(null);
    INSTALL_REQUEST_APPOINTMENT_DTO_1.setInstallRequestId(null);
    INSTALL_REQUEST_APPOINTMENT_DTO_1.setSelected(APPT1_ISSELECTED);
    INSTALL_REQUEST_APPOINTMENT_DTO_1.setAppointementStartTime(LocalDateTime.parse(APPT1_TIMES[0]));
    INSTALL_REQUEST_APPOINTMENT_DTO_1.setAppointementEndTime(LocalDateTime.parse(APPT1_TIMES[1]));

    INSTALL_REQUEST_APPOINTMENT_DTO_2 = new InstallRequestAppointmentDto();
    INSTALL_REQUEST_APPOINTMENT_DTO_2.setId(null);
    INSTALL_REQUEST_APPOINTMENT_DTO_2.setInstallRequestId(null);
    INSTALL_REQUEST_APPOINTMENT_DTO_2.setSelected(APPT2_ISSELECTED);
    INSTALL_REQUEST_APPOINTMENT_DTO_2.setAppointementStartTime(LocalDateTime.parse(APPT2_TIMES[0]));
    INSTALL_REQUEST_APPOINTMENT_DTO_2.setAppointementEndTime(LocalDateTime.parse(APPT2_TIMES[1]));

    INSTALL_REQUEST_APPOINTMENT_DTO_3 = new InstallRequestAppointmentDto();
    INSTALL_REQUEST_APPOINTMENT_DTO_3.setId(null);
    INSTALL_REQUEST_APPOINTMENT_DTO_3.setInstallRequestId(null);
    INSTALL_REQUEST_APPOINTMENT_DTO_3.setSelected(APPT3_ISSELECTED);
    INSTALL_REQUEST_APPOINTMENT_DTO_3.setAppointementStartTime(LocalDateTime.parse(APPT3_TIMES[0]));
    INSTALL_REQUEST_APPOINTMENT_DTO_3.setAppointementEndTime(LocalDateTime.parse(APPT3_TIMES[1]));
  }

  //__INSTALL_REQUEST_APPOINTMENT_DTO__ (persisted versions -> contains ids)
  public static final InstallRequestAppointmentDto INSTALL_REQUEST_APPOINTMENT_DBDTO_1;
  public static final InstallRequestAppointmentDto INSTALL_REQUEST_APPOINTMENT_DBDTO_2;
  public static final InstallRequestAppointmentDto INSTALL_REQUEST_APPOINTMENT_DBDTO_3;

  static{

    INSTALL_REQUEST_APPOINTMENT_DBDTO_1 = new InstallRequestAppointmentDto();
    INSTALL_REQUEST_APPOINTMENT_DBDTO_1.setId(INSTALL_REQUEST_APPOINTMENT_1.getId());
    INSTALL_REQUEST_APPOINTMENT_DBDTO_1.setInstallRequestId(INSTALL_REQUEST_APPOINTMENT_1.getInstallRequestId());
    INSTALL_REQUEST_APPOINTMENT_DBDTO_1.setSelected(APPT1_ISSELECTED);
    INSTALL_REQUEST_APPOINTMENT_DBDTO_1.setAppointementStartTime(LocalDateTime.parse(APPT1_TIMES[0]));
    INSTALL_REQUEST_APPOINTMENT_DBDTO_1.setAppointementEndTime(LocalDateTime.parse(APPT1_TIMES[1]));

    INSTALL_REQUEST_APPOINTMENT_DBDTO_2 = new InstallRequestAppointmentDto();
    INSTALL_REQUEST_APPOINTMENT_DBDTO_2.setId(INSTALL_REQUEST_APPOINTMENT_2.getId());
    INSTALL_REQUEST_APPOINTMENT_DBDTO_2.setInstallRequestId(INSTALL_REQUEST_APPOINTMENT_2.getInstallRequestId());
    INSTALL_REQUEST_APPOINTMENT_DBDTO_2.setSelected(APPT2_ISSELECTED);
    INSTALL_REQUEST_APPOINTMENT_DBDTO_2.setAppointementStartTime(LocalDateTime.parse(APPT2_TIMES[0]));
    INSTALL_REQUEST_APPOINTMENT_DBDTO_2.setAppointementEndTime(LocalDateTime.parse(APPT2_TIMES[1]));
    
    INSTALL_REQUEST_APPOINTMENT_DBDTO_3 = new InstallRequestAppointmentDto();
    INSTALL_REQUEST_APPOINTMENT_DBDTO_3.setId(INSTALL_REQUEST_APPOINTMENT_3.getId());
    INSTALL_REQUEST_APPOINTMENT_DBDTO_3.setInstallRequestId(INSTALL_REQUEST_APPOINTMENT_3.getInstallRequestId());
    INSTALL_REQUEST_APPOINTMENT_DBDTO_3.setSelected(APPT3_ISSELECTED);
    INSTALL_REQUEST_APPOINTMENT_DBDTO_3.setAppointementStartTime(LocalDateTime.parse(APPT3_TIMES[0]));
    INSTALL_REQUEST_APPOINTMENT_DBDTO_3.setAppointementEndTime(LocalDateTime.parse(APPT3_TIMES[1]));
  }

  //__INSTALL_REQUEST__

  public static final InstallRequest INSTALL_REQUEST_1 = InstallRequest.builder()
    .id(1)
    .requestType(InstallRequestType.NODE)
    .requesterPerson(JOHNV)
    .address(ADDRESS5)
    .leadInstaller(INSTALLER_JULIEA)
    .requesterNotes("Note by JVm")
    .leadNotes("Note by JAi1")
    .floorNumber(3)
    .requestedDate(LocalDate.parse("2015-02-22"))
    .claimedDate(LocalDate.parse("2015-02-27"))
    .completedDate(LocalDate.parse("2015-03-10"))
    .hardwareSet(Set.of(HARDWARE1, HARDWARE2, HARDWARE3, HARDWARE4))
    .assistantInstallerSet(Set.of(INSTALLER_TOMJ))
    .build();

  public static final InstallRequest INSTALL_REQUEST_2 = InstallRequest.builder()
    .id(2)
    .requestType(InstallRequestType.NODE)
    .requesterPerson(PAULE)
    .address(ADDRESS4)
    .leadInstaller(INSTALLER_JULIEA)
    .requesterNotes("Note by PEm")
    .leadNotes("Note by JAi2")
    .floorNumber(11)
    .requestedDate(LocalDate.parse("2015-11-11"))
    .claimedDate(LocalDate.parse("2015-11-14"))
    .completedDate(LocalDate.parse("2015-11-22"))
    .hardwareSet(Set.of(HARDWARE2, HARDWARE3, HARDWARE4))
    .assistantInstallerSet(Set.of(INSTALLER_JOHNV))
    .build();

  public static final InstallRequest INSTALL_REQUEST_3 = InstallRequest.builder()
    .id(3)
    .requestType(InstallRequestType.DIY)
    .requesterPerson(JULIEA)
    .address(ADDRESS1)
    .leadInstaller(INSTALLER_JULIEA)
    .requesterNotes("Note by JAm")
    .leadNotes("Note by JAi3")
    .floorNumber(2)
    .requestedDate(LocalDate.parse("2014-11-19"))
    .claimedDate(LocalDate.parse("2014-11-19"))
    .completedDate(LocalDate.parse("2014-11-19"))
    .hardwareSet(Set.of(HARDWARE1, HARDWARE2, HARDWARE3, HARDWARE4))
    .build();

  public static final InstallRequest INSTALL_REQUEST_4 = InstallRequest.builder()
    .id(4)
    .requestType(InstallRequestType.CABLE_RUN)
    .requesterPerson(ANTONY)
    .address(ADDRESS2)
    .leadInstaller(INSTALLER_JOHNV)
    .requesterNotes("Note by AYm")
    .leadNotes("Note by JVi1")
    .floorNumber(5)
    .requestedDate(LocalDate.parse("2017-07-07"))
    .claimedDate(LocalDate.parse("2017-07-10"))
    .completedDate(LocalDate.parse("2017-07-12"))
    .hardwareSet(Set.of(HARDWARE3, HARDWARE4))
    .assistantInstallerSet(Set.of(INSTALLER_TOMJ))
    .build();

  //no los because is cablerun
  public static final InstallRequest INSTALL_REQUEST_5 = InstallRequest.builder()
    .id(5)
    .requestType(InstallRequestType.CABLE_RUN)
    .requesterPerson(JULIEA)
    .address(ADDRESS3)
    .leadInstaller(INSTALLER_JOHNV)
    .requesterNotes("Note by JAm2")
    .leadNotes("Note by JVi2")
    .floorNumber(5)
    .requestedDate(LocalDate.parse("2017-08-12"))
    .claimedDate(LocalDate.parse("2017-08-13"))
    .completedDate(null)
    .hardwareSet(Set.of(HARDWARE3, HARDWARE4))
    .preferedAppointmentTimes(Set.of(INSTALL_REQUEST_APPOINTMENT_1, INSTALL_REQUEST_APPOINTMENT_2, INSTALL_REQUEST_APPOINTMENT_3))
    .build();

  //__NEW_INSTALL_REQUEST_DTO__
  public static final NewInstallRequestDto NEW_INSTALL_REQUEST_DTO_1;

  static{
    NEW_INSTALL_REQUEST_DTO_1 = new NewInstallRequestDto();

    NEW_INSTALL_REQUEST_DTO_1.setRequestType(InstallRequestType.CABLE_RUN.installTypeName());
    NEW_INSTALL_REQUEST_DTO_1.setRequesterPersonId(JOHNV_COMMAND.getId());
    NEW_INSTALL_REQUEST_DTO_1.setAddress(ADDRESS_DTO_6);
    NEW_INSTALL_REQUEST_DTO_1.setRequesterNotes("new install request dto 1 notes");
    NEW_INSTALL_REQUEST_DTO_1.setFloorNumber(3);
    NEW_INSTALL_REQUEST_DTO_1.setPreferedAppointmentTimes(List.of(INSTALL_REQUEST_APPOINTMENT_DTO_1, INSTALL_REQUEST_APPOINTMENT_DTO_2));
    NEW_INSTALL_REQUEST_DTO_1.setNodesLos(List.of(NODE_DTO_1.getId()));
  }

  //__INSTALL_REQUEST_DTO__
  public static final InstallRequestDto INSTALL_REQUEST_DTO_5;

  static {
    INSTALL_REQUEST_DTO_5 = new InstallRequestDto();
    INSTALL_REQUEST_DTO_5.setId(5);
    INSTALL_REQUEST_DTO_5.setRequestType(InstallRequestType.CABLE_RUN.installTypeName());
    INSTALL_REQUEST_DTO_5.setRequesterPerson(JULIEA_COMMAND);
    INSTALL_REQUEST_DTO_5.setAddress(ADDRESS_DTO_3);
    INSTALL_REQUEST_DTO_5.setLeadInstaller(JOHNV_PARTICIPANT);
    INSTALL_REQUEST_DTO_5.setRequesterNotes("Note by JAm2");
    INSTALL_REQUEST_DTO_5.setLeadNotes("Note by JVi2");
    INSTALL_REQUEST_DTO_5.setFloorNumber(5);
    INSTALL_REQUEST_DTO_5.setRequestedDate(LocalDate.parse("2017-08-12"));
    INSTALL_REQUEST_DTO_5.setClaimedDate(LocalDate.parse("2017-08-13"));
    //INSTALL_REQUEST_DTO_5.setCompletedDate();
    INSTALL_REQUEST_DTO_5.setPreferedAppointmentTimes(Set.of(INSTALL_REQUEST_APPOINTMENT_DBDTO_1, INSTALL_REQUEST_APPOINTMENT_DBDTO_2, INSTALL_REQUEST_APPOINTMENT_DBDTO_3));
    //INSTALL_REQUEST_DTO_5.setNodesLos();
    INSTALL_REQUEST_DTO_5.setHardwares(Set.of(HARDWARE_DTO_3, HARDWARE_DTO_4));
    //INSTALL_REQUEST_DTO_5.setAssistantInstallerSet();
  }




}
