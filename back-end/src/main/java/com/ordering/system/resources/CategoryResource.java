package com.ordering.system.resources;

import java.util.ArrayList;
import java.util.List;

import com.ordering.system.domain.Category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    
    @GetMapping
    public List<Category> list(){

        Category category1 = new Category(1, "Informática");
        Category category2 = new Category(2, "Escritório");

        List<Category> listCategories = new ArrayList<>();

        listCategories.add(category1);
        listCategories.add(category2);

        return listCategories;
    }
}
