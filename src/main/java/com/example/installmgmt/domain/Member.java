package com.example.installmgmt.domain;

import java.time.LocalDate;

import com.example.installmgmt.converters.MemberStatusConverter;
import com.example.installmgmt.value.MemberStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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

  @NotNull
  @Positive
  @Convert( converter = MemberStatusConverter.class )
  @Column(name = "status", nullable=false)
  private MemberStatus status;

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
