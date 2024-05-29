package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.OrderEntity;
import com.example.diplomaproject.entities.PrescriptionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderRespondDto {
    private Integer id;
    private List<MedicamentRespondDto> medicamentEntities;
    private Long total;
    private PrescriptionDto prescription;

    public OrderRespondDto(OrderEntity orderEntity){
        this.id=orderEntity.getId();
        this.medicamentEntities=orderEntity.getMedicamentEntities()
                .stream().map(MedicamentRespondDto::new).toList();
        this.total=orderEntity.getTotal();
        this.prescription=new PrescriptionDto(orderEntity.getPrescription());
    }
}
