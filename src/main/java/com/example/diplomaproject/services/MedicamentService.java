package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.MedicamentRequest;
import com.example.diplomaproject.dto.MedicamentRespondDto;
import com.example.diplomaproject.dto.TagRespondDto;
import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.TagEntity;
import com.example.diplomaproject.repositories.MedicamentRepository;
import com.example.diplomaproject.repositories.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicamentService {
    private final MedicamentRepository medicamentRepository;
    private final TagRepository tagRepository;

    public MedicamentRespondDto create(MedicamentRequest medicamentRequest){
        MedicamentEntity medicamentEntity=new MedicamentEntity();
        medicamentEntity.setMedName(medicamentRequest.getMedName());
        medicamentEntity.setDescription(medicamentRequest.getDescription());
        medicamentEntity.setCountry(medicamentRequest.getCountry());
        medicamentEntity.setProducer(medicamentRequest.getProducer());
        medicamentEntity.setPrice(medicamentRequest.getPrice());
        var tags=tagRepository.findByIdIn(medicamentRequest.getTags());
        medicamentEntity.setTags(tags);
        MedicamentEntity md=medicamentRepository.save(medicamentEntity);
        return new MedicamentRespondDto(md);
    }

    public List<MedicamentRespondDto> getAll(){
        return medicamentRepository.findAll().stream().map(MedicamentRespondDto::new).toList();
    }

    public List<TagRespondDto> getAllTagsById(Long id){
        return medicamentRepository.findById(id).get().getTags().stream().map(TagRespondDto::new).toList();
    }

    public MedicamentRespondDto getById(Long id){
        return new MedicamentRespondDto(medicamentRepository.findById(id).get());
    }

    public List<MedicamentRespondDto> getAllByTagId(Integer id){
        List<MedicamentEntity> medicamentEntityList=tagRepository.findById(id).get().getMedicamentEntityList();
        return medicamentEntityList.stream().map(MedicamentRespondDto::new).toList();
    }

    public Long getTotalPrice(List<MedicamentEntity> list){
        Long ans= 0L;
        for (MedicamentEntity medicamentEntity : list) {
            ans += medicamentEntity.getPrice();
        }
        return ans;
    }

    @Transactional
    public void deleteById(Long id){
        medicamentRepository.deleteById(id);
    }
}