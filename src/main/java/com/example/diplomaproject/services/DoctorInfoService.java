package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.DoctorInfoRequestDto;
import com.example.diplomaproject.dto.DoctorInfoRespondDto;
import com.example.diplomaproject.dto.UserDto;
import com.example.diplomaproject.entities.DoctorInfoEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.repositories.DoctorInfoRepository;
import com.example.diplomaproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorInfoService {
    private final DoctorInfoRepository doctorInfoRepository;
    private final UserRepository userRepository;

    public DoctorInfoRespondDto create(DoctorInfoRequestDto doctorInfoRequestDto){
        DoctorInfoEntity doctorInfo=new DoctorInfoEntity();
        doctorInfo.setMajor(doctorInfoRequestDto.getMajor());
        doctorInfo.setHospitalName(doctorInfoRequestDto.getHospitalName());
        UserEntity user=userRepository.findById(doctorInfoRequestDto.getDoctorId())
                .orElseThrow(()->new BadCredentialsException("User not found"));
        doctorInfo.setDoctorId(user);
        return new DoctorInfoRespondDto(doctorInfoRepository.save(doctorInfo));
    }

    public void deleteById(Integer id){
        doctorInfoRepository.deleteById(id);
    }

    public List<DoctorInfoRespondDto> getAll(){
        List<DoctorInfoEntity> ls=doctorInfoRepository.findAll();
        return ls.stream().map(DoctorInfoRespondDto::new).toList();
    }

    public UserDto getDoctorByInfoId(Integer id){
        DoctorInfoEntity doctorInfo=doctorInfoRepository.findById(id).orElseThrow(()->new BadCredentialsException("Can not find doctor info"));
        return new UserDto(doctorInfo.getDoctorId());
    }

    public DoctorInfoRespondDto getInfoByDoctorId(Long id){
        UserEntity user=userRepository.findById(id).orElseThrow(()->new BadCredentialsException("Can not find user"));
        DoctorInfoEntity doctorInfo=doctorInfoRepository.findDoctorInfoEntityByDoctorId(user);
        return new DoctorInfoRespondDto(doctorInfo);
    }



}
