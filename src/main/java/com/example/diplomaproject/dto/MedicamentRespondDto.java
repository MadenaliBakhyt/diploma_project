package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.CategoryEntity;
import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.TagEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MedicamentRespondDto {
    private Long id;
    private String medName;
    private String description;
    private String country;
    private String producer;
    private Long price;
    private String imageUrl;
    private List<CategoryEntity> category;
    private List<TagRespondDto> tags;

    public MedicamentRespondDto(MedicamentEntity medicamentEntity){
        this.id=medicamentEntity.getId();
        this.medName= medicamentEntity.getMedName();
        this.description=medicamentEntity.getDescription();
        this.country=medicamentEntity.getCountry();
        this.producer=medicamentEntity.getProducer();
        this.price=medicamentEntity.getPrice();
        this.imageUrl=medicamentEntity.getImageUrl();
        this.category=medicamentEntity.getCategory();
        this.tags=medicamentEntity.getTags().stream().map(TagRespondDto::new).toList();
    }
}
