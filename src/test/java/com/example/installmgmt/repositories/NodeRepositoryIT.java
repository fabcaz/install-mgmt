package com.example.installmgmt.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.installmgmt.domain.Node;
import com.example.installmgmt.resources.SampleObjects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NodeRepositoryIT {

  @Autowired
  NodeRepository nodeRepo;

  @Test
  @DisplayName("sanity check on sample data")
  void verifyInitialData(){

    List<Node> actualSampleNodeList = nodeRepo.findAll();
    List<Node> expectedSampleNodeList = List.of(SampleObjects.NODE1, SampleObjects.NODE2, SampleObjects.NODE3);

    assertEquals(expectedSampleNodeList, actualSampleNodeList, "initial data not what is expected");

  }

  
}
