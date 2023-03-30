package com.example.installmgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.Hardware;

public interface HardwareRepository extends JpaRepository<Hardware, Integer>{

    public List<Hardware> findByName(String name);
    public List<Hardware> findByNameIn(List<String> names);
}
