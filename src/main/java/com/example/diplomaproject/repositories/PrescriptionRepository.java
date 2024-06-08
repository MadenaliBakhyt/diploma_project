package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.PrescriptionEntity;
import com.example.diplomaproject.entities.TagEntity;
import com.example.diplomaproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Integer> {
    List<PrescriptionEntity> findPrescriptionEntitiesByDoctorId(Optional<UserEntity> doctorId);
    List<PrescriptionEntity> findPrescriptionEntitiesByPatientId(Optional<UserEntity> patientId);

    List<PrescriptionEntity> findPrescriptionEntitiesByTagsContaining(Optional<TagEntity> tagId);




}
