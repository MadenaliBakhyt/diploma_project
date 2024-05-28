package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.PrescriptionEntity;
import com.example.diplomaproject.entities.TagEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.entities.enums.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PrescriptionDto {
    private Integer id;

    private UserDto doctorId;

    private UserDto patientId;

    private PrescriptionStatus status;

    private Date createdDate;

    private Date expiredDate;
    private List<TagEntity> tags;

    public PrescriptionDto(PrescriptionEntity prescriptionEntity) {
        this.id = prescriptionEntity.getId();
        this.doctorId = new UserDto(prescriptionEntity.getDoctorId());
        this.patientId = new UserDto(prescriptionEntity.getPatientId());
        this.status = prescriptionEntity.getStatus();
        this.createdDate = prescriptionEntity.getCreatedDate();
        this.expiredDate = prescriptionEntity.getExpiredDate();
        this.tags=prescriptionEntity.getTags();
    }
}
