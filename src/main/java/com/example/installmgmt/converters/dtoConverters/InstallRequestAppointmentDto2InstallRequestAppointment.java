package com.example.installmgmt.converters.dtoConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.example.installmgmt.domain.InstallRequestAppointment;
import com.example.installmgmt.dtos.InstallRequestAppointmentDto;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Service
public class InstallRequestAppointmentDto2InstallRequestAppointment implements Converter<InstallRequestAppointmentDto, InstallRequestAppointment> {

  @Synchronized
  @Nullable
	@Override
	public InstallRequestAppointment convert(InstallRequestAppointmentDto source) {

    if(source == null){
      return null;
    }

		return new InstallRequestAppointment(
      source.getId(),
      source.getInstallRequestId(),
      source.getSelected(),
      source.getAppointementStartTime(),
      source.getAppointementEndTime()
        );
	}
  
}
