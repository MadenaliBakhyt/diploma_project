package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
    Optional<ImageEntity> findByName(String fileName);
}
