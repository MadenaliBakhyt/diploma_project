package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.TagRespondDto;
import com.example.diplomaproject.entities.TagEntity;
import com.example.diplomaproject.repositories.MedicamentRepository;
import com.example.diplomaproject.repositories.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    private final MedicamentRepository medicamentRepository;

    public TagRespondDto create(String tagName){
        TagEntity tagEntity=new TagEntity();
        tagEntity.setTagName(tagName);
        return new TagRespondDto(tagRepository.save(tagEntity));
    }

    public TagRespondDto findByName(String tagName) {
        return new TagRespondDto(tagRepository.findByTagName(tagName).orElseThrow(() -> new RuntimeException("Tag not found")));
    }

    public TagRespondDto findById(Integer id) {
        return new TagRespondDto(tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found")));
    }

    public List<TagRespondDto> findAll() {
        List<TagEntity> tg=tagRepository.findAll();
        return tg.stream().map(TagRespondDto::new).toList();
    }



    @Transactional
    public void deleteByTagName(String tagName) {
        tagRepository.deleteByTagName(tagName);
    }

    @Transactional
    public void deleteById(Integer id){
        TagEntity tag = tagRepository.findById(id).orElseThrow(() -> new BadCredentialsException("Not found"));
        tag.getMedicamentEntityList().forEach(medicament -> {
            medicament.getTags().remove(tag);
            medicamentRepository.save(medicament);
        });
        tagRepository.deleteById(id);
    }

}
