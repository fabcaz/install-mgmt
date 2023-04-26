package com.example.installmgmt.converters.dtoConverters;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.example.installmgmt.domain.Address;
import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.InstallRequestAppointment;
import com.example.installmgmt.domain.InstallRequestType;
import com.example.installmgmt.domain.Node;
import com.example.installmgmt.domain.Person;
import com.example.installmgmt.dtos.NewInstallRequestDto;
import com.example.installmgmt.services.NodeService;
import com.example.installmgmt.services.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Service
public class NewInstallRequestDto2InstallRequest implements Converter<NewInstallRequestDto, InstallRequest> {

  private final PersonService perService;

  private final NodeService nodeService;

  private final PersonDto2Person personDto2Person;

  private final AddressDto2Address addressDto2Address;

  private final InstallRequestAppointmentDto2InstallRequestAppointment appointmentConverter;

  private final NodeDto2Node nodeDto2Node;

  @Synchronized
  @Nullable
	@Override
	public InstallRequest convert(NewInstallRequestDto source) {

    if(source == null){
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
      .map(id -> nodeDto2Node.convert(nodeService.findById(id)))
      .collect(Collectors.toSet());

    Person requester = personDto2Person.convert(
        perService.findById(source.getRequesterPersonId())
        );

    //added for clarity:
    //.XXX(null) calls set attrs that are not present in NewInstallRequestDto
    InstallRequest ir = InstallRequest.builder()
      //.id(null)
      .requestType(InstallRequestType.fromString(source.getRequestType()))
      .requesterPerson(requester)
      .address(address)
      //.leadInstaller(null)
      .requesterNotes(source.getRequesterNotes())
      //.leadNotes(null)
      .floorNumber(source.getFloorNumber())
      .requestedDate(LocalDate.now())
      //.claimedDate(null)
      //.completedDate(null)
      .preferedAppointmentTimes(appointments)
      .nodesLosSet(nodeLos)
      //.hardwareSet(null)
      //.assistantInstallerSet(null)
      .build();

    return ir;

	}
  
}
