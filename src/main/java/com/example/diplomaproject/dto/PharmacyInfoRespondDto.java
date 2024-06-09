package com.example.diplomaproject.dto;


import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.PharmacyInfoEntity;
import com.example.diplomaproject.entities.PrescriptionEntity;
import com.example.diplomaproject.entities.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PharmacyInfoRespondDto {
    private Integer id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String city;
    private UserDto pharmacyId;

    private List<MedicamentRespondDto> medicamentEntities;

    public PharmacyInfoRespondDto(PharmacyInfoEntity pharmacyInfoEntity){
        this.id= pharmacyInfoEntity.getId();
        this.name= pharmacyInfoEntity.getName();
        this.address= pharmacyInfoEntity.getAddress();
        this.latitude= pharmacyInfoEntity.getLatitude();
        this.longitude= pharmacyInfoEntity.getLongitude();
        this.city= pharmacyInfoEntity.getCity();
        this.pharmacyId=new UserDto(pharmacyInfoEntity.getPharmacyId());
        this.medicamentEntities=pharmacyInfoEntity.getMedicamentEntities().stream().map(MedicamentRespondDto::new).toList();
    }

}
