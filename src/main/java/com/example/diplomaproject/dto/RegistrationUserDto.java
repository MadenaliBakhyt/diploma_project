package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class RegistrationUserDto {
    private Long id;
    private String username;
    private String userSecondName;
    private String userThirdName;
    private String password;
    private String confirmPassword;
    private String iin;
    private String phone_number;

}
