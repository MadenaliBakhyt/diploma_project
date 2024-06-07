package com.example.diplomaproject.dto;

import lombok.Data;

@Data
public class UserRoleDto {
    private String username;
    private String userSecondName;
    private String userThirdName;
    private String password;
    private String confirmPassword;
    private String iin;
    private String phone_number;
    private Integer role_id;

}
