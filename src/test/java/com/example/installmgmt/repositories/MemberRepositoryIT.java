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

import com.example.installmgmt.domain.Member;
import com.example.installmgmt.resources.SampleObjects;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MemberRepositoryIT {
  

  @Autowired
  MemberRepository memberRepo;

  @Test
  @DisplayName("sanity check on sample data")
  void verifyInitialData(){

    List<Member> actualSampleMemberList = memberRepo.findAll();
    List<Member> expectedSampleMemberList = List.of(SampleObjects.MEMBER_JOHNV,
        SampleObjects.MEMBER_PAULE,
        SampleObjects.MEMBER_JULIEA,
        SampleObjects.MEMBER_ANTONY);

    assertEquals(expectedSampleMemberList, actualSampleMemberList, "initial data not what is expected");

  }

  @Test
  @DisplayName("should get members registered within given date range")
  void verifyFindByRoleGivenDateBetween(){

    LocalDate startDate = LocalDate.parse("2015-01-01");
    LocalDate endDate = LocalDate.parse("2015-12-31");

    List<Member> actualByRoleGivenDateBetweenList =
      memberRepo.findByRoleGivenDateBetween(startDate, endDate);
    
    List<Member> expectedByRoleGivenDateBetweenList =
      List.of(SampleObjects.MEMBER_JOHNV, SampleObjects.MEMBER_PAULE);

    assertEquals(expectedByRoleGivenDateBetweenList, actualByRoleGivenDateBetweenList);

  }
}
