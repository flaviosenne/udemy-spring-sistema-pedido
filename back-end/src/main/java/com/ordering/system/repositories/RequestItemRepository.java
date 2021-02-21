package com.ordering.system.repositories;

import com.ordering.system.domains.RequestItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Integer> {
    
}
