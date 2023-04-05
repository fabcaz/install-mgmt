package com.example.installmgmt.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.installmgmt.converters.InstallRequestTypeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "INSTALL_REQUEST")
public class InstallRequest{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable=false)
  private Integer id;

  @NotNull
  @Column(name = "install_request_type_id", nullable=false)
  @Convert( converter = InstallRequestTypeConverter.class )
  private InstallRequestType requestType;
  
  @NotNull
  @ManyToOne
  @JoinColumn(name = "requester_person_id", referencedColumnName = "id", nullable=false)
  private Person requesterPerson;
  
  @NotNull
  @OneToOne
  @JoinColumn(name = "address_id", referencedColumnName = "id", nullable=false)
  private Address address;

  @ManyToOne
  @JoinColumn(name = "lead_installer_id", referencedColumnName = "id", nullable=false)
  private Installer leadInstaller;

  @Column(name ="requester_notes", nullable = false)
  private String requesterNotes;
  
  @Column(name ="lead_notes", nullable = false)
  private String leadNotes;
  
  @Column(name ="floor_number", nullable = false)
  private Integer floorNumber;

  @NotNull
  @PastOrPresent
  @Column(name = "created_date", nullable=false)
  private LocalDate requestedDate;

  @PastOrPresent
  @Column(name = "claimed_date", nullable=false)
  private LocalDate claimedDate;

  @PastOrPresent
  @Column(name = "completed_date", nullable=false)
  @Setter(AccessLevel.NONE)
  private LocalDate completedDate;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "install_request_id", referencedColumnName = "id")
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  @Builder.Default
  private Set<InstallRequestAppointment> preferedAppointmentTimes = new HashSet<>(0);

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "INSTALL_REQUEST_LOS",
    joinColumns = {@JoinColumn(name = "install_request_id", referencedColumnName = "id", nullable=false)},
    inverseJoinColumns = {@JoinColumn (name ="node_id", referencedColumnName = "id", nullable=false)}
  )
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  @Builder.Default
  private Set<Node> nodesLosSet = new HashSet<>(0);

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "INSTALL_REQUEST_X_HARDWARE",
    joinColumns = {@JoinColumn( name = "install_request_id", referencedColumnName="id", nullable=false)},
    inverseJoinColumns = {@JoinColumn( name = "hardware_id", referencedColumnName="id", nullable=false)}
  )
  @Builder.Default
  private Set<Hardware> hardwareSet = new HashSet<>(0);

  /*
     An InstallRequest is unlikely to be fetched after it is completed, and the
     most likely reason to fetch one may be to change the participant list so
     eager load participants.
  */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "INSTALL_REQUEST_PARTICIPANT",
    joinColumns = {@JoinColumn( name = "install_request_id", referencedColumnName="id", nullable=false)},
    inverseJoinColumns = {@JoinColumn( name = "installer_id", referencedColumnName="id", nullable=false)}
  )
  @Builder.Default
  private Set<Installer> assistantInstallerSet = new HashSet<>(0);

  /**
   * Clears nodeLosSet and preferedAppointmentTimes since these are not needed for
   * completed {@link InstallRequest}
   * 
   * @param completedDate
   */
  public void setCompletedDate(LocalDate completedDate){
    log.debug("Marking InstallRequest[{}] as completed; clearing nodesLosSet and preferedAppointmentTimes", this.id);

    if(this.nodesLosSet != null && !this.nodesLosSet.isEmpty()){
      this.nodesLosSet = new HashSet<>(0);
    }
    if(this.preferedAppointmentTimes != null && !this.preferedAppointmentTimes.isEmpty()){
      this.preferedAppointmentTimes = new HashSet<>(0);
    }

    this.completedDate = completedDate;
  }

  /**
   * @return unmodifiable list to avoid adding elements after (@link InstallRequest} completion
   */
  public Set<InstallRequestAppointment> getPreferedAppointmentTimes(){
    if(this.preferedAppointmentTimes == null){
      return Set.of();
    }else{
      return Set.copyOf(this.preferedAppointmentTimes);
    }
  }

  /**
   * Only sets the attr if the given {@link InstallRequest} is not completed (i.e.
   * completedDate is null)
   * 
   * @param preferedAppointmentTimes
   */
  public void setPreferedAppointmentTimes(Set<InstallRequestAppointment> preferedAppointmentTimes){
    if (this.completedDate != null) {
      log.debug("Cannot setup appointment for a completed InstallRequest{}", "");
      return;      
    }
    //to avoid setting unmodifiable set
    Set<InstallRequestAppointment> newSet = new HashSet<>(preferedAppointmentTimes.size());
    newSet.addAll(preferedAppointmentTimes);
    this.preferedAppointmentTimes = newSet;
  }

  /**
   * @return unmodifiable set to avoid adding elements after (@link InstallRequest} completion
   */
  public Set<Node> getNodesLosSet(){
    if (this.nodesLosSet == null) {
      return Set.of();      
    }else{
      return Set.copyOf(this.nodesLosSet);
    }
  }
  /**
   * Only sets the attr if the given {@link InstallRequest} is not completed (i.e.
   * completedDate is null)
   * 
   * @param nodesLosSet
   */
  public void setNodesLosSet(Set<Node> nodesLosSet){
    if (this.completedDate != null) {
      log.debug("Cannot set los set for a completed InstallRequest{}", "");
      return;      
    }
    //to avoid setting unmodifiable set
    Set<Node> newSet = new HashSet<>(nodesLosSet.size());
    newSet.addAll(nodesLosSet);
    this.nodesLosSet = newSet;
  }

}
