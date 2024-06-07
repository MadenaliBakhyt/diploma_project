package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.MedicamentRequest;
import com.example.diplomaproject.exceptions.AppError;
import com.example.diplomaproject.services.MedicamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/medicament")
public class MedicamentController {
    private final MedicamentService medicamentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody MedicamentRequest medicamentRequest){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(medicamentService.create(medicamentRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(medicamentService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllTagsById/{id}")
    public ResponseEntity<?> getAllTagsById(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(medicamentService.getAllTagsById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllMedsByCategory/{name}")
    public ResponseEntity<?> getAllMedsByCategory(@PathVariable String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(medicamentService.getByCategory(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(medicamentService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByTagId/{id}")
    public ResponseEntity<?> getByTagId(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(medicamentService.getAllByTagId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            medicamentService.deleteById(id);
            return ResponseEntity.ok().body("Medicament deleted successfully");
        }catch (Exception exception){
            return new ResponseEntity<>(new AppError(HttpStatus.REQUEST_TIMEOUT.value(), exception.getMessage()),HttpStatus.REQUEST_TIMEOUT);
        }

    }
}
