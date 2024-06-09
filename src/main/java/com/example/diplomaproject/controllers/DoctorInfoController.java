package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.DoctorInfoRequestDto;
import com.example.diplomaproject.exceptions.AppError;
import com.example.diplomaproject.services.DoctorInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/doctorInfo")
public class DoctorInfoController {
    private final DoctorInfoService doctorInfoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DoctorInfoRequestDto dto){
        return ResponseEntity.ok(doctorInfoService.create(dto));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        try {
            doctorInfoService.deleteById(id);
            return ResponseEntity.ok().body("DoctorInfo deleted successfully");
        }catch (Exception exception){
            return new ResponseEntity<>(new AppError(HttpStatus.REQUEST_TIMEOUT.value(), exception.getMessage()),HttpStatus.REQUEST_TIMEOUT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok().body(doctorInfoService.getAll());
        }catch (Exception exception){
            return new ResponseEntity<>(new AppError(HttpStatus.REQUEST_TIMEOUT.value(), exception.getMessage()),HttpStatus.REQUEST_TIMEOUT);
        }
    }

    @GetMapping("/getDoctorByInfoId/{id}")
    public ResponseEntity<?> getDoctorByInfoId(@PathVariable Integer id){
        try {
            return ResponseEntity.ok().body(doctorInfoService.getDoctorByInfoId(id));
        }catch (Exception exception){
            return new ResponseEntity<>(new AppError(HttpStatus.REQUEST_TIMEOUT.value(), exception.getMessage()),HttpStatus.REQUEST_TIMEOUT);
        }
    }

    @GetMapping("/getInfoByDoctorId/{id}")
    public ResponseEntity<?> getInfoByDoctorId(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(doctorInfoService.getInfoByDoctorId(id));
        }catch (Exception exception){
            return new ResponseEntity<>(new AppError(HttpStatus.REQUEST_TIMEOUT.value(), exception.getMessage()),HttpStatus.REQUEST_TIMEOUT);
        }
    }



}
