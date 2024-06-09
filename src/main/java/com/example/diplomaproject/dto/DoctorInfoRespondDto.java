package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.DoctorInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorInfoRespondDto {
    private Integer id;
    private String major;
    private String hospitalName;
    private UserDto doctorId;

    public DoctorInfoRespondDto(DoctorInfoEntity doctorInfo){
        this.id=doctorInfo.getId();
        this.major=doctorInfo.getMajor();
        this.hospitalName= doctorInfo.getHospitalName();
        this.doctorId=new UserDto(doctorInfo.getDoctorId());
    }
}
