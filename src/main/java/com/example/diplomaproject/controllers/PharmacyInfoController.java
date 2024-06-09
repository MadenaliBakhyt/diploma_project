package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.PharmacyInfoRequestDto;
import com.example.diplomaproject.dto.PharmacyInfoUpdateDto;
import com.example.diplomaproject.services.PharmacyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/pharmacyInfo")
public class PharmacyInfoController {
    private final PharmacyInfoService pharmacyInfoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PharmacyInfoRequestDto pharmacyInfoRequestDto){
        return ResponseEntity.ok(pharmacyInfoService.create(pharmacyInfoRequestDto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(pharmacyInfoService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(pharmacyInfoService.getAll());
    }

    @PutMapping("/addNewMedicament/{id}")
    public ResponseEntity<?> addNewMedicament(@RequestBody PharmacyInfoUpdateDto pharmacyInfoUpdateDto,@PathVariable Integer id){
        return ResponseEntity.ok(pharmacyInfoService.addNewMedicament(pharmacyInfoUpdateDto,id));
    }

    @PutMapping("/removeMedicament/{id}")
    public ResponseEntity<?> removeMedicament(@RequestBody PharmacyInfoUpdateDto pharmacyInfoUpdateDto,@PathVariable Integer id){
        return ResponseEntity.ok(pharmacyInfoService.removeMedicament(pharmacyInfoUpdateDto,id));
    }

    @PostMapping("/getAllByMedId")
    public ResponseEntity<?> getAllByMedId(@RequestBody PharmacyInfoUpdateDto pharmacyInfoUpdateDto){
        return ResponseEntity.ok(pharmacyInfoService.getAllByMedId(pharmacyInfoUpdateDto));
    }
}


