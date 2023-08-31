package com.example.installmgmt.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class InstallerDto {

  private Integer id;

  private PersonDto person;

  @NotNull
  @PastOrPresent
  private LocalDate roleGivenDate;

  @NotNull
  @Pattern(regexp = "LEAD|ASSISTANT")
  private String status;

}

