package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.RoleEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.entities.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByIin(String iin);

    List<UserEntity> findUserEntitiesByRolesContains(RoleEntity roles);


}
