package com.example.installmgmt.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.resources.SampleObjects;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InstallerRepositoryIT {
  

  @Autowired
  InstallerRepository installerRepo;

  @Test
  @DisplayName("sanity check on sample data")
  void verifyInitialData(){

    List<Installer> actualSampleInstallerList = installerRepo.findAll();
    List<Installer> expectedSampleInstallerList = List.of(SampleObjects.INSTALLER_JOHNV,
        SampleObjects.INSTALLER_TOMJ,
        SampleObjects.INSTALLER_JULIEA);

    assertEquals(expectedSampleInstallerList, actualSampleInstallerList, "initial data not what is expected");

  }

  @Test
  @DisplayName("should get installers registered within given date range")
  void verifyFindByRoleGivenDateBetween(){

    LocalDate startDate = LocalDate.parse("2014-01-01");
    LocalDate endDate = LocalDate.parse("2014-12-31");

    List<Installer> actualByRoleGivenDateBetweenList =
      installerRepo.findByRoleGivenDateBetween(startDate, endDate);

    List<Installer> expectedByRoleGivenDateBetweenList = 
      List.of(SampleObjects.INSTALLER_TOMJ);

    String expectedName = SampleObjects.TOMJ.getFirstName() + " " + SampleObjects.TOMJ.getLastName();
    String actualName = expectedByRoleGivenDateBetweenList.get(0).getInstallerFullName();

    assertEquals(expectedByRoleGivenDateBetweenList, actualByRoleGivenDateBetweenList);
    assertEquals(expectedName, actualName);

  }
}
