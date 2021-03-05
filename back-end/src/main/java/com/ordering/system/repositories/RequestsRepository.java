package com.ordering.system.repositories;

import java.util.List;

import com.ordering.system.domains.Client;
import com.ordering.system.domains.Requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RequestsRepository extends JpaRepository<Requests, Integer> {
    
    @Transactional(readOnly = true)
    List<Requests> findByClient(Client client);
}
