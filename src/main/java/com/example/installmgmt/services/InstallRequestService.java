package com.example.installmgmt.services;

import java.util.List;

import com.example.installmgmt.dtos.InstallRequestDto;
import com.example.installmgmt.dtos.NewInstallRequestDto;

public interface InstallRequestService {

  
  List<InstallRequestDto> findAll();

  InstallRequestDto findInstallRequestById(Integer id);

   /** selects objects where the given class field has the given value.
   * @param fieldName
   * @param fieldValue
   * @return 
   */
  List<InstallRequestDto> findInstallRequestByX(String fieldName, String fieldValue);
  
  InstallRequestDto saveInstallRequest(NewInstallRequestDto dto);

  InstallRequestDto saveInstallRequest(InstallRequestDto dto);
   
}
