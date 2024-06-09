package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.PharmacyInfoEntity;
import com.example.diplomaproject.entities.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Repository
public interface PharmacyInfoRepository extends JpaRepository<PharmacyInfoEntity, Integer> {
    List<PharmacyInfoEntity> findAllByMedicamentEntitiesIn(List<MedicamentEntity> medicamentEntities);

    PharmacyInfoEntity findByPharmacyId(UserEntity userEntity);
}
