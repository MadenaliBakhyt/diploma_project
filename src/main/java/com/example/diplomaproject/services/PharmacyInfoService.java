package com.example.diplomaproject.services;


import com.example.diplomaproject.dto.PharmacyInfoRequestDto;
import com.example.diplomaproject.dto.PharmacyInfoRespondDto;
import com.example.diplomaproject.dto.PharmacyInfoUpdateDto;
import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.PharmacyInfoEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.repositories.MedicamentRepository;
import com.example.diplomaproject.repositories.PharmacyInfoRepository;
import com.example.diplomaproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PharmacyInfoService {
    private final PharmacyInfoRepository pharmacyInfoRepository;
    private final UserRepository userRepository;
    private final MedicamentRepository medicamentRepository;


    public PharmacyInfoRespondDto create(PharmacyInfoRequestDto pharmacyInfoRequestDto){
        PharmacyInfoEntity pharmacyInfo=new PharmacyInfoEntity();
        pharmacyInfo.setName(pharmacyInfoRequestDto.getName());
        pharmacyInfo.setAddress(pharmacyInfoRequestDto.getAddress());
        pharmacyInfo.setLatitude(pharmacyInfoRequestDto.getLatitude());
        pharmacyInfo.setLongitude(pharmacyInfoRequestDto.getLongitude());
        pharmacyInfo.setCity(pharmacyInfoRequestDto.getCity());
        UserEntity userEntity=userRepository.findById(pharmacyInfoRequestDto.getPharmacyId())
                .orElseThrow(()->new BadCredentialsException("User not found"));
        List<MedicamentEntity> medicamentEntities=medicamentRepository.findByIdIn(pharmacyInfoRequestDto.getMedicamentEntities());
        pharmacyInfo.setPharmacyId(userEntity);
        pharmacyInfo.setMedicamentEntities(medicamentEntities);
        return new PharmacyInfoRespondDto(pharmacyInfoRepository.save(pharmacyInfo));
    }

    public List<PharmacyInfoRespondDto> getAll(){
        List<PharmacyInfoEntity> pharmacyInfoEntities=pharmacyInfoRepository.findAll();
        return pharmacyInfoEntities.stream().map(PharmacyInfoRespondDto::new).toList();
    }

    public PharmacyInfoRespondDto getById(Integer id){
        return new PharmacyInfoRespondDto(pharmacyInfoRepository.findById(id)
                .orElseThrow(()->new BadCredentialsException("Not found")));
    }

    public PharmacyInfoRespondDto addNewMedicament(PharmacyInfoUpdateDto updateDto,Integer id){
        PharmacyInfoEntity pharmacyInfo=pharmacyInfoRepository.findById(id)
                .orElseThrow(()->new BadCredentialsException("Pharmacy not found"));
        List<MedicamentEntity> meds=medicamentRepository.findByIdIn(updateDto.getMedicament_id());
        meds.forEach(med-> pharmacyInfo.getMedicamentEntities().add(med));
        return new PharmacyInfoRespondDto(pharmacyInfoRepository.save(pharmacyInfo));
    }

    public PharmacyInfoRespondDto removeMedicament(PharmacyInfoUpdateDto updateDto,Integer id){
        PharmacyInfoEntity pharmacyInfo=pharmacyInfoRepository.findById(id)
                .orElseThrow(()->new BadCredentialsException("Pharmacy not found"));
        List<MedicamentEntity> meds=medicamentRepository.findByIdIn(updateDto.getMedicament_id());
        meds.forEach(med->{
            pharmacyInfo.getMedicamentEntities().remove(med);
        });
        return new PharmacyInfoRespondDto(pharmacyInfoRepository.save(pharmacyInfo));
    }

    public List<PharmacyInfoRespondDto> getAllByMedId(PharmacyInfoUpdateDto updateDto){
//        HashSet<PharmacyInfoEntity> hashSet=new HashSet<>();
        List<MedicamentEntity> meds=medicamentRepository.findByIdIn(updateDto.getMedicament_id());
//        meds.forEach(med->{
//
//        });
        List<PharmacyInfoEntity> ls=pharmacyInfoRepository.findAllByMedicamentEntitiesIn(meds);
        return ls.stream().map(PharmacyInfoRespondDto::new).toList();
    }
}
