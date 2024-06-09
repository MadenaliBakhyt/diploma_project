package com.example.diplomaproject.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doctorInfo")
public class DoctorInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String major;
    private String hospitalName;
    @OneToOne
    @JoinColumn(name = "doctor_id")
    private UserEntity doctorId;

}
