package com.example.diplomaproject.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pharmacy_info")
public class PharmacyInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;
    private String address;

    private String imageUrl;

    private Double latitude;
    private Double longitude;
    private String city;

    @OneToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private UserEntity pharmacyId;

    @ManyToMany
    @JoinTable(
            name="pharmacyInfo_meds",
            joinColumns = @JoinColumn(name = "pharmacyInfo_id"),
            inverseJoinColumns = @JoinColumn(name = "med_id")
    )
    private List<MedicamentEntity> medicamentEntities;


}
