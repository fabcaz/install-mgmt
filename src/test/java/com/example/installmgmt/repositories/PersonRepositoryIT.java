package com.example.installmgmt.repositories;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.installmgmt.domain.Person;
import com.example.installmgmt.resources.SampleObjects;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PersonRepositoryIT {

  @Autowired
  PersonRepository personRepo;

  @Test
  @DisplayName("sanity check on sample data")
  void verifyInitialData(){

    List<Person> actualSamplePersonList = personRepo.findAll();
    List<Person> expectedSamplePersonList = List.of(SampleObjects.JOHNV, SampleObjects.TOMJ, SampleObjects.PAULE, SampleObjects.JULIEA,
        SampleObjects.ANTONY);

    assertEquals(expectedSamplePersonList, actualSamplePersonList, "initial data not what is expected");

  }

  @Test
  @DisplayName("should get person by firstName")
  void verifyFindByFirstName(){

    List<Person> expectedByFirstName = List.of(SampleObjects.JOHNV);
    List<Person> actualByFirstName = personRepo.findByFirstName(SampleObjects.JOHNV.getFirstName());

    List<Person> actualByFirstNameDne = personRepo.findByFirstName("DNE");

    assertEquals(expectedByFirstName, actualByFirstName);
    assertTrue(actualByFirstNameDne.isEmpty());
  }

  @Test
  @DisplayName("should get person by firstName list")
  void verifyFindByFirstNameIn(){

    List<Person> expectedByFirstName = List.of(SampleObjects.JOHNV, SampleObjects.ANTONY);
    List<Person> actualByFirstName = personRepo.findByFirstNameIn(List.of(SampleObjects.JOHNV.getFirstName(), SampleObjects.ANTONY.getFirstName()));

    List<Person> expectedByFirstNameDne = List.of(SampleObjects.JOHNV);
    List<Person> actualByFirstNameDne = personRepo.findByFirstNameIn(List.of("DNE", SampleObjects.JOHNV.getFirstName()));

    assertEquals(expectedByFirstName, actualByFirstName);
    assertEquals(expectedByFirstNameDne, actualByFirstNameDne);
  }

  @Test
  @DisplayName("should get person by lastName")
  void verifyFindBylastName(){

    List<Person> expectedByLastName = List.of(SampleObjects.JULIEA);
    List<Person> actualByLastName = personRepo.findByLastName(SampleObjects.JULIEA.getLastName());

    List<Person> actualByLastNameDne = personRepo.findByLastName("DNE");

    assertEquals(expectedByLastName, actualByLastName);
    assertTrue(actualByLastNameDne.isEmpty());
  }

  @Test
  @DisplayName("should get person by lastName list")
  void verifyFindByLastNameIn(){

    List<Person> expectedByLastName = List.of(SampleObjects.JOHNV, SampleObjects.ANTONY);
    List<Person> actualByLastName = personRepo.findByLastNameIn(List.of(SampleObjects.JOHNV.getLastName(), SampleObjects.ANTONY.getLastName()));

    List<Person> expectedByLastNameDne = List.of(SampleObjects.JOHNV);
    List<Person> actualByLastNameDne = personRepo.findByLastNameIn(List.of("DNE", SampleObjects.JOHNV.getLastName()));

    assertEquals(expectedByLastName, actualByLastName);
    assertEquals(expectedByLastNameDne, actualByLastNameDne);
  }

  @Test
  @DisplayName("should get person by firstName and lastName")
  void verifyFindByFirstNameAndLastName(){

    List<Person> expectedByFisrtNameAndLastName = List.of(SampleObjects.TOMJ);
    List<Person> actualByFirstNameAndLastName = personRepo.findByFirstNameAndLastName(SampleObjects.TOMJ.getFirstName(), SampleObjects.TOMJ.getLastName());

    List<Person> actualByFisrtNameAndLastNameDne = personRepo.findByFirstNameAndLastName("Tom", "DNE");

    assertEquals(expectedByFisrtNameAndLastName, actualByFirstNameAndLastName);
    assertTrue(actualByFisrtNameAndLastNameDne.isEmpty());
  }

  @Test
  @DisplayName("should get person by slackId")
  void verifyFindBySlackId(){

    List<Person> expectedBySlack = List.of(SampleObjects.JULIEA);
    List<Person> actualBySlack = personRepo.findBySlackId(SampleObjects.JULIEA.getSlackId());

    List<Person> actualBySlackDne = personRepo.findBySlackId("DNE");

    assertEquals(expectedBySlack, actualBySlack);
    assertTrue(actualBySlackDne.isEmpty());
  }

  @Test
  @DisplayName("should get person by slackId list")
  void verifyFindBySlackIdIn(){

    List<Person> expectedBySlackId = List.of(SampleObjects.JOHNV, SampleObjects.ANTONY);
    List<Person> actualBySlackId = personRepo.findBySlackIdIn(List.of(SampleObjects.JOHNV.getSlackId(), SampleObjects.ANTONY.getSlackId()));

    List<Person> expectedBySlackIdDne = List.of(SampleObjects.JOHNV);
    List<Person> actualBySlackIdDne = personRepo.findBySlackIdIn(List.of("DNE", SampleObjects.JOHNV.getSlackId()));

    assertEquals(expectedBySlackId, actualBySlackId);
    assertEquals(expectedBySlackIdDne, actualBySlackIdDne);
  }

  @Test
  @DisplayName("should get person by email")
  void verifyFindByEmail(){

    List<Person> expectedByEmail = List.of(SampleObjects.JULIEA);
    List<Person> actualByEmail = personRepo.findByEmail(SampleObjects.JULIEA.getEmail());

    List<Person> actualByEmailDne = personRepo.findByEmail("DNE");

    assertEquals(expectedByEmail, actualByEmail);
    assertTrue(actualByEmailDne.isEmpty());
  }

  @Test
  @DisplayName("should get person by email list")
  void verifyFindByEmailIn(){

    List<Person> expectedByEmail = List.of(SampleObjects.JOHNV, SampleObjects.ANTONY);
    List<Person> actualByEmail = personRepo.findByEmailIn(List.of(SampleObjects.JOHNV.getEmail(), SampleObjects.ANTONY.getEmail()));

    List<Person> expectedByEmailDne = List.of(SampleObjects.JOHNV);
    List<Person> actualByEmailDne = personRepo.findByEmailIn(List.of("DNE", SampleObjects.JOHNV.getEmail()));

    assertEquals(expectedByEmail, actualByEmail);
    assertEquals(expectedByEmailDne, actualByEmailDne);
  }

  @Test
  @DisplayName("should get person by phoneNumber")
  void verifyFindByPhoneNumber(){

    List<Person> expectedByPhoneNumber = List.of(SampleObjects.JULIEA);
    List<Person> actualByPhoneNumber = personRepo.findByPhoneNumber(SampleObjects.JULIEA.getPhoneNumber());

    List<Person> actualByPhoneNumberDne = personRepo.findByPhoneNumber("DNE");

    assertEquals(expectedByPhoneNumber, actualByPhoneNumber);
    assertTrue(actualByPhoneNumberDne.isEmpty());
  }

  @Test
  @DisplayName("should get person by phoneNumber list")
  void verifyFindByPhoneNumberIn(){

    List<Person> expectedByPhoneNumber = List.of(SampleObjects.JOHNV, SampleObjects.ANTONY);
    List<Person> actualByPhoneNumber = personRepo.findByPhoneNumberIn(List.of(SampleObjects.JOHNV.getPhoneNumber(), SampleObjects.ANTONY.getPhoneNumber()));

    List<Person> expectedByPhoneNumberDne = List.of(SampleObjects.JOHNV);
    List<Person> actualByPhoneNumberDne = personRepo.findByPhoneNumberIn(List.of("DNE", SampleObjects.JOHNV.getPhoneNumber()));

    assertEquals(expectedByPhoneNumber, actualByPhoneNumber);
    assertEquals(expectedByPhoneNumberDne, actualByPhoneNumberDne);
  }

}
