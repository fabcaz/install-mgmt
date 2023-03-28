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
import com.example.installmgmt.resources.SampleObjects;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AddressRepositoryIT {
  

  @Autowired
  AddressRepository addressRepo;

  //sanity check
  @Test
  @DisplayName("sample data sanity check")
  public void verifyInsert(){


    Address expectedInsertedAddress = new Address(null, "apt 555b", SampleObjects.SAMPLE_MAINST_STREETADDRESS, SampleObjects.CITY, SampleObjects.STATE, SampleObjects.SAMPLE_MAINST_ZIPCODE);
    Address expectedInsertedAddress2 = new Address(1, "apt 5555", SampleObjects.SAMPLE_MAINST_STREETADDRESS, SampleObjects.CITY, SampleObjects.STATE, SampleObjects.SAMPLE_MAINST_ZIPCODE);
    long initialCount = addressRepo.count();

    assertEquals(5, initialCount, "initial count mismatch");

    Address actualInsertedAddress = addressRepo.save(expectedInsertedAddress);
    Address actualInsertedAddress2 = addressRepo.save(expectedInsertedAddress2);
    List<Address> actualAllAddressList = addressRepo.findAll();

    long afterInsertCount = addressRepo.count();

    assertEquals(6, afterInsertCount, "initial count mismatch");

    assertEquals(6, actualAllAddressList.size(), "after insert count mismatch");

    assertNotNull(expectedInsertedAddress.getId(), "Address inserted by AddressRepo did not get an Id");
    assertEquals(actualInsertedAddress.getAptUnitNumber(), expectedInsertedAddress.getAptUnitNumber());
    assertEquals(actualInsertedAddress.getStreetAddress(),SampleObjects.SAMPLE_MAINST_STREETADDRESS);
    assertEquals(actualInsertedAddress.getCity(),SampleObjects.CITY);
    assertEquals(actualInsertedAddress.getState(),SampleObjects.STATE);
    assertEquals(actualInsertedAddress.getZipcode(), SampleObjects.SAMPLE_MAINST_ZIPCODE);

    assertEquals(actualAllAddressList.get(0), expectedInsertedAddress2, "address did not get updated");
  }


  @Test
  @DisplayName("should get addresses having given streetAddress and zipcode")
  public void verifyFindByStreetAddressAndZipcode_validArgs(){

    List<Address> expectedByCityAndZipcodeAddressList = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3
      );
    
    List<Address> actualByCityAndZipcodeAddressList  = addressRepo.findAllByStreetAddressAndZipcode(SampleObjects.SAMPLE_MAINST_STREETADDRESS, SampleObjects.SAMPLE_MAINST_ZIPCODE);
    assertEquals(3, actualByCityAndZipcodeAddressList.size());
    assertEquals(expectedByCityAndZipcodeAddressList, actualByCityAndZipcodeAddressList,  "by city and zipcode list mismatch");
  }

  @Test
  @DisplayName("should get addresses with given street address")
  public void verifyFindByStreetAddress(){
    List<Address> expectedByStreetAddressList = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3
      );

    List<Address> actualByStreetAddressList = addressRepo.findAllByStreetAddress(SampleObjects.SAMPLE_MAINST_STREETADDRESS);

    assertEquals(3, actualByStreetAddressList.size());
    assertEquals(expectedByStreetAddressList, actualByStreetAddressList,  "by city and zipcode list mismatch");
  }

  @Test
  @DisplayName("should get addresses with given zipcode")
  public void verifyFindByZipcode(){
    List<Address> expectedByZipcodeList1 = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3
      );

    List<Address> actualByZipcodeList1 = addressRepo.findAllByZipcode(SampleObjects.SAMPLE_MAINST_ZIPCODE);

    assertEquals(3, actualByZipcodeList1.size());
    assertEquals(expectedByZipcodeList1, actualByZipcodeList1,  "by city and zipcode list mismatch");

    List<Address> expectedByZipcodeList2 = List.of(SampleObjects.ADDRESS5);

    List<Address> actualByZipcodeList2 = addressRepo.findAllByZipcode(SampleObjects.SAMPLE_ROOSST_ZIPCODE);

    assertEquals(1, actualByZipcodeList2.size());
    assertEquals(expectedByZipcodeList2, actualByZipcodeList2,  "by city and zipcode list mismatch");
  }

  @Test
  @DisplayName("should get addresses having given City")
  public void verifyFindByCity(){

    
    List<Address> expectedByCityAddressList = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3, SampleObjects.ADDRESS4, SampleObjects.ADDRESS5
      );

    List<Address> actualByCityAddressList = addressRepo.findAllByCity(SampleObjects.CITY);

    assertEquals(5, actualByCityAddressList.size());
    assertEquals(expectedByCityAddressList, actualByCityAddressList, "by city list mismatch");
  }

  @Test
  @DisplayName("should get addresses having given State")
  public void verifyFindByState(){

    
    List<Address> expectedByStateAddressList = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3, SampleObjects.ADDRESS4, SampleObjects.ADDRESS5
      );

    List<Address> actualByStateAddressList = addressRepo.findAllByState(SampleObjects.STATE);

    assertEquals(5, actualByStateAddressList.size());
    assertEquals(expectedByStateAddressList, actualByStateAddressList, "by state list mismatch");
  }

  @Test
  @DisplayName("should get addresses with given street address")
  public void verifyFindByStreetAddressIn(){

    List<String> queryList = List.of(SampleObjects.SAMPLE_MAINST_STREETADDRESS, SampleObjects.SAMPLE_ROOSST_STREETADDRESS);

    List<Address> expectedByStreetAddressList = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3, SampleObjects.ADDRESS5
      );

    List<Address> actualByStreetAddressList = addressRepo.findAllByStreetAddressIn(queryList);

    assertEquals(4, actualByStreetAddressList.size());
    assertEquals(expectedByStreetAddressList, actualByStreetAddressList,  "by city and zipcode list mismatch");
  }

  @Test
  @DisplayName("should get addresses with given zipcode")
  public void verifyFindByZipcodeIn(){
    
    List<Integer> queryList = List.of(SampleObjects.SAMPLE_MAINST_ZIPCODE, SampleObjects.SAMPLE_ROOSST_ZIPCODE);

    List<Address> expectedByZipcodeList1 = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3, SampleObjects.ADDRESS5
      );

    List<Address> actualByZipcodeList1 = addressRepo.findAllByZipcodeIn(queryList);

    assertEquals(4, actualByZipcodeList1.size());
    assertEquals(expectedByZipcodeList1, actualByZipcodeList1,  "by city and zipcode list mismatch");

  }

  @Test
  @DisplayName("should get addresses having given City list")
  public void verifyFindByCityIn(){

    
    List<String> queryList = List.of(SampleObjects.CITY);

    
    List<Address> expectedByCityAddressList = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3, SampleObjects.ADDRESS4, SampleObjects.ADDRESS5
      );

    List<Address> actualByCityAddressList = addressRepo.findAllByCityIn(queryList);

    assertEquals(5, actualByCityAddressList.size());
    assertEquals(expectedByCityAddressList, actualByCityAddressList, "by city list mismatch");
  }

  @Test
  @DisplayName("should get addresses having given State")
  public void verifyFindByStateIn(){
    
    List<String> queryList = List.of(SampleObjects.STATE);
    
    List<Address> expectedByStateAddressList = List.of(
      SampleObjects.ADDRESS1, SampleObjects.ADDRESS2, SampleObjects.ADDRESS3, SampleObjects.ADDRESS4, SampleObjects.ADDRESS5
      );

    List<Address> actualByStateAddressList = addressRepo.findAllByStateIn(queryList);

    assertEquals(5, actualByStateAddressList.size());
    assertEquals(expectedByStateAddressList, actualByStateAddressList, "by state list mismatch");
  }
}
