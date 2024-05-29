package com.example.diplomaproject.services;


import com.example.diplomaproject.dto.OrderRequestDto;
import com.example.diplomaproject.dto.OrderRespondDto;
import com.example.diplomaproject.dto.QrDto;
import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.OrderEntity;
import com.example.diplomaproject.entities.PrescriptionEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.entities.enums.PrescriptionStatus;
import com.example.diplomaproject.repositories.MedicamentRepository;
import com.example.diplomaproject.repositories.OrderRepository;
import com.example.diplomaproject.repositories.PrescriptionRepository;
import com.example.diplomaproject.repositories.UserRepository;
import com.example.diplomaproject.utils.JwtTokenUtils;
import com.example.diplomaproject.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicamentRepository medicamentRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final MedicamentService medicamentService;

    private final JwtTokenUtils jwtTokenUtils;

    public OrderRespondDto create(OrderRequestDto orderRequestDto) {
        OrderEntity orderEntity = new OrderEntity();
        List<MedicamentEntity> ls = medicamentRepository.findByIdIn(orderRequestDto.getMedicaments());
        Long total = medicamentService.getTotalPrice(ls);
        PrescriptionEntity prescriptionEntity = prescriptionRepository.findById(orderRequestDto.getPrescriptionId()).get();
        orderEntity.setMedicamentEntities(ls);
        orderEntity.setPrescription(prescriptionEntity);
        orderEntity.setTotal(total);
        OrderEntity order = orderRepository.save(orderEntity);
        return new OrderRespondDto(order);
    }

    public OrderRespondDto getOrderById(Integer id) {
        OrderEntity order = orderRepository.findById(id).get();
        return new OrderRespondDto(order);
    }

    public OrderRespondDto getOrderByPresId(Integer id) {
        OrderEntity order = orderRepository.findByPrescription(prescriptionRepository.findById(id).get());
        return new OrderRespondDto(order);
    }

    public List<OrderRespondDto> getOrdersByPatientId() {
        UserEntity patient = userRepository.findByUsername(SecurityUtils.getCurrentName()).get();
        List<OrderEntity> ls = orderRepository.findOrderEntitiesByPrescription_PatientId(patient);
        return ls.stream().map(OrderRespondDto::new).toList();
    }

    public List<OrderRespondDto> getOrdersByDoctorId() {
        UserEntity doctor = userRepository.findByUsername(SecurityUtils.getCurrentName()).get();
        List<OrderEntity> ls = orderRepository.findOrderEntitiesByPrescription_DoctorId(doctor);
        return ls.stream().map(OrderRespondDto::new).toList();
    }

    public QrDto createOrderToken(Integer orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadCredentialsException("Order not found"));
        return new QrDto(jwtTokenUtils.generateOrderToken(order));
    }

    public OrderRespondDto scanQrCode(QrDto qrDto) {
        Integer orderId = jwtTokenUtils.getOrderId(qrDto.getToken());

        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadCredentialsException("Order not found"));

        return new OrderRespondDto(order);
    }

    public Boolean handleOrder(Integer orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadCredentialsException("Order not found"));

        if (order.getPrescription().getStatus().equals(PrescriptionStatus.ACTIVE)) {
            order.getPrescription().setStatus(PrescriptionStatus.HANDED);
            orderRepository.save(order);
        } else {
            throw new BadCredentialsException("Prescription is not active");
        }
        return true;
    }

}
