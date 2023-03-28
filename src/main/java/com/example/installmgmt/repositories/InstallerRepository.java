package com.example.installmgmt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.Installer;

public interface InstallerRepository extends JpaRepository<Installer, Integer>{

    public List<Installer> findByStatus(String status);
    public List<Installer> findByRoleGivenDateBetween(LocalDate startDate, LocalDate endDate);
}
