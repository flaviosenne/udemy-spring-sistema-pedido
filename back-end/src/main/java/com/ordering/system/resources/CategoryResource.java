package com.ordering.system.resources;



import com.ordering.system.domains.Category;
import com.ordering.system.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){

        return this.categoryService.findCategoryById(id);
    }
}
