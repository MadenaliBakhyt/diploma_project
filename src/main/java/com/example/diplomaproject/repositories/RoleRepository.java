package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.RoleEntity;
import com.example.diplomaproject.entities.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{
    Optional<RoleEntity> findByName(Roles name);


}
