package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import com.example.installmgmt.domain.InstallRequestAppointment;
import com.example.installmgmt.dtos.InstallRequestAppointmentDto;

import lombok.Synchronized;

public class InstallRequestAppointment2InstallRequestAppointmentDto implements Converter<InstallRequestAppointment, InstallRequestAppointmentDto> {

  @Synchronized
  @Nullable
	@Override
	public InstallRequestAppointmentDto convert(InstallRequestAppointment source) {

    if(source == null){
      return null;
    }

    InstallRequestAppointmentDto dto = new InstallRequestAppointmentDto();
      dto.setId(source.getId());
      dto.setInstallRequestId(source.getInstallRequestId());
      dto.setSelected(source.getSelected());
      dto.setAppointementStartTime(source.getAppointementStartTime());
      dto.setAppointementEndTime(source.getAppointementEndTime());

      return dto;
	}
  
}
