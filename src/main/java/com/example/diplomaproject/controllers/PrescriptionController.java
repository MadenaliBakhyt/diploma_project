package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.PrescriptionRequest;
import com.example.diplomaproject.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PrescriptionRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(prescriptionService.create(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findTags/{id}")
    public ResponseEntity<?> findAllTagsById(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(prescriptionService.findAllTagsById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findAllPresByDoctor")
    public ResponseEntity<?> findAllPresByDoctor(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(prescriptionService.findAllPresByDoctorId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllPres")
    public ResponseEntity<?> getAllPres(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(prescriptionService.getAllPres());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findAllPresByPatient")
    public ResponseEntity<?> findAllPresByPatient(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(prescriptionService.findAllPresByPatientId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/activatePrescription/{presId}")
    public ResponseEntity<?> activatePrescription(@PathVariable Integer presId){
        return ResponseEntity.ok(prescriptionService.activatePrescription(presId));
    }

    @PutMapping("/blockPrescription/{presId}")
    public ResponseEntity<?> blockPrescription(@PathVariable Integer presId){
        return ResponseEntity.ok(prescriptionService.blockPrescription(presId));
    }

//    @GetMapping("/getByTag/{id}")
//    public ResponseEntity<?> getPresByTag(@PathVariable Integer id){
//        return ResponseEntity.ok(prescriptionService.getPresByTag(id));
//    }
}
