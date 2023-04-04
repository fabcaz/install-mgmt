package com.example.installmgmt.domain;

public enum InstallRequestType {

  DIY("DIY_INSTALL"),
  NODE("NODE_INSTALL"),
  CABLE_RUN("CABLERUN_INSTALL"),
  NODE_AND_CABLERUN("NODE_AND_CABLERUN_INSTALL");

  private String name;

  InstallRequestType(String installTypeName){this.name = installTypeName;}

  public String installTypeName(){return this.name;}

  public static InstallRequestType fromString(String str) {
    for (InstallRequestType rt : InstallRequestType.values()){
      if(rt.name.equalsIgnoreCase(str)){
        return rt;
      }
    }
    return null;
  }  

}
