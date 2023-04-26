package com.example.installmgmt.services;

import java.util.List;

import com.example.installmgmt.dtos.NodeDto;

public interface NodeService {
  
  NodeDto findById(Integer id);
  List<NodeDto> findByStatus(String status);
  NodeDto save(NodeDto dto);

}
