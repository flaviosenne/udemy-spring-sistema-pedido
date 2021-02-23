package com.ordering.system.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ordering.system.domains.Category;
import com.ordering.system.dto.CategoryDTO;
import com.ordering.system.exceptions.DataIntegrityException;
import com.ordering.system.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<List<CategoryDTO>> findAllCategory(){
        List<Category> categories = this.categoryRepository.findAll();

        List<CategoryDTO> listDTO = categories.stream()
                .map(obj ->  new CategoryDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.status(200).body(listDTO);
    }

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

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        if(direction.equals("asc")){
            PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.ASC,
                    orderBy);
            return this.categoryRepository.findAll(pageRequest);
        }
        else {
            PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.DESC,
                    orderBy);
            return this.categoryRepository.findAll(pageRequest);
        }


    }
}
