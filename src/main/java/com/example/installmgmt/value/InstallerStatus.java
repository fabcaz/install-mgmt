package com.example.installmgmt.value;

public enum InstallerStatus {
  
  LEAD("LEAD"),
  ASSISTANT("ASSISTANT");

  private String name;

  InstallerStatus(String installerStatusName){this.name = installerStatusName;}

  public String installTypeName(){return this.name;}

  public static InstallerStatus fromString(String str) {
    for (InstallerStatus installerStatus : InstallerStatus.values()){
      if(installerStatus.name.equalsIgnoreCase(str)){
        return installerStatus;
      }
    }
    return null;
  }  
}
