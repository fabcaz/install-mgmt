package com.example.installmgmt.resources;

import java.time.LocalDate;
import java.util.Set;

import com.example.installmgmt.domain.Address;
import com.example.installmgmt.domain.Hardware;
import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.domain.Member;
import com.example.installmgmt.domain.Node;
import com.example.installmgmt.domain.Person;

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

  

  public static final Address ADDRESS1 = Address.builder().id(1)
    .aptUnitNumber("apt 2h").streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  public static final Address ADDRESS2 = Address.builder().id(2)
    .aptUnitNumber("apt 5b").streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  public static final Address ADDRESS3 = Address.builder().id(3)
    .aptUnitNumber("apt 5c").streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  public static final Address ADDRESS4 = Address.builder().id(4)
    .aptUnitNumber("apt 1102").streetAddress(SAMPLE_BRIDGEST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_BRIDGEST_ZIPCODE)
    .build();

  public static final Address ADDRESS5 = Address.builder().id(5)
    .aptUnitNumber("apt 3a").streetAddress(SAMPLE_ROOSST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_ROOSST_ZIPCODE)
    .build();

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

  // ___PERSON___
  public static final String EMAIL_DOMAIN = "@domain.com";

  public static final Person JOHNV  = new Person(1, "John", "Valjohn", "ASD", "JValjohn"+EMAIL_DOMAIN, "1234567890");
  public static final Person TOMJ   = new Person(2, "Tom", "Jerry", "FGH", "TJerry"+EMAIL_DOMAIN, "");
  public static final Person PAULE  = new Person(3, "Paul", "Eadee", "", "PEadee"+EMAIL_DOMAIN, "4591267893");
  public static final Person JULIEA = new Person(4, "Julie", "Ah", "QWE", "JAh"+EMAIL_DOMAIN, "2583691743");
  public static final Person ANTONY = new Person(5, "Anton", "Yo", "RTY", "AYo"+EMAIL_DOMAIN, "1597534682");

  // ___MEMBER___
  public static final Member MEMBER_JOHNV =
    Member.builder()
    .id(1)
    .person(JOHNV)
    .roleGivenDate(LocalDate.parse("2015-03-10"))
    .status("ACTIVE")
    .build();
  
  public static final Member MEMBER_PAULE  =
    Member.builder()
    .id(2)
    .person(PAULE)
    .roleGivenDate(LocalDate.parse("2015-11-22"))
    .status("ACTIVE")
    .build();
  
  public static final Member MEMBER_JULIEA =
    Member.builder()
    .id(3)
    .person(JULIEA)
    .roleGivenDate(LocalDate.parse("2014-11-19"))
    .status("ACTIVE")
    .build();

  public static final Member MEMBER_ANTONY =
    Member.builder()
    .id(4)
    .person(ANTONY)
    .roleGivenDate(LocalDate.parse("2017-07-12"))
    .status("ACTIVE")
    .build();

  // ___INSTALLER___
  public static final Installer INSTALLER_JOHNV =
    Installer.builder()
    .id(1)
    .person(JOHNV)
    .roleGivenDate(LocalDate.parse("2015-11-22"))
    .status("LEAD")
    .build();
  
  public static final Installer INSTALLER_TOMJ  =
    Installer.builder()
    .id(2)
    .person(TOMJ)
    .roleGivenDate(LocalDate.parse("2014-03-10"))
    .status("ASSISTANT")
    .build();
  
  public static final Installer INSTALLER_JULIEA =
    Installer.builder()
    .id(3)
    .person(JULIEA)
    .roleGivenDate(LocalDate.parse("2013-11-19"))
    .status("LEAD")
    .build();

  // ___NODE___

  public static Node NODE1 = Node.builder()
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


  // ___INSTALL___
}
