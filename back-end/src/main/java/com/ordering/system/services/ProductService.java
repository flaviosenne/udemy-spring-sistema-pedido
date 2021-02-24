package com.ordering.system.services;

import java.util.List;
import java.util.Optional;

import com.ordering.system.domains.Category;
import com.ordering.system.domains.Product;
import com.ordering.system.exceptions.ObjectNotFoundException;
import com.ordering.system.repositories.CategoryRepository;
import com.ordering.system.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product findProducttById(Integer id){
       Optional<Product> product = this.productRepository.findById(id);

        if(product.isPresent()){
            return product.get();
        }
        throw new  ObjectNotFoundException("product not found");

    }
    
    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){

        List<Category> categories = this.categoryRepository.findAllById(ids);
        if(direction.equals("asc")){
            PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.ASC,
                    orderBy);
            return this.productRepository.search(name, categories, pageRequest);
        }
        else {
            PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.DESC,
            orderBy);
            return this.productRepository.search(name, categories, pageRequest);
            
        }
    }
}
