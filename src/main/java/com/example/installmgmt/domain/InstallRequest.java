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
  @JoinColumn(name = "lead_installer_id", referencedColumnName = "id")
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

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
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
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
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
      this.nodesLosSet.clear();
    }
    if(this.preferedAppointmentTimes != null && !this.preferedAppointmentTimes.isEmpty()){
      this.preferedAppointmentTimes.clear();
    }

    this.completedDate = completedDate;
  }

  /**
   * @return unmodifiable list to avoid adding elements after (@link InstallRequest} completion
   */
  public Set<InstallRequestAppointment> getPreferedAppointmentTimes(){
    if(this.preferedAppointmentTimes == null || this.completedDate != null){
      return Set.of();
    }else{
      Set<InstallRequestAppointment> res = new HashSet<>(this.preferedAppointmentTimes.size());
      res.addAll(this.preferedAppointmentTimes);
      return res;
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

    this.preferedAppointmentTimes.clear();

    if (preferedAppointmentTimes != null && !preferedAppointmentTimes.isEmpty()) {
      this.preferedAppointmentTimes.addAll(preferedAppointmentTimes);
    }
  }

  /**
   * @return unmodifiable set to avoid adding elements after (@link InstallRequest} completion
   */
  public Set<Node> getNodesLosSet(){
    if (this.nodesLosSet == null || this.completedDate != null) {
      return Set.of();      
    }else{
      Set<Node> res = new HashSet<>(this.nodesLosSet.size());
      res.addAll(this.nodesLosSet);
      return res;
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

    this.nodesLosSet.clear();

    if (nodesLosSet != null && !nodesLosSet.isEmpty()) {
      this.nodesLosSet.addAll(nodesLosSet);
    }
  }

  public Set<Hardware> getHardwareSet(){
    if (this.hardwareSet == null) {
      return new HashSet<>(0);
    }else{
      Set<Hardware> res = new HashSet<>(this.hardwareSet.size());
      res.addAll(this.hardwareSet);
      return res;
    }
  }

  public void setHardwareSet(Set<Hardware> hardwareSet){

    this.hardwareSet.clear();

    if (hardwareSet != null && !hardwareSet.isEmpty()) {
      this.hardwareSet.addAll(hardwareSet);
    }
  }

  public Set<Installer> getAssistantInstallerSet(){
    if (this.assistantInstallerSet == null) {
      return new HashSet<>(0);
    }else{
      Set<Installer> res = new HashSet<>(this.assistantInstallerSet.size());
      res.addAll(this.assistantInstallerSet);
      return res;
    }
  }

  public void setAssistantInstallerSet(Set<Installer> assistantInstallerSet){

    this.assistantInstallerSet.clear();

    if (assistantInstallerSet != null && !assistantInstallerSet.isEmpty()) {
      this.assistantInstallerSet.addAll(assistantInstallerSet);
    }
  }


}
