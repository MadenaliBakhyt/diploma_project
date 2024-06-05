package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageDto {
    private Integer id;
    private String name;
    private String type;
    public ImageDto(ImageEntity imageEntity){
        this.id=imageEntity.getId();
        this.name=imageEntity.getName();
        this.type= imageEntity.getType();
    }
}
