package com.example.installmgmt.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.installmgmt.domain.InstallRequestType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class InstallRequestDto {

  private Integer id;

  @NotNull
  @Pattern(regexp = "(DIY|NODE|CABLERUN|NODE_AND_CABLERUN)_INSTALL")
  private String requestType;
  
  @NotNull
  private PersonDto requesterPerson;
  
  @NotNull
  private AddressDto address;

  private InstallParticipantDto leadInstaller;

  private String requesterNotes;
  
  private String leadNotes;
  
  private Integer floorNumber;

  @NotNull
  @PastOrPresent
  private LocalDate requestedDate;

  @PastOrPresent
  private LocalDate claimedDate;

  @PastOrPresent
  private LocalDate completedDate;

  private Set<InstallRequestAppointmentDto> preferedAppointmentTimes = new HashSet<>(0);

  private Set<NodeDto> nodesLos = new HashSet<>(0);

  private Set<HardwareDto> hardwares = new HashSet<>(0);

  private Set<InstallParticipantDto> assistantInstallerSet = new HashSet<>(0);
}
