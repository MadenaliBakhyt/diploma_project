package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.RoleEntity;
import com.example.diplomaproject.entities.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private Long id;
    private String username;
    private String userSecondname;
    private String userThirdname;
    private String iin;
    private String phone_number;
    private String imageUrl;
    private Collection<RoleEntity> roles;

    private String token;

}
