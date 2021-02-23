package com.ordering.system.services;

import java.util.Optional;

import com.ordering.system.domains.Category;
import com.ordering.system.exceptions.DataIntegrityException;
import com.ordering.system.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Category> findCategoryById(Integer id){
        Optional<Category> category = this.categoryRepository.findById(id);
         
        if(category.isPresent())
            return ResponseEntity.status(200).body(category.get());

        return ResponseEntity.status(404).body(null);
    }

    public Category saveCategory(Category category){
        
        return (this.categoryRepository.save(category));
    }

    public Category updateCategory(Category category){
            return this.categoryRepository.save(category);
    }
    public void deleteCategoryById(Integer id){
        this.findCategoryById(id);
        try{
            this.categoryRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e){

            throw new DataIntegrityException("Can't remove category if exist connect products ");
        }

    }
}
