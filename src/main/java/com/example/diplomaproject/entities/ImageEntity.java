package com.example.diplomaproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
@Builder
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    private String url;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;

}
