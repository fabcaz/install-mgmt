package com.example.installmgmt.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.installmgmt.domain.Install;
import com.example.installmgmt.resources.SampleObjects;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InstallRepositoryIT {
  
  @Autowired
  InstallRepository installRepo;

  @Test
  @DisplayName("sanity check on sample data and JPA mappings")
  void verifyInitialData(){

    List<Install> actualSampleList = installRepo.findAll();
    List<Install> expectedSampleList = List.of(SampleObjects.INSTALL1, SampleObjects.INSTALL2, SampleObjects.INSTALL3, SampleObjects.INSTALL4);

    assertEquals(expectedSampleList, actualSampleList, "initial data not what is expected");

  }
}
