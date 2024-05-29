package com.example.diplomaproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private List<Long> medicaments;
    private Integer prescriptionId;
}
