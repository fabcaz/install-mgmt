package com.example.installmgmt.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "INSTALL")
public class Install{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable=false)
  private Integer id;

  @Column(name = "install_request_id")
  private Integer installRequestId;

  @OneToOne
  @JoinColumn(name = "address_id", referencedColumnName = "id", nullable=false)
  private Address address;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "INSTALL_X_HARDWARE",
    joinColumns = {@JoinColumn( name = "install_id", referencedColumnName="id", nullable=false)},
    inverseJoinColumns = {@JoinColumn( name = "hardware_id", referencedColumnName="id", nullable=false)}
  )
  private Set<Hardware> hardwareSet;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
    name = "INSTALL_X_NODE",
    joinColumns = {@JoinColumn( name = "install_id", referencedColumnName="id", nullable=false)},
    inverseJoinColumns = {@JoinColumn( name = "node_id", referencedColumnName="id", nullable=false)}
  )
  private Node node;

  @Column(name = "status", nullable=false)
  private String status;

}
