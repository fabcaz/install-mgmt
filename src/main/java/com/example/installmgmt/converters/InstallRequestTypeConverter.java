package com.example.installmgmt.converters;

import com.example.installmgmt.domain.InstallRequestType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class InstallRequestTypeConverter implements AttributeConverter<InstallRequestType, Integer>{

	@Override
	public Integer convertToDatabaseColumn(InstallRequestType attribute) {
    if (attribute == null) {
      return null;
    }
    switch(attribute){
      case DIY:
        return 1;
      case NODE:
        return 2;
      case CABLE_RUN:
        return 3;
      case NODE_AND_CABLERUN:
        return 4;
      default: throw new IllegalArgumentException("InstallRequestTypeConverter: [" + attribute + "] DNE");
    }
	}

	@Override
	public InstallRequestType convertToEntityAttribute(Integer dbData) {
    if (dbData == null) {
      return null;
    }
    switch(dbData){
      case 1:
        return InstallRequestType.DIY;
      case 2:
        return InstallRequestType.NODE;
      case 3:
        return InstallRequestType.CABLE_RUN;
      case 4:
        return InstallRequestType.NODE_AND_CABLERUN;
      default: throw new IllegalArgumentException("InstallRequestTypeConverter Id: [" + dbData + "] DNE");
    }
	}
  
}
