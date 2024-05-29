package com.example.diplomaproject.controllers;

import com.example.diplomaproject.entities.enums.Roles;
import com.example.diplomaproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/getPatients")
    public ResponseEntity<?> getPatients(){
        return ResponseEntity.ok(userService.findUsersByRole(Roles.ROLE_USER));
    }
}
