package com.example.installmgmt.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.installmgmt.converters.dtoConverters.Node2NodeDto;
import com.example.installmgmt.converters.dtoConverters.NodeDto2Node;
import com.example.installmgmt.domain.Node;
import com.example.installmgmt.dtos.NodeDto;
import com.example.installmgmt.repositories.NodeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NodeServiceImpl implements NodeService {
  
  private final NodeRepository nodeRepository;

  private final Node2NodeDto node2NodeDto;

  private final NodeDto2Node nodeDto2Node;

  public NodeDto findById(Integer id){
    Optional<Node> found = nodeRepository.findById(id);

    return found.isPresent() ? node2NodeDto.convert(found.get()) : null;
  }

  public List<NodeDto> findByStatus(String status){
    List<Node> found = nodeRepository.findByStatus(status);
		return found.stream().map(node2NodeDto::convert).collect(Collectors.toList());
  }

  public NodeDto save(NodeDto dto){
    
    Node incoming = nodeDto2Node.convert(dto);
    Node saved = nodeRepository.save(incoming);

    return node2NodeDto.convert(saved);
  }

}

