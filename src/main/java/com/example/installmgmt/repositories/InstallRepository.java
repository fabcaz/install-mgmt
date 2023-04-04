package com.example.installmgmt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.Address;
import com.example.installmgmt.domain.Install;

public interface InstallRepository extends JpaRepository<Install, Integer>{

    public List<Install> findByStatus(String status);
    public List<Install> findByNodeId(Integer nodeId);
    public List<Install> findByAddress(Address address);

    
}
