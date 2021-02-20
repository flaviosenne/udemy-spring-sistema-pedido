package com.ordering.system.repositories;

import com.ordering.system.domains.Adress;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress, Integer> {
    
}
