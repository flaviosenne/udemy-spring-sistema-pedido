package com.ordering.system.resources;



import com.ordering.system.domains.Category;
import com.ordering.system.dto.CategoryDTO;
import com.ordering.system.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){

        return this.categoryService.findAllCategory();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){

        return this.categoryService.findCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Category category){
        Category categorySaved = this.categoryService.saveCategory(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categorySaved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Category category){
        category.setId(id);
        Category categorySaved = this.categoryService.updateCategory(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categorySaved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){

        this.categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
