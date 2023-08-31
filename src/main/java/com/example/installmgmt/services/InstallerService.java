package com.example.installmgmt.services;

import java.util.Collection;
import java.util.List;

import com.example.installmgmt.domain.Installer;
import com.example.installmgmt.dtos.InstallerDto;

public interface InstallerService {

  Installer findInstallerById(Integer id);

  List<Installer> findInstallersById(Collection<Integer> id);
  
  InstallerDto findById(Integer id);

  List<InstallerDto> findAllById(Collection<Integer> id);

  InstallerDto save(InstallerDto dto);
}
