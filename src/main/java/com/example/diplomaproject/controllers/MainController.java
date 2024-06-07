package com.example.diplomaproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;



@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MainController {



    @GetMapping("/unsecured")
    public String  unsecuredData(){
        return "Unsecured data";
    }


    @GetMapping("/secured")
    public String securedData(){
        return "Secured data";
    }


    @GetMapping("/doctor")
    public String doctorData(){
        return "Doctor data";
    }


    @GetMapping("/pharmacy")
    public String pharmacyData(){
        return "Pharmacy data";
    }


    @GetMapping("/admin")
    public String adminData(){
        return "Admin data";
    }


    @GetMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }


}
