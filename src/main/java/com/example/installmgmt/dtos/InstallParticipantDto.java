package com.example.installmgmt.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class InstallParticipantDto {
  // view global_note 6b6ffbf5-7748-4ec7-b7ee-9dc022338e72
  

  private Integer installerId;
   
  //person attrs
  private String name;
  private String slackId;

}
