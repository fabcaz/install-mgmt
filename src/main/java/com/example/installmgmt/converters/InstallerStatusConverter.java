package com.example.installmgmt.converters;

import com.example.installmgmt.value.InstallerStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class InstallerStatusConverter implements AttributeConverter<InstallerStatus, Integer>{

	@Override
	public Integer convertToDatabaseColumn(InstallerStatus attribute) {
    if (attribute == null) {
      return null;
    }
    switch(attribute){
      case LEAD:
        return 1;
      case ASSISTANT:
        return 2;
      default: throw new IllegalArgumentException("InstallerStatus: [" + attribute + "] DNE");
    }
	}

	@Override
	public InstallerStatus convertToEntityAttribute(Integer dbData) {
    if (dbData == null) {
      return null;
    }
    switch(dbData){
      case 1:
        return InstallerStatus.LEAD;
      case 2:
        return InstallerStatus.ASSISTANT;
      default: throw new IllegalArgumentException("InstallerStatus Id: [" + dbData + "] DNE");
    }
	}
  
}
