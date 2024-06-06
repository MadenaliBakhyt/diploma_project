package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.JwtRequest;
import com.example.diplomaproject.dto.RegistrationUserDto;
import com.example.diplomaproject.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.createAuthToken(authRequest);
    }


    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) throws IOException {
        return authService.createNewUser(registrationUserDto);
    }
}
