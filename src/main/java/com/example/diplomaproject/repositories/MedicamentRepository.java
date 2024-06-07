package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.CategoryEntity;
import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicamentRepository extends JpaRepository<MedicamentEntity,Long> {
    List<MedicamentEntity> findByIdIn(List<Long> id);

    List<MedicamentEntity> findAllByCategoryContains(Optional<CategoryEntity> categoryEntity);

}
