package com.example.installmgmt.converters.dtoConverters;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.dtos.HardwareDto;
import com.example.installmgmt.dtos.InstallParticipantDto;
import com.example.installmgmt.dtos.InstallRequestAppointmentDto;
import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.dtos.NodeDto;
import com.example.installmgmt.services.InstallerService;
import com.example.installmgmt.services.NodeService;
import com.example.installmgmt.services.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class InstallRequest2InstallRequestDto implements Converter<InstallRequest, InstallRequestDto>{

  //private final NodeService nodeService;

  //private final PersonService perService;

  private final Address2AddressDto address2AddressDto;

  private final Hardware2HardwareDto hardware2HardwareDto;

  private final InstallRequestAppointment2InstallRequestAppointmentDto appointmentConverter;

  private final Node2NodeDto node2NodeDto;

  private final Person2PersonDto person2PersonDto;

  
  @Synchronized
  @Nullable
  @Override
	public InstallRequestDto convert(InstallRequest source) {

    //installRequest with no type should not exist
    if (source == null || source.getRequestType() == null) {
      return null;
    }

    InstallRequestDto dto = new InstallRequestDto();

    
    dto.setId(source.getId());
    dto.setRequestType(source.getRequestType().installTypeName());
    dto.setRequesterPerson(
        person2PersonDto.convert(source.getRequesterPerson())
        );
    dto.setAddress(
        address2AddressDto.convert(source.getAddress())
        );
    if(source.getLeadInstaller() != null){
      dto.setLeadInstaller(
          installer2Participant(source.getLeadInstaller())
          );
    }
    dto.setRequesterNotes(source.getRequesterNotes());
    dto.setLeadNotes(source.getLeadNotes());
    dto.setFloorNumber(source.getFloorNumber());
    dto.setRequestedDate(source.getRequestedDate());
    dto.setClaimedDate(source.getClaimedDate());
    dto.setCompletedDate(source.getCompletedDate());

    // addAll the source sets
    Set<InstallRequestAppointmentDto> preferedAppointmentTimes = new HashSet<>(source.getPreferedAppointmentTimes().size());
    Set<NodeDto> nodesLos = new HashSet<>(source.getNodesLosSet().size());
    Set<HardwareDto> hardwares = new HashSet<>(source.getHardwareSet().size());
    Set<InstallParticipantDto> assistantInstallerSet = new HashSet<>(source.getAssistantInstallerSet().size());

    preferedAppointmentTimes.addAll(
        source.getPreferedAppointmentTimes().stream()
        .map(appointmentConverter::convert)
        .collect(Collectors.toList())

        );
    nodesLos.addAll(
        source.getNodesLosSet().stream()
        .map(node2NodeDto::convert)
        .collect(Collectors.toList())
        );

    hardwares.addAll(
        source.getHardwareSet().stream()
        .map(hardware2HardwareDto::convert)
        .collect(Collectors.toList())
        );

    assistantInstallerSet.addAll(
        source.getAssistantInstallerSet().stream()
        .map(this::installer2Participant)
        .collect(Collectors.toList())
        );
    dto.setPreferedAppointmentTimes(preferedAppointmentTimes); 
    dto.setNodesLos(nodesLos);
    dto.setHardwares(hardwares);
    dto.setAssistantInstallerSet(assistantInstallerSet);

    return dto;
	}
  
  private InstallParticipantDto installer2Participant(Installer installer){
    InstallParticipantDto dto = new InstallParticipantDto();

    dto.setInstallerId(installer.getId());
    dto.setName(installer.getInstallerFullName());
    dto.setSlackId(installer.getInstallerSlackId());

    return dto;
  }
}
