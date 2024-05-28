package com.example.diplomaproject.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrescriptionMapper {
    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

}
