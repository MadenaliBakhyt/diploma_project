package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentRepository extends JpaRepository<MedicamentEntity,Long> {
    List<MedicamentEntity> findByIdIn(List<Long> id);

    List<MedicamentEntity> findAllByCategoryContains(String category);

}
