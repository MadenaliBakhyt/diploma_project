package com.example.diplomaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtRequest {
    private String iin;
    private String password;


}
