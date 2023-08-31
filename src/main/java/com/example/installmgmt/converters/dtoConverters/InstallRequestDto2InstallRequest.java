package com.example.installmgmt.converters.dtoConverters;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.installmgmt.domain.Address;
import com.example.installmgmt.domain.Hardware;
import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.InstallRequestAppointment;
import com.example.installmgmt.domain.InstallRequestType;
import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.domain.Node;
import com.example.installmgmt.domain.Person;
import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.services.InstallerService;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class InstallRequestDto2InstallRequest implements Converter<InstallRequestDto, InstallRequest>{

  private final InstallerService installerService;

  private final AddressDto2Address addressDto2Address;

  private final HardwareDto2Hardware hardwareDto2Hardware;

  private final InstallRequestAppointmentDto2InstallRequestAppointment appointmentConverter;

  private final NodeDto2Node nodeDto2Node;
  
  private final PersonDto2Person personDto2Person;

  @Synchronized
  @Nullable
  @Override
	public InstallRequest convert(InstallRequestDto source) {

    if (source == null 
        || source.getRequestType() == null 
        || InstallRequestType.fromString(source.getRequestType()) == null) {

      return null;
    }

    // convert nested dtos
    Address address = addressDto2Address.convert(source.getAddress());

    Set<InstallRequestAppointment> appointments = source
      .getPreferedAppointmentTimes()
      .stream()
      .map(appointmentConverter::convert)
      .collect(Collectors.toSet());

    Set<Node> nodeLos = source.getNodesLos().stream()
      .map(dto -> nodeDto2Node.convert(dto))
      .collect(Collectors.toSet());

    Person requester = personDto2Person.convert(
        source.getRequesterPerson()
        );

    Set<Integer> allParticipantIds = source.getAssistantInstallerSet()
        .stream()
        .map(dto -> dto.getInstallerId())
        .collect(Collectors.toSet());

    if(source.getLeadInstaller() != null){
      allParticipantIds.add(source.getLeadInstaller().getInstallerId());
    }
    Set<Installer> participants = fetchParticipants(allParticipantIds);

    Installer leadInstaller = participants.stream()
      .filter(installer ->
          installer.getId() == source.getLeadInstaller().getInstallerId())
      .findFirst()
      // no lead installer is a valid case but may want to handle this differently
      .orElse(null);
      //.orElseThrow(() -> {throw new RuntimeException("leadInstaller id should have been in participants set");});

    participants.remove(leadInstaller);

    Set<Hardware> hardwares = source.getHardwares().stream()
      .map(hardwareDto2Hardware::convert)
      .collect(Collectors.toSet());

    InstallRequest ir = InstallRequest.builder()
      .id(source.getId())
      .requestType(InstallRequestType.fromString(source.getRequestType()))
      .requesterPerson(requester)
      .address(address)
      .leadInstaller(leadInstaller)
      .requesterNotes(source.getRequesterNotes())
      .leadNotes(source.getLeadNotes())
      .floorNumber(source.getFloorNumber())
      .requestedDate(source.getRequestedDate())
      .claimedDate(source.getClaimedDate())
      .completedDate(source.getCompletedDate())
      .preferedAppointmentTimes(appointments)
      .nodesLosSet(nodeLos)
      .hardwareSet(hardwares)
      .assistantInstallerSet(participants)
      .build();

    return ir;

	}

  private Set<Installer> fetchParticipants(Set<Integer> installerIds){

    Set<Installer> s = new HashSet<>(installerIds.size());

    s.addAll(installerService.findInstallersById(installerIds));

    return s;
  }
  
}
