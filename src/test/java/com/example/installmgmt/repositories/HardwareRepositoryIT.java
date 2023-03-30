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

import com.example.installmgmt.domain.Hardware;
import com.example.installmgmt.resources.SampleObjects;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HardwareRepositoryIT {
  
  @Autowired
  HardwareRepository hardwareRepo;


  @Test
  @DisplayName("verify sample data")
  public void sanitiyCheckForSmallSample(){
    
    List<Hardware> expectedByNameList = List.of(SampleObjects.HARDWARE1, SampleObjects.HARDWARE2, SampleObjects.HARDWARE3, SampleObjects.HARDWARE4, SampleObjects.HARDWARE5);
    List<Hardware> actualByNameList = hardwareRepo.findAll();

    assertEquals(expectedByNameList, actualByNameList, "unexpected sample data");
  }

  @Test
  @DisplayName("should find by name")
  public void verifyFindByName(){


    List<Hardware> actualByNameList = hardwareRepo.findByName(SampleObjects.ANTENNA);
    List<Hardware> actualByNameList_empty = hardwareRepo.findByName("oops");

    assertEquals(0, actualByNameList_empty.size(), "query result for non-existant name should be empty list");

    assertEquals(1, actualByNameList.size());
    assertEquals(SampleObjects.HARDWARE1, actualByNameList.get(0));
  }

  @Test
  @DisplayName("should find by name list")
  public void verifyFindByNameIn(){

    List<String> queryList = List.of(SampleObjects.ANTENNA, SampleObjects.OUTDOORROUTER, SampleObjects.RJ45, "oops");

    List<Hardware> expectedByNameInList = List.of(SampleObjects.HARDWARE1, SampleObjects.HARDWARE2, SampleObjects.HARDWARE4);
    List<Hardware> actualByNameInList = hardwareRepo.findByNameIn(queryList);


    assertEquals(3, actualByNameInList.size());

    assertEquals(expectedByNameInList, actualByNameInList);
  }
}
