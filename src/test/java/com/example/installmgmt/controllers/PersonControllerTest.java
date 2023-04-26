package com.example.installmgmt.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.installmgmt.dtos.PersonDto;
import com.example.installmgmt.resources.SampleObjects;
import com.example.installmgmt.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonControllerTest {

  public static final String GET_PERSON_URL = ControllerUris.PERSON_REQUESTMAPPING 
    + ControllerUris.PERSON_GET_SING_FIND_BY_ID;

  public static final String SAVE_PERSON_URL = ControllerUris.PERSON_REQUESTMAPPING 
    + ControllerUris.PERSON_POST_SING_CREATE_OR_UPDATE;

  ObjectMapper mapper = new ObjectMapper();

  @Mock
  PersonService personService;

  @InjectMocks
  PersonController personController;

  MockMvc mockMvc;

  @BeforeEach
  public void setup(){
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
  }

  @Test
  @DisplayName("should return command object with given id")
  public void shouldReturnCommandObjectWithGivenId() throws Exception{
    
    PersonDto validPc = SampleObjects.VALID_UNREGISTERED_USER_COMMAND;
    String expectedResponseBody = mapper.writeValueAsString(validPc);

    when(personService.findById(validPc.getId())).thenReturn(validPc);

    String response = mockMvc.perform(
        get(GET_PERSON_URL.replace("{id}", validPc.getId().toString()))
        .accept(MediaType.APPLICATION_JSON)
        )
      .andExpect(status().is2xxSuccessful())
      .andExpect(content().json(expectedResponseBody))
      .andReturn().getResponse().getContentAsString();
    System.out.println(response);
  }

  @Test
  @DisplayName("should validate request's valid command object")
  public void shouldValidateRequestCommandObject() throws Exception{
    
    PersonDto validPc = SampleObjects.VALID_UNREGISTERED_USER_COMMAND;
    String expectedResponseBody = mapper.writeValueAsString(validPc);

    when(personService.save(any())).thenReturn(validPc);

    String response = mockMvc.perform(
        post(SAVE_PERSON_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(validPc))
        )
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().json(expectedResponseBody))
        .andReturn().getResponse().getContentAsString();
  }

  @Test
  @DisplayName("should not validate request's invalid command object")
  public void shouldInvalidateRequestCommandObject() throws Exception{
    
    PersonDto invalidPc = SampleObjects.INVALID_UNREGISTERED_USER_COMMAND;
    String expectedResponseBody = mapper.writeValueAsString(invalidPc);

    when(personService.save(any())).thenReturn(invalidPc);

    String response = mockMvc.perform(
        post(SAVE_PERSON_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(invalidPc))
        )
        .andExpect(status().is4xxClientError())
        .andExpect(content().string(""))
        .andReturn().getResponse().getContentAsString();
        ;

    verify(personService, never()).save(any());
  }
}
