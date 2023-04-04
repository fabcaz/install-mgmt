package com.example.installmgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.InstallRequestAppointment;


public interface InstallRequestAppointmentRepository extends JpaRepository<InstallRequestAppointment, Integer>{
}
