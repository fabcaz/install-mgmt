package com.example.installmgmt.converters;

import com.example.installmgmt.value.MemberStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MemberStatusConverter implements AttributeConverter<MemberStatus, Integer>{

	@Override
	public Integer convertToDatabaseColumn(MemberStatus attribute) {
    if (attribute == null) {
      return null;
    }
    switch(attribute){
      case ACTIVE:
        return 1;
      case INACTIVE:
        return 2;
      default: throw new IllegalArgumentException("MemberStatus: [" + attribute + "] DNE");
    }
	}

	@Override
	public MemberStatus convertToEntityAttribute(Integer dbData) {
    if (dbData == null) {
      return null;
    }
    switch(dbData){
      case 1:
        return MemberStatus.ACTIVE;
      case 2:
        return MemberStatus.ACTIVE;
      default: throw new IllegalArgumentException("MemberStatus Id: [" + dbData + "] DNE");
    }
	}
  
}
