package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.UserRoleDto;
import com.example.diplomaproject.entities.enums.Roles;
import com.example.diplomaproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/getPatients")
    public ResponseEntity<?> getPatients(){
        return ResponseEntity.ok(userService.findUsersByRole(Roles.ROLE_USER));
    }
    @GetMapping("/getPharmacies")
    public ResponseEntity<?> getPharmacies(){
        return ResponseEntity.ok(userService.findUsersByRole(Roles.ROLE_PHARMACY));
    }
    @GetMapping("/getDoctors")
    public ResponseEntity<?> getDoctors(){
        return ResponseEntity.ok(userService.findUsersByRole(Roles.ROLE_DOCTOR));
    }
    @GetMapping("/getAdmins")
    public ResponseEntity<?> getAdmins(){
        return ResponseEntity.ok(userService.findUsersByRole(Roles.ROLE_ADMIN));
    }
    @GetMapping("/getCDSs")
    public ResponseEntity<?> getCDSs(){
        return ResponseEntity.ok(userService.findUsersByRole(Roles.ROLE_CDS));
    }

    @PostMapping("/createUserByRole")
    public ResponseEntity<?> createUserByRole(@RequestBody UserRoleDto userRoleDto){
        return ResponseEntity.ok(userService.createUserByRole(userRoleDto));
    }
}
