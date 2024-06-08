package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.OrderEntity;
import com.example.diplomaproject.entities.PrescriptionEntity;
import com.example.diplomaproject.entities.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByPrescription(PrescriptionEntity prescription) ;

    List<OrderEntity> findOrderEntitiesByPrescription_DoctorId(UserEntity userEntity);

    List<OrderEntity> findOrderEntitiesByPrescription_PatientId(UserEntity userEntity);
    List<OrderEntity> findAllByMedicamentEntitiesContains(MedicamentEntity medicamentEntity);
}
