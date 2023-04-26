package com.example.installmgmt.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.installmgmt.converters.dtoConverters.PersonDto2Person;
import com.example.installmgmt.converters.dtoConverters.Person2PersonDto;
import com.example.installmgmt.domain.Person;
import com.example.installmgmt.dtos.PersonDto;
import com.example.installmgmt.repositories.PersonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private final Person2PersonDto entity2Dto;

  private final PersonDto2Person dto2Entity;


	@Override
	public PersonDto findById(Integer id) {
    Optional<Person> found = personRepository.findById(id);
    return found.isPresent() ? entity2Dto.convert(found.get()) : null;
	}

	@Override
	public List<PersonDto> findByName(String firstName, String lastName) {
    List<Person> found;
    if(firstName != null && lastName != null){
      found = personRepository.findByFirstNameAndLastName(firstName, lastName);
    }else if(firstName != null){
      found = personRepository.findByFirstName(firstName);
    }else{
      found = personRepository.findByLastName(lastName);
    }

		return found.stream().map(entity2Dto::convert).collect(Collectors.toList());
	}

	@Override
	public List<PersonDto> findByEmail(String email) {
    List<Person> found = personRepository.findByEmail(email);
		return found.stream().map(entity2Dto::convert).collect(Collectors.toList());
	}

	@Override
	public List<PersonDto> findByPhoneNumber(String phoneNumber) {
    List<Person> found = personRepository.findByPhoneNumber(phoneNumber);
		return found.stream().map(entity2Dto::convert).collect(Collectors.toList());
	}

	@Override
	public List<PersonDto> findBySlackId(String slackId) {
    List<Person> found = personRepository.findBySlackId(slackId);
		return found.stream().map(entity2Dto::convert).collect(Collectors.toList());
	}

	@Override
	public PersonDto save(PersonDto personDto) {
    Person incoming = dto2Entity.convert(personDto);

    Person saved = personRepository.save(incoming);

    return entity2Dto.convert(saved);
	}
  
}
