package com.example.diplomaproject.entities;


import com.example.diplomaproject.entities.enums.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "doctor_id", nullable = false)
    private UserEntity doctorId;
    @ManyToOne()
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patientId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PrescriptionStatus status;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "expired_date")
    private Date expiredDate;
    @ManyToMany
    @JoinTable(
            name="prescription_tags",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags = new ArrayList<>();
    @PrePersist
    public void onCreate() {
        createdDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(createdDate);
        c.add(Calendar.HOUR, 24*7);
        expiredDate=c.getTime();
        status = PrescriptionStatus.INACTIVE;
    }
}
