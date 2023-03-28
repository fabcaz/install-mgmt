package com.example.installmgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

  public List<Person> findByFirstName(String firstName);
  public List<Person> findByFirstNameIn(List<String> firstNames);

  public List<Person> findByLastName(String lastName);
  public List<Person> findByLastNameIn(List<String> lastNames);

  public List<Person> findByFirstNameAndLastName(String firstName, String lastName);
  
  public List<Person> findBySlackId(String slackId);
  public List<Person> findBySlackIdIn(List<String> slackIds);

  public List<Person> findByEmail(String email);
  public List<Person> findByEmailIn(List<String> emails);

  public List<Person> findByPhoneNumber(String phoneNumber);
  public List<Person> findByPhoneNumberIn(List<String> phoneNumbers);
}
