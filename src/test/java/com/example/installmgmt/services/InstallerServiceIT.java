package com.example.installmgmt.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.installmgmt.converters.dtoConverters.Installer2InstallerDto;
import com.example.installmgmt.converters.dtoConverters.InstallerDto2Installer;
import com.example.installmgmt.converters.dtoConverters.Person2PersonDto;
import com.example.installmgmt.converters.dtoConverters.PersonDto2Person;
import com.example.installmgmt.domain.Person;
import com.example.installmgmt.dtos.InstallerDto;
import com.example.installmgmt.dtos.PersonDto;
import com.example.installmgmt.repositories.InstallerRepository;
import com.example.installmgmt.repositories.PersonRepository;
import com.example.installmgmt.resources.SampleObjects;

  @DataJpaTest
  @AutoConfigureTestDatabase(replace = Replace.NONE)
  public class InstallerServiceIT {

    @Autowired
    private InstallerRepository installerRepository;

    @Autowired
    private PersonRepository personRepository;

    private Person2PersonDto person2PersonDto = new Person2PersonDto();

    private Installer2InstallerDto installer2InstallerDto =
      new Installer2InstallerDto(person2PersonDto);

    private PersonDto2Person personDto2Person = new PersonDto2Person();

    private InstallerDto2Installer installerDto2Installer = 
      new InstallerDto2Installer(personDto2Person);


    private InstallerService installerService;

    @BeforeEach
    void setup(){

      installerService = new InstallerServiceImpl(
          installerRepository,
          personRepository,
          installer2InstallerDto,
          installerDto2Installer);
    }



    @Test
    @DisplayName("should save valid Installer object")
    public void shouldSaveValidInstallerObject(){


      InstallerDto installerDto = new InstallerDto();
      // dont set id since new entity
      //installerDto.setId(INSTALLER_JOHNV.getId());
      installerDto.setPerson(SampleObjects.JOHNV_COMMAND);
      installerDto.setRoleGivenDate(SampleObjects.INSTALLER_JOHNV.getRoleGivenDate());
      installerDto.setStatus(SampleObjects.INSTALLER_JOHNV.getStatus().installerStatusName());


      long installerCountBeforeSave = installerRepository.count();

      InstallerDto savedEntity = installerService.save(installerDto);

      long installerCountAfterSave = installerRepository.count();

      assertNotNull(savedEntity);
      assertNotEquals(installerDto.getId(), savedEntity.getId());
      assertEquals(installerDto.getPerson(), savedEntity.getPerson());

      assertEquals(installerCountBeforeSave, installerCountAfterSave - 1 );


    }

    @Test
    @DisplayName("should throw for invalid Installer object | invalid person")
    public void shouldThrowForInvalidInstallerObject(){

      Person john = SampleObjects.JOHNV;

      PersonDto invalidPerson = new PersonDto();
      //no id -> not persisted -> invalid
      //invalidPerson.setId(john.getId());
      invalidPerson.setFirstName(john.getFirstName());
      invalidPerson.setLastName(john.getLastName());
      invalidPerson.setSlackId(john.getSlackId());
      invalidPerson.setEmail(john.getEmail());
      invalidPerson.setPhoneNumber(john.getPhoneNumber());

      InstallerDto installerDto = new InstallerDto();
      // dont set id since new entity
      //installerDto.setId(INSTALLER_JOHNV.getId());
      installerDto.setPerson(invalidPerson);
      installerDto.setRoleGivenDate(SampleObjects.INSTALLER_JOHNV.getRoleGivenDate());
      installerDto.setStatus(SampleObjects.INSTALLER_JOHNV.getStatus().installerStatusName());

      assertThrows(IllegalStateException.class, () -> 
          installerService.save(installerDto)
          );
    }

    @Test
    @DisplayName("should throw for invalid Installer object | nonexistent person")
    public void shouldThrowForInvalidInstallerObject2(){

      Person john = SampleObjects.JOHNV;

      PersonDto invalidPerson = new PersonDto();
      //nonexistent id -> not persisted -> invalid
      invalidPerson.setId(9999);
      invalidPerson.setFirstName(john.getFirstName());
      invalidPerson.setLastName(john.getLastName());
      invalidPerson.setSlackId(john.getSlackId());
      invalidPerson.setEmail(john.getEmail());
      invalidPerson.setPhoneNumber(john.getPhoneNumber());

      InstallerDto installerDto = new InstallerDto();
      // dont set id since new entity
      //installerDto.setId(INSTALLER_JOHNV.getId());
      installerDto.setPerson(invalidPerson);
      installerDto.setRoleGivenDate(SampleObjects.INSTALLER_JOHNV.getRoleGivenDate());
      installerDto.setStatus(SampleObjects.INSTALLER_JOHNV.getStatus().installerStatusName());

      assertThrows(IllegalStateException.class, () -> 
          installerService.save(installerDto)
          );
    }
  }
