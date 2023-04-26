package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.dtos.PersonDto;
import com.example.installmgmt.domain.Person;

import lombok.Synchronized;

@Component
public class PersonDto2Person implements Converter<PersonDto, Person>{


  @Synchronized
  @Nullable
	@Override
	public Person convert(PersonDto source) {
    if(source == null){
      return null;
    }
    return new Person(
        source.getId(),
        source.getFirstName(),
        source.getLastName(),
        source.getSlackId(),
        source.getEmail(),
        source.getPhoneNumber()
        );
	}
  
}
