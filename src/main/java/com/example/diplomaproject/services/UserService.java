package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.ImageDto;
import com.example.diplomaproject.dto.RegistrationUserDto;
import com.example.diplomaproject.dto.UserDto;
import com.example.diplomaproject.dto.UserRoleDto;
import com.example.diplomaproject.entities.RoleEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.entities.enums.Roles;
import com.example.diplomaproject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

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

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findByIin(String iin) {
        return userRepository.findByIin(iin);
    }

    public List<UserDto> findUsersByRole(Roles roles) {
        List<UserEntity> ls = userRepository.findUserEntitiesByRolesContains(roleService.getRole(roles));
        return ls.stream().map(UserDto::new).toList();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByIin(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));

        return new User(
                userEntity.getIin(),
                userEntity.getPassword(),
                userEntity.getRoles().stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName().name())).collect(Collectors.toList())
        );
    }

    public UserEntity createNewUser(RegistrationUserDto registrationUserDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationUserDto.getUsername());
        userEntity.setUserSecondname(registrationUserDto.getUserSecondName());
        userEntity.setUserThirdname(registrationUserDto.getUserThirdName());
        userEntity.setIin(registrationUserDto.getIin());
        userEntity.setPhone_number(registrationUserDto.getPhone_number());
        userEntity.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        //todo Nado proverku sdelat typa susestvuetly takoy akkaunt yly net
        userEntity.setRoles(List.of(roleService.getRole(Roles.ROLE_USER)));
        final String uri = "https://ui-avatars.com/api/?name=" + registrationUserDto.getUsername() + "+" + registrationUserDto.getUserSecondName();
        userEntity.setImageUrl(uri);
        return userRepository.save(userEntity);
    }

    public UserEntity createUserByRole(UserRoleDto userRoleDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRoleDto.getUsername());
        userEntity.setUserSecondname(userRoleDto.getUserSecondName());
        userEntity.setUserThirdname(userRoleDto.getUserThirdName());
        userEntity.setPassword(passwordEncoder.encode(userRoleDto.getPassword()));
        userEntity.setIin(userRoleDto.getIin());
        userEntity.setPhone_number(userRoleDto.getPhone_number());
        RoleEntity roleEntity = roleService.getRoleById(userRoleDto.getRole_id());
        userEntity.setRoles(List.of(roleEntity));
        final String uri = "https://ui-avatars.com/api/?name=" + userRoleDto.getUsername() + "+" + userRoleDto.getUserSecondName();
        userEntity.setImageUrl(uri);
        return userRepository.save(userEntity);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
