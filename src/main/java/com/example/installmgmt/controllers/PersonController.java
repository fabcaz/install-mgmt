package com.example.installmgmt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.installmgmt.dtos.PersonDto;
import com.example.installmgmt.services.PersonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import static com.example.installmgmt.controllers.ControllerUris.PERSON_REQUESTMAPPING;
import static com.example.installmgmt.controllers.ControllerUris.PERSON_GET_SING_FIND_BY_ID;
import static com.example.installmgmt.controllers.ControllerUris.PERSON_POST_SING_CREATE_OR_UPDATE;

@RequiredArgsConstructor
@RestController
@RequestMapping(PERSON_REQUESTMAPPING)
public class PersonController {
  
  @Autowired
  PersonService personService;

  @GetMapping(PERSON_GET_SING_FIND_BY_ID)
  public PersonDto getPerson(@PathVariable Integer id){
    return personService.findById(id);
  }

  @PostMapping(PERSON_POST_SING_CREATE_OR_UPDATE)
  public PersonDto saveOrUpdatePerson(@Valid @RequestBody PersonDto personCommand){
    return personService.save(personCommand);
  }

}
