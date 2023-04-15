package com.example.installmgmt.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.installmgmt.resources.SampleObjects;

public class InstallRequestAppointmentTest{

  @Test
  @DisplayName("shouldn't be able to set start postdating end")
  void shouldntSetStartAfterEnd(){

    LocalDateTime start = SampleObjects.INSTALL_REQUEST_APPOINTMENT_1.getAppointementStartTime();
    LocalDateTime end = SampleObjects.INSTALL_REQUEST_APPOINTMENT_1.getAppointementEndTime();
    LocalDateTime erroneousStart = end.plusDays(1);

    InstallRequestAppointment ira = new InstallRequestAppointment(1,1,false, start, end);

    ira.setAppointementStartTime(erroneousStart);

    assertEquals(start, ira.getAppointementStartTime());
    assertNotEquals(erroneousStart, ira.getAppointementStartTime());
  }

  @Test
  @DisplayName("should set valid start date")
  void shouldSetValidStart(){

    LocalDateTime start = SampleObjects.INSTALL_REQUEST_APPOINTMENT_1.getAppointementStartTime();
    LocalDateTime end = SampleObjects.INSTALL_REQUEST_APPOINTMENT_1.getAppointementEndTime();
    LocalDateTime newStart = start.minusDays(1);

    InstallRequestAppointment ira = new InstallRequestAppointment(1,1,false, start, end);

    ira.setAppointementStartTime(newStart);

    assertNotEquals(start, ira.getAppointementStartTime());
    assertEquals(newStart, ira.getAppointementStartTime());
  }

  @Test
  @DisplayName("shouldn't be able to set end predating start")
  void shouldntSetEndBeforeStart(){

    LocalDateTime start = SampleObjects.INSTALL_REQUEST_APPOINTMENT_1.getAppointementStartTime();
    LocalDateTime end = SampleObjects.INSTALL_REQUEST_APPOINTMENT_1.getAppointementEndTime();
    LocalDateTime erroneousEnd = start.minusDays(1);

    InstallRequestAppointment ira = new InstallRequestAppointment(1,1,false, start, end);

    ira.setAppointementEndTime(erroneousEnd);

    assertEquals(end, ira.getAppointementEndTime());
    assertNotEquals(erroneousEnd, ira.getAppointementEndTime());
  }

  @Test
  @DisplayName("should set valid end")
  void shouldSetValidEnd(){

    LocalDateTime start = SampleObjects.INSTALL_REQUEST_APPOINTMENT_1.getAppointementStartTime();
    LocalDateTime end = SampleObjects.INSTALL_REQUEST_APPOINTMENT_1.getAppointementEndTime();
    LocalDateTime newEnd = end.plusDays(1);

    InstallRequestAppointment ira = new InstallRequestAppointment(1,1,false, start, end);

    ira.setAppointementEndTime(newEnd);

    assertEquals(newEnd, ira.getAppointementEndTime());
    assertNotEquals(end, ira.getAppointementEndTime());
  }
}
