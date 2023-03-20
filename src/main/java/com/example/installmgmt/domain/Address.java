package com.example.installmgmt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ADDRESS")
public class Address {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable=false)
  private Integer id;

  @NotBlank
  @Size(min = 1)
  @Size(max = 10)
  @Column(name = "apt_unit_number")
  private String aptUnitNumber;
  
  @NotBlank
  @Size(min = 5)
  @Size(max = 40)
  @Column(name = "street_address", nullable=false)
  private String streetAddress;
  
  @NotBlank
  @Size(min = 5)
  @Size(max = 40)
  @Column(name = "city", nullable=false)
  private String city;

  @NotBlank
  @Size(min = 2)
  @Size(max = 2)
  @Column(name = "state", nullable=false)
  private String state;

  @NotNull
  @Positive
  @Column(name = "zipcode", nullable=false)
  private Integer zipcode;
}
