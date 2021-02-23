package com.ordering.system.resources;



import com.ordering.system.domains.Category;
import com.ordering.system.dto.CategoryDTO;
import com.ordering.system.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryDTO category){
        Category categoryConverted = this.categoryService.toCategory(category);
        Category categorySaved = this.categoryService.saveCategory(categoryConverted);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categorySaved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO category){
        category.setId(id);
        Category categoryConverted = this.categoryService.toCategory(category);
        Category categoryUpdated = this.categoryService.updateCategory(categoryConverted);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoryUpdated.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){

        this.categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue ="0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue ="24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue ="name") String orderBy,
            @RequestParam(value = "direction", defaultValue ="asc") String direction){

        Page<Category> list = this.categoryService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDTO> listDTO = list.map(obj -> new CategoryDTO(obj));
        return ResponseEntity.ok(listDTO);
    }
}
