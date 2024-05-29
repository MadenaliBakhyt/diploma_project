package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.OrderRequestDto;
import com.example.diplomaproject.dto.QrDto;
import com.example.diplomaproject.services.OrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @GetMapping("/getQrToken/{orderId}")
    public ResponseEntity<?> createOrderToken(@PathVariable Integer orderId){
        return ResponseEntity.ok(orderService.createOrderToken(orderId));
    }

    @GetMapping("/scanQrCode")
    public ResponseEntity<?> scanQrCode(@RequestBody QrDto qrDto){
        return ResponseEntity.ok(orderService.scanQrCode(qrDto));
    }

    @PutMapping("/handleOrder/{orderId}")
    public ResponseEntity<?> handleOrder(@PathVariable Integer orderId){
        return ResponseEntity.ok(orderService.handleOrder(orderId));
    }
}
