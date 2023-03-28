package com.example.installmgmt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

    public List<Member> findByStatus(String status);
    public List<Member> findByRoleGivenDateBetween(LocalDate startDate, LocalDate endDate);
}
