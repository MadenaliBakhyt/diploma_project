package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.CategoryEntity;
import com.example.diplomaproject.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    Optional<CategoryEntity> findByName(String name);

    List<CategoryEntity> findByIdIn(List<Integer> id);
}
