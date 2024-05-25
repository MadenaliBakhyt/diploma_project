package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.RegistrationUserDto;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.entities.enums.Roles;
import com.example.diplomaproject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {
    private  UserRepository userRepository;
    private  RoleService roleService;

    private  PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByUsername(String username){
        return userRepository.findByUsername(username);

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity=findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(
                String.format("User '%s' not found",username)
        ));

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoles().stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName().name())).collect(Collectors.toList())
        );
    }

    public UserEntity createNewUser(RegistrationUserDto registrationUserDto){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(registrationUserDto.getUsername());
        userEntity.setIin(registrationUserDto.getIin());
        userEntity.setPhone_number(registrationUserDto.getPhone_number());
        userEntity.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        //Nado proverku sdelat typa susestvuetly takoy akkaunt yly net
        userEntity.setRoles(List.of(roleService.getRole(Roles.ROLE_USER)));
        return userRepository.save(userEntity);

    }
}
