package com.example.installmgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.Node;

public interface NodeRepository extends JpaRepository<Node, Integer>{

    public List<Node> findByStatus(String status);


}
