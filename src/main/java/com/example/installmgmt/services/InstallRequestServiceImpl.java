package com.example.installmgmt.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.installmgmt.converters.dtoConverters.InstallRequest2InstallRequestDto;
import com.example.installmgmt.converters.dtoConverters.InstallRequestDto2InstallRequest;
import com.example.installmgmt.converters.dtoConverters.NewInstallRequestDto2InstallRequest;
import com.example.installmgmt.domain.Address;
import com.example.installmgmt.domain.InstallRequest;
import com.example.installmgmt.domain.InstallRequestAppointment;
import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.dtos.NewInstallRequestDto;
import com.example.installmgmt.exceptions.NotFoundException;
import com.example.installmgmt.repositories.AddressRepository;
import com.example.installmgmt.repositories.InstallRequestAppointmentRepository;
import com.example.installmgmt.repositories.InstallRequestRepository;
import com.example.installmgmt.repositories.InstallerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstallRequestServiceImpl implements InstallRequestService{

      private static final String REQUEST_TYPE = "requestType";
      private static final String REQUESTER_PERSON_ID = "requesterPersonId";
      private static final String REQUESTER_PERSON_FULLNAME = "requesterPersonFullName";
      private static final String REQUESTER_PERSON_FIRSTNAME = "requesterPersonFirstName";
      private static final String REQUESTER_PERSON_LASTNAME = "requesterPersonLastName";
      private static final String REQUESTER_PERSON_EMAIL = "requesterPersonEmail";
      private static final String REQUESTER_PERSON_PHONENUMBER = "requesterPersonPhoneNumber";
      private static final String REQUESTED_DATE = "requestedDate";
      private static final String COMPLETED_DATE = "completedDate";
  // will need the InstallRequestAppointmentRepository
  // since when creating new InstallRequest there will
  // not be an id for teh request (which is required 
  // by the schema) -> save request with no
  // InstallRequestAppointments then add the saved
  // request's id to each appointmentDto then convert 
  // appointmentDtos, set to saved request and save again 
  // (unidirectional relationship)
  
  @Autowired
  private final AddressRepository addressRepository;

  @Autowired
  private final InstallRequestAppointmentRepository installRequestAppointmentRepository;

  @Autowired
  private final InstallRequestRepository installRequestRepository;

  @Autowired
  private final InstallerRepository installerRepository;

  @Autowired
  private final InstallRequestDto2InstallRequest installRequestDto2InstallRequest;

  @Autowired
  private final InstallRequest2InstallRequestDto installRequest2InstallRequestDto;

  @Autowired
  private final NewInstallRequestDto2InstallRequest newInstallRequestDto2InstallRequest;

	@Override
	public List<InstallRequestDto> findAll() {
    return installRequestRepository.findAll()
      .stream()
      .map(installRequest2InstallRequestDto::convert)
      .collect(Collectors.toList());
  }

	@Override
	public InstallRequestDto findInstallRequestById(Integer id) {
		return installRequest2InstallRequestDto.convert(
        installRequestRepository.findById(id)
        .orElseThrow(NotFoundException::new)
        );
	}

	@Override
	public List<InstallRequestDto> findInstallRequestByX(String fieldName, String fieldValue) {

    List<InstallRequest> res = new ArrayList<>();

    switch (fieldName) {
      case REQUEST_TYPE:

          res = installRequestRepository.findByRequestType(fieldValue);
          break;

      case REQUESTER_PERSON_ID:

          try {
            Integer id = Integer.parseInt(fieldValue);
            res = installRequestRepository.findByRequesterPersonId(id);
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("InstallRequestId invalid");
          }
          break;

      case REQUESTER_PERSON_FULLNAME: //first AND last names
                                      //
          if (!fieldValue.contains(" ")) {
            throw new IllegalArgumentException("InstallRequest requesterPersonFullName does not contain space");
          }
          String[] names = fieldValue.split(" ");
          res = installRequestRepository.findByRequesterPersonFirstNameAndRequesterPersonLastName(names[0], names[1]);
          break;
      case REQUESTER_PERSON_FIRSTNAME:

          res = installRequestRepository.findByRequesterPersonFirstNameOrRequesterPersonLastName(fieldValue, "");
          break;
      case REQUESTER_PERSON_LASTNAME:

          res = installRequestRepository.findByRequesterPersonFirstNameOrRequesterPersonLastName("", fieldValue);
          break;
      case REQUESTER_PERSON_EMAIL:

          res = installRequestRepository.findByRequesterPersonEmail(fieldValue);
          break;
      case REQUESTER_PERSON_PHONENUMBER:

          res = installRequestRepository.findByRequesterPersonPhoneNumber(fieldValue);
          break;
      case REQUESTED_DATE:

          try {
            LocalDate requestedDate = LocalDate.parse(fieldValue);
            res = installRequestRepository.findByRequestedDate(requestedDate);
          } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("InstallRequest requestedDate string unparsable");
          }
          break;
      case COMPLETED_DATE:

          try {
            LocalDate compDate = LocalDate.parse(fieldValue);
            res = installRequestRepository.findByCompletedDate(compDate);
          } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("InstallRequest requestedDate string unparsable");
          }
          break;
      default:
        break;
    }
		return res 
            .stream()
            .map(installRequest2InstallRequestDto::convert)
            .collect(Collectors.toList());
	}

  @Transactional
	@Override
	public InstallRequestDto saveInstallRequest(NewInstallRequestDto dto) {


    if (dto == null) {
      throw new IllegalArgumentException("Arg cannot be null");
    }

    InstallRequest newRequest = newInstallRequestDto2InstallRequest.convert(dto);

    Address rqstAddress = findExistingAddressOrSave(newRequest.getAddress());
    newRequest.setAddress(rqstAddress);

    // appt entity requires InstallRequestId -> 
    // 1) save request w/out appts to get an id 
    // 2) set the request's id to the appts and save them 
    // 3) save requests with appts
    Set<InstallRequestAppointment> tempAppts = newRequest.getPreferedAppointmentTimes();
    newRequest.setPreferedAppointmentTimes(new HashSet<>(0));

    InstallRequest persistedRequest = installRequestRepository.save(newRequest);
    persistedRequest.setPreferedAppointmentTimes(tempAppts);

    saveApptAndsetToRequest(persistedRequest);

		return installRequest2InstallRequestDto.convert(
          installRequestRepository.save(newRequest)
        );
	}

  @Transactional
	@Override
	public InstallRequestDto saveInstallRequest(InstallRequestDto dto) {

    if (dto == null || dto.getId() == null) {
      throw new IllegalArgumentException("Expected existing dto of InstallRequest");
    }

    InstallRequest installRequest = installRequestDto2InstallRequest.convert(dto);

    Address rqstAddress = findExistingAddressOrSave(installRequest.getAddress());
    installRequest.setAddress(rqstAddress);

    saveApptAndsetToRequest(installRequest);

		return installRequest2InstallRequestDto.convert(
        installRequestRepository.save(installRequest)
      );
	}

  private Address findExistingAddressOrSave(Address adrs){

    return addressRepository.findAllByStreetAddressAndZipcode(
        adrs.getStreetAddress(),
        adrs.getZipcode()
        )
      .stream()
      .filter(a -> a.getAptUnitNumber().equals(adrs.getAptUnitNumber()))
      .findFirst()
      .orElse(addressRepository.save(adrs));
  }

  /**
   * To be used on an InstallRequest with an id
   * @param rqst
   */
  private void saveApptAndsetToRequest(InstallRequest rqst){
    if (rqst.getId() == null) {
      throw new IllegalArgumentException("Expected InstallRequest with id");
    }
    //not null checking since a request's appt set cannot be null
    if (rqst.getPreferedAppointmentTimes().size() == 0) {
      return;
    }

    rqst.getPreferedAppointmentTimes().forEach(appt -> appt.setInstallRequestId(rqst.getId()));

    // appt getters and setters guarentee start < end but should validate and simply filter out any invalid appt - enduser can edit to correct
    // TODO: (4.4.6) refactor repo to have a method returning a set
    List<InstallRequestAppointment> rqstApptsList =
      installRequestAppointmentRepository.saveAll(rqst.getPreferedAppointmentTimes());

    Set<InstallRequestAppointment> rqstAppts = new HashSet<>(rqstApptsList.size());
    rqstAppts.addAll(rqstApptsList);

    rqst.setPreferedAppointmentTimes(rqstAppts);


  }





}















