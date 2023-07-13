package com.example.installmgmt.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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
public class InstallRequestAppointmentDto {
  
  private Integer id;

  @NotNull
  @Positive
  private Integer installRequestId;

  @NotNull
  private Boolean selected = false;

  @FutureOrPresent
  private LocalDateTime appointementStartTime;

  @FutureOrPresent
  private LocalDateTime appointementEndTime;
}
