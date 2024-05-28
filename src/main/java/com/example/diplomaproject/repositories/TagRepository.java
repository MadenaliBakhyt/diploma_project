package com.example.diplomaproject.repositories;

import com.example.diplomaproject.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity,Integer> {
    Optional<TagEntity> findByTagName(String tagName);

    List<TagEntity> findByIdIn(List<Integer> id);

    Boolean deleteByTagName(String tagName);

    void deleteById(Integer id);


}
