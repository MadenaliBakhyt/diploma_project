package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.OrderRequestDto;
import com.example.diplomaproject.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderRequestDto orderRequestDto){
        return ResponseEntity.ok(orderService.create(orderRequestDto));
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/getOrderByPresId/{id}")
    public ResponseEntity<?> getOrderByPresId(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrderByPresId(id));
    }

    @GetMapping("/getOrdersByPatientId")
    public ResponseEntity<?> getOrdersByPatientId(){
        return ResponseEntity.ok(orderService.getOrdersByPatientId());
    }

    @GetMapping("/getOrdersByDoctorId")
    public ResponseEntity<?> getOrdersByDoctorId(){
        return ResponseEntity.ok(orderService.getOrdersByDoctorId());
    }
}
