package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class DoctorInfoRequestDto {
    private String major;
    private String hospitalName;
    private Long doctorId;
}
