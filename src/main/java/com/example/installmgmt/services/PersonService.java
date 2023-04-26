package com.example.installmgmt.services;

import java.util.List;

import com.example.installmgmt.dtos.PersonDto;

public interface PersonService {

  PersonDto findById(Integer id);
  List<PersonDto> findByName(String firstName, String lastName);
  List<PersonDto> findByEmail(String email);
  List<PersonDto> findByPhoneNumber(String phoneNumber);
  List<PersonDto> findBySlackId(String slackId);

  PersonDto save(PersonDto personCommand);
  
}
