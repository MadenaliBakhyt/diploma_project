package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.CategoryEntity;
import com.example.diplomaproject.entities.TagEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class MedicamentRequest {
    private String medName;
    private String description;
    private String country;
    private String producer;
    private Long price;
    private String imageUrl;
    private List<Integer> category;
    private List<Integer> tags;
}
