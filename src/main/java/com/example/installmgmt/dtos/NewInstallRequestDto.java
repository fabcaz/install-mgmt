package com.example.installmgmt.dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
public class NewInstallRequestDto {
  
  @NotBlank
  @Pattern(regexp = "(DIY|NODE|CABLERUN|NODE_AND_CABLERUN)_INSTALL")
  private String requestType;
  
  @NotNull
  @Positive
  private Integer requesterPersonId;
  
  @NotNull
  private AddressDto address;

  private String requesterNotes;
  
  private Integer floorNumber;

  private List<InstallRequestAppointmentDto> preferedAppointmentTimes = new ArrayList<>(3);

  private List<Integer> nodesLos = new ArrayList<>(3);

}
