package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.JwtRequest;
import com.example.diplomaproject.dto.JwtResponse;
import com.example.diplomaproject.dto.RegistrationUserDto;
import com.example.diplomaproject.dto.UserDto;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class AuthService{
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),"Incorrect login or password"),HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails=userService.loadUserByUsername(authRequest.getUsername());
        String token=jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"Passwords doesnt match"),HttpStatus.BAD_REQUEST);
        }
        if(userService.findByUsername(registrationUserDto.getUsername()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"User is already exist"),HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity=userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(userEntity.getId(),userEntity.getUsername(),userEntity.getEmail()));

    }


}
