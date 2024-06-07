package com.example.diplomaproject.entities;

import com.example.diplomaproject.entities.enums.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String token;

    @ManyToMany
    @JoinTable(
            name="order_meds",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "med_id")
    )
    private List<MedicamentEntity> medicamentEntities;

    @Column(name = "total")
    private Long total;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "prescription_id", nullable = false)
    private PrescriptionEntity prescription;
}
