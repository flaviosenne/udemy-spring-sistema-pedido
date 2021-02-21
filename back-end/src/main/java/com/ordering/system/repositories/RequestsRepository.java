package com.ordering.system.repositories;

import com.ordering.system.domains.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsRepository extends JpaRepository<Request, Integer> {
    
}
