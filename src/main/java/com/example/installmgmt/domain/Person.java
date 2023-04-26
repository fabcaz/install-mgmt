package com.example.installmgmt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "PERSON")
public class Person{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable=false)
  private Integer id;

  @NotBlank
  @Size(min = 2)
  @Size(max = 20)
  @Column(name = "first_name", nullable = false)
  String firstName;

  @NotBlank
  @Size(min = 2)
  @Size(max = 20)
  @Column(name = "last_name", nullable = false)
  String lastName;

  @Column(name = "slack_id")
  String slackId;

  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
  @Column(name = "email", nullable = false)
  String email;

  @Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")
  @Column(name = "phone_number")
  String phoneNumber;

}
