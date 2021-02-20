package com.ordering.system.repositories;

import com.ordering.system.domains.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
