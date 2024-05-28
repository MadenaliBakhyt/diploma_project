package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.TagEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TagRespondDto {
    private Integer id;
    private String tagName;

    public TagRespondDto(TagEntity tagEntity){
        this.id=tagEntity.getId();
        this.tagName=tagEntity.getTagName();
    }
}
