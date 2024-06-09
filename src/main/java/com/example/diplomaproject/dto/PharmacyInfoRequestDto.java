package com.example.diplomaproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class PharmacyInfoRequestDto {
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String city;
    private Long pharmacyId;

    private List<Long> medicamentEntities;
}
