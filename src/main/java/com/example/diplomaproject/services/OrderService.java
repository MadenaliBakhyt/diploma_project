package com.example.diplomaproject.services;


import com.example.diplomaproject.dto.OrderRequestDto;
import com.example.diplomaproject.dto.OrderRespondDto;
import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.OrderEntity;
import com.example.diplomaproject.entities.PrescriptionEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.repositories.MedicamentRepository;
import com.example.diplomaproject.repositories.OrderRepository;
import com.example.diplomaproject.repositories.PrescriptionRepository;
import com.example.diplomaproject.repositories.UserRepository;
import com.example.diplomaproject.utils.SecurityUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicamentRepository medicamentRepository;
    private final MedicamentService medicamentService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    public OrderRespondDto create(OrderRequestDto orderRequestDto){
        OrderEntity orderEntity=new OrderEntity();
        List<MedicamentEntity> ls=medicamentRepository.findByIdIn(orderRequestDto.getMedicaments());
        Long total=medicamentService.getTotalPrice(ls);
        PrescriptionEntity prescriptionEntity=prescriptionRepository.findById(orderRequestDto.getPrescriptionId()).get();
        orderEntity.setMedicamentEntities(ls);
        orderEntity.setPrescription(prescriptionEntity);
        orderEntity.setTotal(total);
        OrderEntity order=orderRepository.save(orderEntity);
        return new OrderRespondDto(order);
    }

    public OrderRespondDto getOrderById(Integer id){
        OrderEntity order=orderRepository.findById(id).get();
        return new OrderRespondDto(order);
    }

    public OrderRespondDto getOrderByPresId(Integer id){
        OrderEntity order=orderRepository.findByPrescription(prescriptionRepository.findById(id).get());
        return new OrderRespondDto(order);
    }

    public List<OrderRespondDto> getOrdersByPatientId(){
        UserEntity patient=userRepository.findByUsername(SecurityUtils.getCurrentName()).get();
        List<OrderEntity> ls=orderRepository.findOrderEntitiesByPrescription_PatientId(patient);
        return ls.stream().map(OrderRespondDto::new).toList();
    }

    public List<OrderRespondDto> getOrdersByDoctorId(){
        UserEntity doctor=userRepository.findByUsername(SecurityUtils.getCurrentName()).get();
        List<OrderEntity> ls=orderRepository.findOrderEntitiesByPrescription_DoctorId(doctor);
        return ls.stream().map(OrderRespondDto::new).toList();
    }


}
