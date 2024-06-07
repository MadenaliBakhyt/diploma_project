package com.example.diplomaproject.services;

import com.example.diplomaproject.entities.CategoryEntity;
import com.example.diplomaproject.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> findAllCategories(){
        return categoryRepository.findAll();
    }

    public CategoryEntity createCategory(String name){
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setName(name);
        return categoryRepository.save(categoryEntity);
    }
}
