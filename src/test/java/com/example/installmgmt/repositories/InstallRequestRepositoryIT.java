package com.example.installmgmt.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.resources.SampleObjects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InstallRequestRepositoryIT {
  
  @Autowired
  InstallRequestRepository installRequestRepo;

  @Test
  @DisplayName("sanity check on sample data and JPA mappings")
  void verifyInitialData(){

    List<InstallRequest> actualSampleList = installRequestRepo.findAll();
    List<InstallRequest> expectedSampleList =
      List.of(
          SampleObjects.INSTALL_REQUEST_1,
          SampleObjects.INSTALL_REQUEST_2,
          SampleObjects.INSTALL_REQUEST_3,
          SampleObjects.INSTALL_REQUEST_4,
          SampleObjects.INSTALL_REQUEST_5
          );

    assertEquals(expectedSampleList, actualSampleList, "initial data not what is expected");

  }

  @Test
  @DisplayName("should clear nodes and appointments when setting completionDate")
  void setCompletionDateShouldClearLosAndAppointments(){

    Optional<InstallRequest> ir5Op = installRequestRepo.findById(5);
    assertTrue(ir5Op.isPresent());
    InstallRequest ir5 = installRequestRepo.findById(5).get();
    assertEquals(ir5, SampleObjects.INSTALL_REQUEST_5);

    ir5.setNodesLosSet(Set.of(SampleObjects.NODE1));

    ir5.setCompletedDate(LocalDate.now());
    assertTrue(ir5.getNodesLosSet().isEmpty());
    assertTrue(ir5.getPreferedAppointmentTimes().isEmpty());

    installRequestRepo.save(ir5);

    InstallRequest completeIr5 = installRequestRepo.findById(5).get();
    assertEquals(ir5, completeIr5);
  }

}
