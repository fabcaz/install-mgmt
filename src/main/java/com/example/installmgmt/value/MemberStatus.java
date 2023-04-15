package com.example.installmgmt.value;

public enum MemberStatus {
  
  ACTIVE("ACTIVE"),
  INACTIVE("INACTIVE");

  private String name;
  
  MemberStatus(String installerStatusName){this.name = installerStatusName;}

  public String installTypeName(){return this.name;}

  public static MemberStatus fromString(String str) {
    for (MemberStatus MemberStatus : MemberStatus.values()){
      if(MemberStatus.name.equalsIgnoreCase(str)){
        return MemberStatus;
      }
    }
    return null;
  }  
}

