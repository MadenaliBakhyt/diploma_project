package com.example.diplomaproject.services;

import com.example.diplomaproject.entities.RoleEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.entities.enums.Roles;
import com.example.diplomaproject.repositories.RoleRepository;
import com.example.diplomaproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService  {
    private final RoleRepository roleRepository;

    public RoleEntity getRole(Roles role){
        return roleRepository.findByName(role).orElseThrow(()->new RuntimeException("Role not found"));
    }

}
