package com.example.installmgmt.domain;

import jakarta.persistence.Entity;

@Entity
public class UserRoles {
  

  // create view and go from there
  /* Laur uses Strings for authorities but could also use int bitmask; see if
    the view itself can contain a bitmask based on data from other tables (ie
     whether person has Lead or asst or no row in INSTALLER_TABLE)
    */
}
