package com.example.installmgmt.repositories;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.installmgmt.domain.Address;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AddressRepositoryIT {
  
  private static final String CITY = "New York";
  private static final String STATE = "NY";

  private static final String SAMPLE_MAINST_STREETADDRESS = "123 main Street";
  private static final int SAMPLE_MAINST_ZIPCODE = 10022;

  private static final String SAMPLE_BRIDGEST_STREETADDRESS = "456 bridge Street";
  private static final int SAMPLE_BRIDGEST_ZIPCODE = 11211;

  private static final String SAMPLE_ROOSST_STREETADDRESS = "789 Roos Street";
  private static final int SAMPLE_ROOSST_ZIPCODE = 10044;

  

  private static final Address expectedAdrs1 = Address.builder().id(1)
    .aptUnitNumber("apt 2h").streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  private static final Address expectedAdrs2 = Address.builder().id(2)
    .aptUnitNumber("apt 5b").streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  private static final Address expectedAdrs3 = Address.builder().id(3)
    .aptUnitNumber("apt 5c").streetAddress(SAMPLE_MAINST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_MAINST_ZIPCODE)
    .build();

  private static final Address expectedAdrs4 = Address.builder().id(4)
    .aptUnitNumber("apt 1102").streetAddress(SAMPLE_BRIDGEST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_BRIDGEST_ZIPCODE)
    .build();

  private static final Address expectedAdrs5 = Address.builder().id(5)
    .aptUnitNumber("apt 3a").streetAddress(SAMPLE_ROOSST_STREETADDRESS)
    .city(CITY).state(STATE).zipcode(SAMPLE_ROOSST_ZIPCODE)
    .build();

  @Autowired
  AddressRepository addressRepo;

  //sanity check
  @Test
  @DisplayName("should get addresses having given City")
  public void verifyInsert(){


    Address expectedInsertedAddress = new Address(null, "apt 555b", SAMPLE_MAINST_STREETADDRESS, CITY, STATE, SAMPLE_MAINST_ZIPCODE);
    long initialCount = addressRepo.count();

    assertEquals(5, initialCount, "initial count mismatch");

    Address actualInsertedAddress = addressRepo.save(expectedInsertedAddress);
    List<Address> actualAllAddressList = addressRepo.findAll();

    long afterInsertCount = addressRepo.count();

    assertEquals(6, afterInsertCount, "initial count mismatch");

    assertEquals(6, actualAllAddressList.size(), "after insert count mismatch");

    assertNotNull(expectedInsertedAddress.getId(), "Address inserted by AddressRepo did not get an Id");
    assertEquals(actualInsertedAddress.getAptUnitNumber(), expectedInsertedAddress.getAptUnitNumber());
    assertEquals(actualInsertedAddress.getStreetAddress(),SAMPLE_MAINST_STREETADDRESS);
    assertEquals(actualInsertedAddress.getCity(),CITY);
    assertEquals(actualInsertedAddress.getState(),STATE);
    assertEquals(actualInsertedAddress.getZipcode(), SAMPLE_MAINST_ZIPCODE);
  }


  @Test
  @DisplayName("should get addresses having given streetAddress and zipcode")
  public void verifyFindByStreetAddressAndZipcode_validArgs(){

    List<Address> expectedByCityAndZipcodeAddressList = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3
      );
    
    List<Address> actualByCityAndZipcodeAddressList  = addressRepo.findAllByStreetAddressAndZipcode(SAMPLE_MAINST_STREETADDRESS, SAMPLE_MAINST_ZIPCODE);
    assertEquals(3, actualByCityAndZipcodeAddressList.size());
    assertEquals(expectedByCityAndZipcodeAddressList, actualByCityAndZipcodeAddressList,  "by city and zipcode list mismatch");
  }

  @Test
  @DisplayName("should get addresses with given street address")
  public void verifyFindByStreetAddress(){
    List<Address> expectedByStreetAddressList = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3
      );

    List<Address> actualByStreetAddressList = addressRepo.findAllByStreetAddress(SAMPLE_MAINST_STREETADDRESS);

    assertEquals(3, actualByStreetAddressList.size());
    assertEquals(expectedByStreetAddressList, actualByStreetAddressList,  "by city and zipcode list mismatch");
  }

  @Test
  @DisplayName("should get addresses with given zipcode")
  public void verifyFindByZipcode(){
    List<Address> expectedByZipcodeList1 = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3
      );

    List<Address> actualByZipcodeList1 = addressRepo.findAllByZipcode(SAMPLE_MAINST_ZIPCODE);

    assertEquals(3, actualByZipcodeList1.size());
    assertEquals(expectedByZipcodeList1, actualByZipcodeList1,  "by city and zipcode list mismatch");

    List<Address> expectedByZipcodeList2 = List.of(expectedAdrs5);

    List<Address> actualByZipcodeList2 = addressRepo.findAllByZipcode(SAMPLE_ROOSST_ZIPCODE);

    assertEquals(1, actualByZipcodeList2.size());
    assertEquals(expectedByZipcodeList2, actualByZipcodeList2,  "by city and zipcode list mismatch");
  }

  @Test
  @DisplayName("should get addresses having given City")
  public void verifyFindByCity_validArgs(){

    
    List<Address> expectedByCityAddressList = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3, expectedAdrs4, expectedAdrs5
      );

    List<Address> actualByCityAddressList = addressRepo.findAllByCity(CITY);

    assertEquals(5, actualByCityAddressList.size());
    assertEquals(expectedByCityAddressList, actualByCityAddressList, "by city list mismatch");
  }

  @Test
  @DisplayName("should get addresses having given State")
  public void verifyFindByState_validArgs(){

    
    List<Address> expectedByStateAddressList = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3, expectedAdrs4, expectedAdrs5
      );

    List<Address> actualByStateAddressList = addressRepo.findAllByState(STATE);

    assertEquals(5, actualByStateAddressList.size());
    assertEquals(expectedByStateAddressList, actualByStateAddressList, "by state list mismatch");
  }

  @Test
  @DisplayName("should get addresses with given street address")
  public void verifyFindByStreetAddressIn(){

    List<String> queryList = List.of(SAMPLE_MAINST_STREETADDRESS, SAMPLE_ROOSST_STREETADDRESS);

    List<Address> expectedByStreetAddressList = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3, expectedAdrs5
      );

    List<Address> actualByStreetAddressList = addressRepo.findAllByStreetAddressIn(queryList);

    assertEquals(4, actualByStreetAddressList.size());
    assertEquals(expectedByStreetAddressList, actualByStreetAddressList,  "by city and zipcode list mismatch");
  }

  @Test
  @DisplayName("should get addresses with given zipcode")
  public void verifyFindByZipcodeIn(){
    
    List<Integer> queryList = List.of(SAMPLE_MAINST_ZIPCODE, SAMPLE_ROOSST_ZIPCODE);

    List<Address> expectedByZipcodeList1 = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3, expectedAdrs5
      );

    List<Address> actualByZipcodeList1 = addressRepo.findAllByZipcodeIn(queryList);

    assertEquals(4, actualByZipcodeList1.size());
    assertEquals(expectedByZipcodeList1, actualByZipcodeList1,  "by city and zipcode list mismatch");

  }

  @Test
  @DisplayName("should get addresses having given City")
  public void verifyFindByCityIn(){

    
    List<String> queryList = List.of(CITY);

    
    List<Address> expectedByCityAddressList = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3, expectedAdrs4, expectedAdrs5
      );

    List<Address> actualByCityAddressList = addressRepo.findAllByCityIn(queryList);

    assertEquals(5, actualByCityAddressList.size());
    assertEquals(expectedByCityAddressList, actualByCityAddressList, "by city list mismatch");
  }

  @Test
  @DisplayName("should get addresses having given State")
  public void verifyFindByStateIn(){
    
    List<String> queryList = List.of(STATE);
    
    List<Address> expectedByStateAddressList = List.of(
      expectedAdrs1, expectedAdrs2, expectedAdrs3, expectedAdrs4, expectedAdrs5
      );

    List<Address> actualByStateAddressList = addressRepo.findAllByStateIn(queryList);

    assertEquals(5, actualByStateAddressList.size());
    assertEquals(expectedByStateAddressList, actualByStateAddressList, "by state list mismatch");
  }
}
