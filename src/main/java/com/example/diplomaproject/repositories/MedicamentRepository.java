package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.MedicamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<MedicamentEntity,Long> {


}
