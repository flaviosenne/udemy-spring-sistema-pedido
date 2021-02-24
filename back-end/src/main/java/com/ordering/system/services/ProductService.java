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
import org.springframework.data.domain.Sort.Direction;
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
    
    
    public Page<Product> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categorias = categoryRepository.findAllById(ids);
		return productRepository.search(nome, categorias, pageRequest);	
	}
}
