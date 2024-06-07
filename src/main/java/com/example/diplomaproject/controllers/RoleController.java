package com.example.diplomaproject.controllers;

import com.example.diplomaproject.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(roleService.getAll());
    }
}
