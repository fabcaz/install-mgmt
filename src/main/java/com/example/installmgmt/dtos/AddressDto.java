package com.example.installmgmt.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
  
  private Integer id;

  @NotBlank
  @Size(min = 1)
  @Size(max = 10)
  private String aptUnitNumber;
  
  @NotBlank
  @Size(min = 5)
  @Size(max = 40)
  private String streetAddress;
  
  @NotBlank
  @Size(min = 5)
  @Size(max = 40)
  private String city;

  @NotBlank
  @Size(min = 2)
  @Size(max = 2)
  private String state;

  @NotNull
  @Positive
  private Integer zipcode;
}
