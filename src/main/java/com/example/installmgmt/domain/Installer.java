package com.example.installmgmt.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "INSTALLER")
public class Installer{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable=false)
  private Integer id;

  @Getter(AccessLevel.NONE)
  @OneToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id", nullable=false)
  private Person person;

  @NotNull
  @PastOrPresent
  @Column(name = "role_given_date", nullable = false)
  private LocalDate roleGivenDate;

  @NotBlank
  @Size(min = 1)
  @Size(max = 10)
  @Column(name = "type", nullable=false)
  private String status;

  public String getInstallerFullName(){
    return String.join(" ", this.person.getFirstName(), this.person.getLastName());
  }

  public String getInstallerSlackId(){
    return this.person.getSlackId();
  }

  public String getInstallerEmail(){
    return this.person.getEmail();
  }

  public String getInstallerPhoneNumber(){
    return this.person.getPhoneNumber();
  }

}
