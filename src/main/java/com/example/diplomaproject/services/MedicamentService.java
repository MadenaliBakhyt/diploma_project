package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.MedicamentRequest;
import com.example.diplomaproject.dto.MedicamentRespondDto;
import com.example.diplomaproject.dto.TagRespondDto;
import com.example.diplomaproject.entities.MedicamentEntity;
import com.example.diplomaproject.entities.OrderEntity;
import com.example.diplomaproject.entities.PharmacyInfoEntity;
import com.example.diplomaproject.entities.TagEntity;
import com.example.diplomaproject.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicamentService {
    private final MedicamentRepository medicamentRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final PharmacyInfoRepository pharmacyInfoRepository;

    private final OrderRepository orderRepository;

    public MedicamentRespondDto create(MedicamentRequest medicamentRequest){
        MedicamentEntity medicamentEntity=new MedicamentEntity();
        medicamentEntity.setMedName(medicamentRequest.getMedName());
        medicamentEntity.setDescription(medicamentRequest.getDescription());
        medicamentEntity.setCountry(medicamentRequest.getCountry());
        medicamentEntity.setProducer(medicamentRequest.getProducer());
        medicamentEntity.setPrice(medicamentRequest.getPrice());
        medicamentEntity.setImageUrl(medicamentRequest.getImageUrl());
        var categories=categoryRepository.findByIdIn(medicamentRequest.getCategory());
        medicamentEntity.setCategory(categories);
        var tags=tagRepository.findByIdIn(medicamentRequest.getTags());
        medicamentEntity.setTags(tags);
        MedicamentEntity md=medicamentRepository.save(medicamentEntity);
        return new MedicamentRespondDto(md);
    }

    public List<MedicamentRespondDto> getAll(){
        return medicamentRepository.findAll().stream().map(MedicamentRespondDto::new).toList();
    }

    public List<MedicamentRespondDto> getByCategory(String name){
        return medicamentRepository.findAllByCategoryContains(categoryRepository.findByName(name)).stream().map(MedicamentRespondDto::new).toList();
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

//    @Modifying
//    @Query(value = "DELETE FROM medicament_entity_category WHERE medicament_entity_id = :id;",
//            nativeQuery = true)
//    public void deletingInMedCat(@Param("id") Long id){
//    }



    @Transactional
    public void deleteById(Long id){
        MedicamentEntity medicament=medicamentRepository.findById(id).orElseThrow(() -> new BadCredentialsException("Not found"));
        List<OrderEntity> orderEntities=orderRepository.findAllByMedicamentEntitiesContains(medicament);
        orderEntities.forEach(orderEntity -> {
            orderEntity.getMedicamentEntities().remove(medicament);
            orderRepository.save(orderEntity);
        });
        List<PharmacyInfoEntity> pharmacyInfoEntities= pharmacyInfoRepository
                .findAllByMedicamentEntitiesIn((List<MedicamentEntity>) medicament);
        pharmacyInfoEntities.forEach(phar->{
            phar.getMedicamentEntities().remove(medicament);
            pharmacyInfoRepository.save(phar);
        });

//        try{
//            deletingInMedCat(id);
//        }catch (Exception e){
//            throw new BadCredentialsException("Can not delete medicament");
//        }
        medicamentRepository.deleteById(id);
    }
}
