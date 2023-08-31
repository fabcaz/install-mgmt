// look at global note 23332579-7186-4c54-a43a-f2932a75b3ed 
package com.example.installmgmt.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "INSTALL_REQUEST_PREFERED_DATES")
public class InstallRequestAppointment{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable=false)
  private Integer id;

  @NotNull
  @Positive
  @Column(name = "install_request_id", nullable=false)
  private Integer installRequestId;

  @NotNull
  @Column(name = "selected", nullable=false)
  @Builder.Default
  private Boolean selected = false;

  //@FutureOrPresent
  @Column(name = "appointement_start_time", nullable=false)
  @Setter(AccessLevel.NONE)
  private LocalDateTime appointementStartTime;

  //@FutureOrPresent
  @Column(name = "appointement_end_time", nullable=false)
  @Setter(AccessLevel.NONE)
  private LocalDateTime appointementEndTime;

  public void setAppointementStartTime(LocalDateTime appointementStartTime){
    if(this.appointementEndTime != null 
        && this.appointementEndTime.isBefore(appointementStartTime)){
        log.debug("Cannot set appointementStartTime postdating appointementEndTime{}", "")  ;
        return;
    }
    this.appointementStartTime = appointementStartTime;
  }
  public void setAppointementEndTime(LocalDateTime appointementEndTime){
    if (this.appointementStartTime != null 
        && appointementStartTime.isAfter(appointementEndTime)) {
        log.debug("Cannot set appointementEndTime predating appointementStartTime{}", "")  ;
        return;
    }
    this.appointementEndTime = appointementEndTime;
  }

}
