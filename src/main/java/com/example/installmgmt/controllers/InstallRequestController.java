package com.example.installmgmt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.installmgmt.dtos.NewInstallRequestDto;
import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.services.InstallRequestService;

import lombok.RequiredArgsConstructor;


import java.util.List;

import static com.example.installmgmt.controllers.ControllerUris.INSTALLREQUEST_REQUESTMAPPING;
import static com.example.installmgmt.controllers.ControllerUris.INSTALLREQUEST_POST_SING_CREATE;
import static com.example.installmgmt.controllers.ControllerUris.INSTALLREQUEST_GET_MULT_FIND_BY_ID;
import static com.example.installmgmt.controllers.ControllerUris.INSTALLREQUEST_GET_SING_FIND_BY_ID;
import static com.example.installmgmt.controllers.ControllerUris.INSTALLREQUEST_POST_SING_UPDATE;





@RequiredArgsConstructor
@RestController
@RequestMapping(INSTALLREQUEST_REQUESTMAPPING)
public class InstallRequestController {
  
  //new and update handlers should be separate since create uses NewInstallRequestDto

  private final InstallRequestService installRequestService;


  @GetMapping(INSTALLREQUEST_GET_MULT_FIND_BY_ID)
  public List<InstallRequestDto> getInstallRequests(){
    return installRequestService.findAll();
  }

  @GetMapping(INSTALLREQUEST_GET_SING_FIND_BY_ID)
  public InstallRequestDto getInstallRequests(@PathVariable Integer installrequestid){
    return installRequestService.findInstallRequestById(installrequestid);
  }

  @PostMapping(INSTALLREQUEST_POST_SING_CREATE)
  public InstallRequestDto createNewInstallRequest(@RequestBody NewInstallRequestDto newDto){
    return installRequestService.saveInstallRequest(newDto);
  }

  @PostMapping(INSTALLREQUEST_POST_SING_UPDATE)
  public InstallRequestDto createNewInstallRequest(@RequestBody InstallRequestDto dto){
    return installRequestService.saveInstallRequest(dto);
  }


}
