package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.dtos.InstallerDto;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class Installer2InstallerDto implements Converter<Installer, InstallerDto >{

  private final Person2PersonDto person2PersonDto;

  @Synchronized
  @Nullable
  @Override
  public InstallerDto convert(Installer source) {

    // an installer without a status should not exist
    if (source == null || source.getStatus() == null) {
      return null;
    }

    InstallerDto dto = new InstallerDto();

    dto.setId(source.getId());
    dto.setPerson(person2PersonDto.convert(source.getPerson()));
    dto.setRoleGivenDate(source.getRoleGivenDate());
    dto.setStatus(source.getStatus().installerStatusName());

    return dto;
  }
}
