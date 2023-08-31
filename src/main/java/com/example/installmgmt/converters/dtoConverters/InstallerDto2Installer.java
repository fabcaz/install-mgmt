package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.dtos.InstallerDto;
import com.example.installmgmt.value.InstallerStatus;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class InstallerDto2Installer implements Converter<InstallerDto, Installer>{

  private final PersonDto2Person personDto2Person;

  @Synchronized
  @Nullable
  @Override
  public Installer convert(InstallerDto source) {

    // an installer without a status should not exist
    if (source == null 
        || source.getStatus() == null
        || InstallerStatus.fromString(source.getStatus()) == null) {
      return null;
    }

    return new Installer(
      source.getId(),
      personDto2Person.convert(source.getPerson()),
      source.getRoleGivenDate(),
      InstallerStatus.fromString(source.getStatus())
        );
  }
}
