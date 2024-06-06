package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.JwtRequest;
import com.example.diplomaproject.dto.JwtResponse;
import com.example.diplomaproject.dto.RegistrationUserDto;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.exceptions.AppError;
import com.example.diplomaproject.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getIin(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getIin());
        Optional<UserEntity> userEntity = userService.findByIin(userDetails.getUsername());
        String token = jwtTokenUtils.generateToken(userEntity.get());
        return ResponseEntity.ok(new JwtResponse(userEntity.get().getId(),userEntity.get().getUsername(),userEntity.get().getUserSecondname(),userEntity.get().getUserThirdname(),userEntity.get().getIin(),userEntity.get().getPhone_number(),userEntity.get().getImageUrl(), userEntity.get().getRoles(),token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Passwords doesnt match"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByIin(registrationUserDto.getIin()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User is already exist"), HttpStatus.BAD_REQUEST);
        }
        userService.createNewUser(registrationUserDto);
        return createAuthToken(new JwtRequest(registrationUserDto.getIin(), registrationUserDto.getPassword()));
    }


}
