package com.example.diplomaproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "medicaments")
public class MedicamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "med_name")
    private String medName;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "producer")
    private String producer;

    @Column(name = "price")
    private Long price;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "category")
    @ElementCollection
    private List<String> category;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="med_tags",
            joinColumns = @JoinColumn(name = "med_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags;
}
