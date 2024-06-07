package com.example.diplomaproject.services;


import com.example.diplomaproject.dto.PrescriptionDto;
import com.example.diplomaproject.dto.PrescriptionRequest;
import com.example.diplomaproject.dto.TagRespondDto;
import com.example.diplomaproject.entities.PrescriptionEntity;
import com.example.diplomaproject.entities.TagEntity;
import com.example.diplomaproject.entities.enums.PrescriptionStatus;
import com.example.diplomaproject.repositories.PrescriptionRepository;
import com.example.diplomaproject.repositories.TagRepository;
import com.example.diplomaproject.repositories.UserRepository;
import com.example.diplomaproject.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public PrescriptionDto create(PrescriptionRequest request) {
        var patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new BadCredentialsException("Patient not found"));
        var doctor = userRepository.findByUsername(SecurityUtils.getCurrentName())
                .orElseThrow(() -> new BadCredentialsException("Doctor not found"));
        var tags = tagRepository.findByIdIn(request.getTags());
        var prescription = new PrescriptionEntity();
        prescription.setPatientId(patient);
        prescription.setDoctorId(doctor);
        prescription.setTags(tags);
        PrescriptionEntity prescriptionEntity=prescriptionRepository.save(prescription);
        return new PrescriptionDto(prescriptionEntity);
    }

    public PrescriptionDto activatePrescription(Integer id){
        PrescriptionEntity prescriptionEntity=prescriptionRepository.findById(id).get();
        if(!prescriptionEntity.getStatus().equals(PrescriptionStatus.INACTIVE)){
            throw new BadCredentialsException("Prescription can't be activated");
        }
        Date today=new Date();
        if(today.after(prescriptionEntity.getExpiredDate())){
            prescriptionEntity.setStatus(PrescriptionStatus.EXPIRED);
            prescriptionRepository.save(prescriptionEntity);
            throw new BadCredentialsException("Prescription is expired");
        }
        prescriptionEntity.setStatus(PrescriptionStatus.ACTIVE);
        prescriptionRepository.save(prescriptionEntity);
        return new PrescriptionDto(prescriptionEntity);
    }

    public PrescriptionDto blockPrescription(Integer id){
        PrescriptionEntity prescriptionEntity=prescriptionRepository.findById(id).get();
        prescriptionEntity.setStatus(PrescriptionStatus.BLOCKED);
        prescriptionRepository.save(prescriptionEntity);
        return new PrescriptionDto(prescriptionEntity);
    }

    public List<TagRespondDto> findAllTagsById(Integer id){
        return prescriptionRepository.findById(id).get().getTags()
                .stream().map(TagRespondDto::new).toList();
    }

    public List<PrescriptionDto> findAllPresByDoctorId(){
        List<PrescriptionEntity> ls=prescriptionRepository.findPrescriptionEntitiesByDoctorId(userRepository.findByUsername(SecurityUtils.getCurrentName()));
        return ls.stream().map(PrescriptionDto::new).toList();
    }

    public List<PrescriptionDto> findAllPresByPatientId(){
        List<PrescriptionEntity> ls=prescriptionRepository.findPrescriptionEntitiesByPatientId(userRepository.findByUsername(SecurityUtils.getCurrentName()));
        return ls.stream().map(PrescriptionDto::new).toList();
    }


}
