package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.dtos.PersonDto;
import com.example.installmgmt.domain.Person;

import lombok.Synchronized;

@Component
public class Person2PersonDto implements Converter<Person, PersonDto>{


  @Synchronized
  @Nullable
  @Override
  public PersonDto convert(Person source) {
    if(source == null){
      return null;
    }
    PersonDto dto = new PersonDto();
    dto.setId(source.getId());
    dto.setFirstName(source.getFirstName());
    dto.setLastName(source.getLastName());
    dto.setSlackId(source.getSlackId());
    dto.setEmail(source.getEmail());
    dto.setPhoneNumber(source.getPhoneNumber());
    return dto;
	}
  
}

