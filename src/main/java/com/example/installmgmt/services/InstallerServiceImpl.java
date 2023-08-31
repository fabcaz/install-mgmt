package com.example.installmgmt.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.installmgmt.converters.dtoConverters.Installer2InstallerDto;
import com.example.installmgmt.converters.dtoConverters.InstallerDto2Installer;
import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.dtos.InstallerDto;
import com.example.installmgmt.exceptions.NotFoundException;
import com.example.installmgmt.repositories.InstallerRepository;
import com.example.installmgmt.repositories.PersonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InstallerServiceImpl implements InstallerService{

  private final InstallerRepository installerRepository;

  private final PersonRepository personRepository;

  private final Installer2InstallerDto installer2InstallerDto;

  private final InstallerDto2Installer installerDto2Installer;
  
  @Override
	public Installer findInstallerById(Integer id) {
		Optional<Installer> installer = installerRepository.findById(id);

    return installer.orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Installer> findInstallersById(Collection<Integer> ids) {
    List<Installer> s = new ArrayList<>(ids.size());

    s.addAll(installerRepository.findAllById(ids));

    return s;
	}

	@Override
	public InstallerDto findById(Integer id) {
		Optional<Installer> installer = installerRepository.findById(id);

    return installer2InstallerDto.convert(
        installer.orElseThrow(NotFoundException::new));
	}

	@Override
	public List<InstallerDto> findAllById(Collection<Integer> ids) {
    List<Installer> s = new ArrayList<>(ids.size());

    s.addAll(installerRepository.findAllById(ids));

    return s.stream()
      .map(installer2InstallerDto::convert)
      .collect(Collectors.toList());
	}

  @Override
  public InstallerDto save(InstallerDto dto){


    Installer installer = installerDto2Installer.convert(dto);

    if (   installer.getPerson() == null 
        || installer.getPerson().getId() == null
        || !personRepository.existsById(installer.getPerson().getId())) {
      
      throw new IllegalStateException("Installer must have an existing Person");
    }

    return installer2InstallerDto.convert(
        installerRepository.save(installer)
        );
  }
  
}

