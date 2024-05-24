package com.example.diplomaproject.entities;


import com.example.diplomaproject.entities.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Roles name;
}
