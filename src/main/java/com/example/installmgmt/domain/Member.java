package com.example.installmgmt.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "MEMBER")
public class Member{

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
  @Column(name = "status", nullable=false)
  private String status;

  public String getMemberFullName(){
    return String.join(" ", this.person.getFirstName(), this.person.getLastName());
  }

  public String getMemberSlackId(){
    return this.person.getSlackId();
  }

  public String getMemberEmail(){
    return this.person.getEmail();
  }

  public String getMemberPhoneNumber(){
    return this.person.getPhoneNumber();
  }

}
