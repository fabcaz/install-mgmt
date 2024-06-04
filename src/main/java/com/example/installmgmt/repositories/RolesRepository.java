package com.example.installmgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.UserRoles;

public interface RolesRepository extends JpaRepository<UserRoles, Integer>{
  
}
