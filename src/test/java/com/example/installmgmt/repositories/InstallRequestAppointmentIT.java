package com.example.installmgmt.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import com.example.installmgmt.domain.InstallRequestAppointment;
import com.example.installmgmt.resources.SampleObjects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InstallRequestAppointmentIT {
  
  @Autowired
  InstallRequestAppointmentRepository installRequestAppointmentRepo;

  @Test
  @DisplayName("sanity check on sample data and JPA mappings")
  void verifyInitialData(){

    List<InstallRequestAppointment> actualSampleList = installRequestAppointmentRepo.findAll();
    List<InstallRequestAppointment> expectedSampleList =
      List.of(SampleObjects.INSTALL_REQUEST_APPOINTMENT_1,
          SampleObjects.INSTALL_REQUEST_APPOINTMENT_2,
          SampleObjects.INSTALL_REQUEST_APPOINTMENT_3);

    assertEquals(expectedSampleList, actualSampleList, "initial data not what is expected");

  }

  //@Commit
  @Test
  @DisplayName("debug timestamp issue")
  void debugTimestampIssue(){

    LocalDateTime start = LocalDateTime.parse("2023-10-10T11:32:03");
    LocalDateTime end = LocalDateTime.parse("2023-10-10T23:34:05");

    InstallRequestAppointment apt = new InstallRequestAppointment(null,5,false,start,end);

    installRequestAppointmentRepo.save(apt);
    List<InstallRequestAppointment> aptList = installRequestAppointmentRepo.findAll();

    InstallRequestAppointment aptS = aptList.get(aptList.size()-1);

    assertEquals(aptS.getAppointementStartTime().getHour(), 11);
    assertEquals(aptS.getAppointementStartTime().getMinute(), 32);
    assertEquals(aptS.getAppointementStartTime().getSecond(), 03);

    assertEquals(aptS.getAppointementEndTime().getHour(), 23);
    assertEquals(aptS.getAppointementEndTime().getMinute(), 34);
    assertEquals(aptS.getAppointementEndTime().getSecond(), 05);


  }
}
