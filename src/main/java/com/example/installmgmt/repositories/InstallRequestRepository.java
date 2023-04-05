package com.example.installmgmt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.InstallRequest;

public interface InstallRequestRepository extends JpaRepository<InstallRequest, Integer>{


    public List<InstallRequest> findByRequestType(String name);

    public List<InstallRequest> findByRequesterPersonId(Integer personId);
    public List<InstallRequest> findByRequesterPersonFirstNameAndRequesterPersonLastName(String firstName, String lastName);
    public List<InstallRequest> findByRequesterPersonFirstNameOrRequesterPersonLastName(String firstName, String lastName);
    public List<InstallRequest> findByRequesterPersonEmail(String Email);
    public List<InstallRequest> findByRequesterPersonPhoneNumber(String phoneNumber);

    public List<InstallRequest> findByRequestedDate(LocalDate date);
    public List<InstallRequest> findByCompletedDate(LocalDate date);
}
