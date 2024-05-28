package com.example.diplomaproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrescriptionRequest {
    private Long patientId;
    private List<Integer> tags;
}
