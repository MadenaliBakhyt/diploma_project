package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.DoctorInfoEntity;
import com.example.diplomaproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorInfoRepository extends JpaRepository<DoctorInfoEntity,Integer> {
    DoctorInfoEntity findDoctorInfoEntityByDoctorId(UserEntity userEntity);
}
