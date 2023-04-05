package com.example.installmgmt.domain;

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
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NODE")
public class Node{

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
    name = "NODE_X_HARDWARE",
    joinColumns = {@JoinColumn( name = "node_id", referencedColumnName="id", nullable=false)},
    inverseJoinColumns = {@JoinColumn( name = "hardware_id", referencedColumnName="id", nullable=false)}
  )
  private Set<Hardware> hardwareSet;


  @Column(name = "status", nullable=false)
  private String status;

}
